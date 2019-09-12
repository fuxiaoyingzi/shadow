package com.example.administrator.shadowapplication.Gallery;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GalleryActivity extends AppCompatActivity {

    private Gallery mGallery;
    private ImageAdapter imageAdapter;
    private ViewPager mViewPager;
    private MyPagerAdapter mViewPagerAdapter;
    private List<GalleryListBean> mGalleryListBeans;
    private List<PagerGoodsBean> mViewPagerListBeans;
    private List<String> mGalleryIdList;
    private List<String> mViewPagerIdList;

    private int  fragmentSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initView();
        //initGalleryAdapter();
        initViewPager();
    }

    private void initView() {
      /*
        mViewPagerListBeans = new ArrayList<>();
        mViewPagerIdList = new ArrayList<>();
        testViewPagerData("1", 3);*/

        findViewById(R.id.updateViewPager).setOnClickListener(v -> {
            int size = 0;
            String mainId;
            if (galleryCurrentPosition != mGalleryListBeans.size() - 1) {//不是最后一个
                mainId = mGalleryListBeans.get(galleryCurrentPosition + 1).getMainId();
                for (int i = galleryCurrentPosition + 1; i < mGalleryListBeans.size(); i++) {
                    size++;
                    if (i + 1 == mGalleryListBeans.size() ||
                            mGalleryListBeans.get(i).getMainId() != mGalleryListBeans.get(i+1).getMainId())
                    {
                        break;
                    }
                }
                if (size == 0) size=1;
                testViewPagerData(mainId, size);

                if (mViewPager.getAdapter() != null) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    List<Fragment> fragments = fm.getFragments();
                    if(fragments != null && fragments.size() >0){
                        for (int i = 0; i < fragments.size(); i++) {
                            ft.remove(fragments.get(i));
                        }
                    }
                    ft.commit();}
            }


           /* testViewPagerData("3", 4);
            mViewPagerAdapter.setImagesId(mViewPagerListBeans);
            mViewPager.setCurrentItem(0);*/


            // start
            // 可以删除这段代码看看，数据源更新而viewpager不更新的情况
            // 在数据源更新前增加的代码，将上一次数据源的fragment对象从FragmentManager中删除
            if (mViewPager.getAdapter() != null) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                List<Fragment> fragments = fm.getFragments();
                if(fragments != null && fragments.size() >0){
                    for (int i = 0; i < fragments.size(); i++) {
                        ft.remove(fragments.get(i));
                    }
                }
                ft.commit();
            }
            // End
            fragmentSize = 6;
            // 重写adapter的notifyDataChanged方法
            mViewPagerAdapter.notifyDataSetChanged();
            mViewPager.setCurrentItem(0);
        });
    }

    private int galleryCurrentPosition;

    private void initGalleryAdapter() {
        mGalleryListBeans = new ArrayList<>();
        mGalleryIdList = new ArrayList<>();
        testGalleryData();
        imageAdapter = new ImageAdapter(this);
        imageAdapter.setGalleryListBeans(mGalleryListBeans,true);
        mGallery = findViewById(R.id.gallery);
        mGallery.setSpacing(0);
        mGallery.setSelection(0, true);
        mGallery.setAdapter(imageAdapter);
        mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageAdapter.setSelectItem(position);
                galleryCurrentPosition = position;
                GalleryListBean galleryListBean = mGalleryListBeans.get(position);
                updatePosition(galleryListBean.getMainId() + galleryListBean.getSubId(), true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initViewPager() {
        mViewPager = findViewById(R.id.viewPager);
        mViewPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
       // mViewPagerAdapter.setImagesId(mViewPagerListBeans);
        fragmentSize = 3;
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtil.d("hh", " addOnPageChangeListener --- onPageScrolled---positionOffsetPixels " + positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d("hh", " addOnPageChangeListener --- onPageSelected---position " + position);

                PagerGoodsBean viewPagerListBean = mViewPagerListBeans.get(position);
                updatePosition(viewPagerListBean.getMainId() + viewPagerListBean.getSubId(), false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtil.d("hh", " addOnPageChangeListener --- onPageScrollStateChanged---state " + state);
            }
        });
    }

    //生成gallery数据
    private void testGalleryData() {
        mGalleryListBeans.add(createGalleryData("1", "1", R.drawable.pic0,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("1", "2", R.drawable.pic1,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("1", "3", R.drawable.pic2,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("2", "1", R.drawable.pic3,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("3", "1", R.drawable.pic4,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("3", "2", R.drawable.pic5,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("3", "3", R.drawable.pic6,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("3", "4", R.drawable.pic7,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("4", "1", R.drawable.pic8,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("4", "2", R.drawable.pic9,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("5", "1", R.drawable.pic0,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("5", "2", R.drawable.pic1,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("5", "3", R.drawable.pic2,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("6", "1", R.drawable.pic3,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("7", "1", R.drawable.pic4,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("7", "2", R.drawable.pic5,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("7", "3", R.drawable.pic6,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("7", "4", R.drawable.pic7,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("8", "1", R.drawable.pic8,GalleryListBean.TYPE_GOODS));
        mGalleryListBeans.add(createGalleryData("8", "2", R.drawable.pic9,GalleryListBean.TYPE_GOODS));

        for (int i = 0; i < mGalleryListBeans.size(); i++) {
            GalleryListBean galleryListBean = mGalleryListBeans.get(i);
            mGalleryIdList.add(galleryListBean.getMainId() + galleryListBean.getSubId());
        }


    }

    private GalleryListBean createGalleryData(String mainId, String subId, Integer imageUrl,int type) {
        return new GalleryListBean(mainId, subId, imageUrl,type);
    }

    //生成viewpager数据
    private void testViewPagerData(String mainId, int size) {
        mViewPagerListBeans.clear();
        mViewPagerIdList.clear();
        for (int i = 0; i < size; i++) {
            mViewPagerListBeans.add(createViewPagerData(mainId, String.valueOf(i + 1), R.drawable.pic0));
        }

        for (int i = 0; i < mViewPagerListBeans.size(); i++) {
            PagerGoodsBean viewPagerListBean = mViewPagerListBeans.get(i);
            mViewPagerIdList.add(viewPagerListBean.getMainId() + viewPagerListBean.getSubId());
        }
    }

    private PagerGoodsBean createViewPagerData(String mainId, String subId, Integer imageUrl) {
        return new PagerGoodsBean(mainId, subId, imageUrl);
    }

    //更新,避免重复更新
    private void updatePosition(String position, boolean refreshViewPager) {
        if (refreshViewPager) {
            int pagerPosition = mViewPagerIdList.indexOf(position);
            if (pagerPosition != -1 && mViewPager.getCurrentItem() != pagerPosition) {
                mViewPager.setCurrentItem(pagerPosition);
            }
        } else {
            int galleryPosition = mGalleryIdList.indexOf(position);
            if (galleryPosition != -1 && mGallery.getSelectedItemPosition() != galleryPosition) {
                mGallery.setSelection(galleryPosition, true);
            }
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<PagerGoodsBean> mImageList;
        private Map<String, GalleryFragment> mFragmentMap;
        private FragmentManager fm;



        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
           /* mImageList = new ArrayList<>();
            mFragmentMap = new HashMap<>();
            this.fm = fm;*/
           for (int i =0; i< mFragmentMap.size();i++){
               mFragmentMap.get(String.valueOf(i));
           }
        }


        public void setImagesId(List<PagerGoodsBean> imagesId) {
            this.mFragmentMap.clear();
            this.mImageList.clear();
            mImageList.addAll(imagesId);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
           /* LogUtil.d("hh", " MyPagerAdapter --- getItem() ---position " + position);
            GalleryFragment fragment = GalleryFragment.getInstance(mImageList.get(position));
            //mFragmentMap.put(String.valueOf(position),fragment);
            return fragment;*/

            Bundle bundle=new Bundle();
            bundle.putString("text","第"+position+"页");
            MyFragment myFragment=new MyFragment();
            myFragment.setArguments(bundle);
            return myFragment;
        }

        @Override
        public int getCount() {
            LogUtil.d("hh", " MyPagerAdapter --- getCount() "+mImageList.size());
            //return mImageList.size();
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
            if ( mChildCount > 0) {
                // 这里利用判断执行若干次不缓存，刷新
                mChildCount --;
                // 返回这个是强制ViewPager不缓存，每次滑动都刷新视图
                return POSITION_NONE;
            }
            // 这个则是缓存不刷新视图
            return super.getItemPosition(object);
        }
        // end
    }

    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.85f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                view.setAlpha(MIN_ALPHA);
                view.setScaleY(MIN_SCALE);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(1f);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(MIN_ALPHA);
                view.setScaleY(MIN_SCALE);
            }

        }
    }
}
