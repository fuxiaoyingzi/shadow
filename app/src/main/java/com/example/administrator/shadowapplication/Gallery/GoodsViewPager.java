package com.example.administrator.shadowapplication.Gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author : shadow
 * Desc :
 * Date :2018/6/27/027
 */

public class GoodsViewPager extends ViewPager {
    public GoodsViewPager(@NonNull Context context) {
        super(context);
    }

    public GoodsViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int currentPosition = -1;
    private boolean smoothScroll =true;

    public void setCurrentPosition(int position,boolean smoothScroll) {
        this.currentPosition = position;
        this.smoothScroll = smoothScroll;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        //因为viewpager MotionEvent.ACTION_UP 会在当前基础上向前或者向后设置下一个选中的item
        if (currentPosition != -1 && getCurrentItem() != currentPosition){
            setCurrentItem(currentPosition,smoothScroll);
            currentPosition = -1;
        }
        return true;
    }


}
