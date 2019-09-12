package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/8
 *     desc   :view的触摸事件
 * </pre>
 */
public class CustomViewMotionEvent extends View {
    private GestureDetector gestureDetector;
    private Scroller scroller;
    public CustomViewMotionEvent(Context context) {
        super(context);
    }

    public CustomViewMotionEvent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewMotionEvent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomViewMotionEvent(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initView(Context context){
        scroller = new Scroller(context);
        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {//手指轻轻触摸屏幕的一瞬间
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {//手指轻轻触摸屏幕尚未松开或拖动

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {//手指轻轻触摸屏幕后松开
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {//手指按下屏幕并拖动
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {//长按

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { //手指触摸屏幕，快速滑动
                return false;
            }
        });
        gestureDetector.setIsLongpressEnabled(false); //解决长按屏幕后无法拖动的现象

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        VelocityTracker velocityTracker = VelocityTracker.obtain(); //速度追踪
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        Log.d("hh","X轴移动速度："+velocityTracker.getXVelocity());
        Log.d("hh","Y轴移动速度："+velocityTracker.getYVelocity());
        velocityTracker.clear();
        velocityTracker.recycle();
        return gestureDetector.onTouchEvent(event);
    }



    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        scroller.startScroll(scrollX,0,delta,0,1000);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 当包含此view的activity显示时候调用
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /**
     * 当包含此view的activity退出时或者此view被remove的时候调用
     * 与attachedToWindow是一致的，一般此处处理内存释放
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
