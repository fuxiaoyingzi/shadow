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

import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/6/006
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int selectItem;
  /*  private Integer mImagesId[] = {
            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
    };
*/
    private List<GalleryListBean> mGalleryListBeans;

    public ImageAdapter(Context c) {
        mContext = c;
        mGalleryListBeans = new ArrayList<>();
    }



    public void setGalleryListBeans(List<GalleryListBean> galleryListBeans,boolean needClean) {
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
        return mGalleryListBeans.get(position).getImageUrl();
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
        viewHolder.mImg.setImageResource(mGalleryListBeans.get(position).getImageUrl());
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