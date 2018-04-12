package com.example.administrator.shadowapplication.Gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/6/006
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int mGalleryItemBackground;
    private int[] myImageIds = {R.drawable.like, R.drawable.ic_my_selector, R.drawable.like, R.drawable.ic_my_selector, R.drawable.like,};
    private int selectItem;

    public ImageAdapter(Context c) {
        mContext = c;
       /* TypedArray a = mContext.obtainStyledAttributes(R.styleable.Gallery); *//* 使用在res/values/attrs.xml中的定义 的Gallery属性. *//*
//        mGalleryItemBackground = a.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0); //*//*取得Gallery属性的Index
        a.recycle();*//* 让对象的styleable属性能够反复使用 */
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;//最大值能使图片无限滑动
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(myImageIds[position%myImageIds.length]);//实现循环滑动
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if(selectItem==position){
            //选中时的动画
//            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_scale_action);    //实现动画效果
//            imageView.startAnimation(animation);  //选中时，这时设置的比较大
            imageView.setLayoutParams(new Gallery.LayoutParams(320,240));
        }
        else{
            //未选中时的动画
//            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_scale_action);    //实现动画效果
//            imageView.startAnimation(animation);
            imageView.setLayoutParams(new Gallery.LayoutParams(160,120));//未选中
        }
        return imageView;
    }
}