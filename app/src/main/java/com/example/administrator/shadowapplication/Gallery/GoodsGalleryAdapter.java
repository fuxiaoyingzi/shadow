package com.example.administrator.shadowapplication.Gallery;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.Gallery.bean.GalleryDataBean;
import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : shadow
 * Desc : 商品详情 gallery adapter
 * Date :2018/4/6/006
 */
public class GoodsGalleryAdapter extends BaseAdapter {
    private Context mContext;
    private int selectItem;
    private List<GalleryDataBean> mGalleryListBeans;

    public GoodsGalleryAdapter(Context c) {
        mContext = c;
        mGalleryListBeans = new ArrayList<>();
    }



    public void setGalleryListBeans(List<GalleryDataBean> galleryListBeans, boolean needClean) {
        if (needClean){
            this.mGalleryListBeans.clear();
        }
        this.mGalleryListBeans.addAll( galleryListBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mGalleryListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mGalleryListBeans.get(position).getPic_url();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectItem(int selectItem) {
        if (this.selectItem != selectItem) {
            this.selectItem = selectItem;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_sample, parent, false);
            viewHolder.mImg = convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (selectItem == position) {

            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) viewHolder.mImg.getLayoutParams();
            params.width = dp2px(47);
            params.height = dp2px(26);
            params.leftMargin = dp2px(10);
            params.rightMargin = dp2px(10);
            viewHolder.mImg.setLayoutParams(params);
            AnimationSet animationSet = new AnimationSet(true);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(300);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 1f, 0.1f, 1f);
            scaleAnimation.setDuration(300);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(scaleAnimation);
            viewHolder.mImg.startAnimation(animationSet);
        }
        viewHolder.mImg.setImageResource(mGalleryListBeans.get(position).getPic_url());
        return convertView;
    }

    private int dp2px(float dp) {
        Resources r = mContext.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }

    private class ViewHolder {
        ImageView mImg;
    }
}