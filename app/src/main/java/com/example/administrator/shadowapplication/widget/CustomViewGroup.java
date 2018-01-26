package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.shadowapplication.R;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/25
 *     desc   :类似线性布局的垂直布局
 * </pre>
 */


public class CustomViewGroup extends ViewGroup {
    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);

    }

    public void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomViewGroup);
        //....获取自定义属性
        a.recycle();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int curHeight = t;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            child.layout(l, curHeight, l + width, curHeight + height);
            curHeight += height;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthModel == MeasureSpec.AT_MOST && heightModel == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), getChildHeightCount());
            } else if (widthModel == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), heightSize);
            } else if (heightModel == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getChildHeightCount());

            }
        }

    }

    public int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getMeasuredWidth() > maxWidth) {
                maxWidth = view.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    public int getChildHeightCount() {
        int childCount = getChildCount();
        int childHeightCount = 0;
        for (int i = 0; i < childCount; i++) {
            childHeightCount += getChildAt(i).getMeasuredHeight();
        }
        return childHeightCount;
    }


    /**
     * view 和 viewGroup的事件分发机制：点击事件分发过程如下 dispatchTouchEvent—->OnTouchListener的onTouch方法—->onTouchEvent–>OnClickListener的onClick方法。
     * 也就是说，我们平时调用的setOnClickListener，优先级是最低的，所以，onTouchEvent或OnTouchListener的onTouch方法如果返回true，
     * 则不响应onClick方法…
     *
     * @param ev
     * @return true, 只会响应此类的dispatchTouchEvent 两次，其它的方法都不会调用,false 什么都不会调用
     * super.dispatchTouchEvent(ev) 根据情况调用onInterceptTouchEvent,onTouchEvent
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("hh", "CustomViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param ev
     * @return true 拦截 不向下分发，所以子view就不会调用 dispatchTouchEvent，然后由此类实现onTouchEvent
     * false 向下分发，只有子view onTouchEvent 返回true时候，才会调用此类的onTouchEvent
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("hh", "CustomViewGroup onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 如果子类处理了，则此方法不会调用
     *
     * @param event
     * @return true 只会调用此类 dispatchTouchEvent方法一次
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("hh", "CustomViewGroup onTouchEvent");
        return false;
    }
}
