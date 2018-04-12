package com.example.administrator.shadowapplication.Gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.administrator.shadowapplication.Gallery.adapter.WGalleryAdapter;
import com.example.administrator.shadowapplication.R;

/**
 * @author wuzhen
 * @version Version 1.0, 2016-05-10
 */
public class BaseWGalleryActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private WGallery gallery;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wgallery);

        gallery =  findViewById(R.id.wgallery);
        gallery.setScalePivot(WGallery.SCALE_PIVOT_CENTER);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                gallery.setSelection(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       //

        gallery.setAdapter(  new WGalleryAdapter());
        gallery.setOnItemSelectedListener(new EcoGalleryAdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(EcoGalleryAdapterView<?> parent, View view, int position, long id) {
                Log.i("hh","当前位置："+position);
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onNothingSelected(EcoGalleryAdapterView<?> parent) {

            }
        });

       /* RadioGroup rg =  findViewById(R.id.rg);
        rg.check(R.id.rbtn3);
        rg.setOnCheckedChangeListener(this);

        ((SeekBar) findViewById(R.id.seekbar1)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.seekbar2)).setOnSeekBarChangeListener(this);*/
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        /*switch (checkedId) {
            case R.id.rbtn1:
                gallery.setScalePivot(WGallery.SCALE_PIVOT_CENTER);
                break;

            case R.id.rbtn2:
                gallery.setScalePivot(WGallery.SCALE_PIVOT_TOP);
                break;

            case R.id.rbtn3:
                gallery.setScalePivot(WGallery.SCALE_PIVOT_BOTTOM);
                break;

            default:
                break;
        }*/
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
       /* switch (seekBar.getId()) {
            case R.id.seekbar1:
                gallery.setSelectedScale(0.8f + 0.1f * progress);
                break;

            case R.id.seekbar2:
                gallery.setUnSelectedAlpha(progress * 1.f / 10);
                break;

            default:
                break;
        }*/
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    protected BaseAdapter initGalleryAdapter() {
        throw new RuntimeException("必须重写该方法");
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return GalleryFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
