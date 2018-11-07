package com.example.administrator.shadowapplication.Gallery;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerChangeDataActivity extends AppCompatActivity {

    ViewPager viewPager;
    int fragmentSize = 0;
    MyAdapter adapter;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewpager);
        viewPager = (ViewPager) findViewById(R.id.pager);

        tag = "first";
        fragmentSize = 3;
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
    }

    public void btn_click(View v) {
        update();
    }

    public void update() {

        // start
        // 可以删除这段代码看看，数据源更新而viewpager不更新的情况
        // 在数据源更新前增加的代码，将上一次数据源的fragment对象从FragmentManager中删除
        if (viewPager.getAdapter() != null) {
            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            List<Fragment> fragments = adapter.getFragments();
           // List<Fragment>fragments1 = fm.getActiveFragments()

            if (fragments != null && fragments.size() > 0) {
                for (int i = 0; i < fragments.size(); i++) {
                    ft.remove(fragments.get(i));
                }
            }
            ft.commit();
            fm.executePendingTransactions();
        }
        // End

        tag = "update";
        fragmentSize = 6;
        adapter.cleanFragment();
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(0);

    }

    public class MyAdapter extends FragmentPagerAdapter {

        private FragmentManager mFragmentManager;
        List<Fragment> mFragments;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            this.mFragmentManager = fm;
            mFragments= new ArrayList<>();
        }

        public void cleanFragment() {
            mFragments .clear();
        }

        public List<Fragment> getFragments() {
            return mFragments;
        }

        @Override
        public Fragment getItem(int position) {

            Bundle bundle = new Bundle();
            bundle.putString("text", "第" + position + "页" + tag);
            MyFragment myFragment = new MyFragment();
            myFragment.setArguments(bundle);
            mFragments.add(myFragment);
            return myFragment;
        }

        @Override
        public int getCount() {
            return fragmentSize;
        }

        // start
        // 可以删除这段代码看看，数据源更新而viewpager不更新的情况
        private int mChildCount = 0;

        @Override
        public void notifyDataSetChanged() {
            // 重写这个方法，取到子Fragment的数量，用于下面的判断，以执行多少次刷新
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                // 这里利用判断执行若干次不缓存，刷新
                mChildCount--;
                // 返回这个是强制ViewPager不缓存，每次滑动都刷新视图
                return POSITION_NONE;
            } else {
                // 这个则是缓存不刷新视图
                return super.getItemPosition(object);
            }
        }
        // end
    }

}
