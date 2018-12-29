package com.example.administrator.shadowapplication.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.autonavi.amap.mapcore.interfaces.IglModel;

/**
 * Author : shadow
 * Desc : view 事件体系
 * Date :2018/12/29/029
 */
public class ViewEvent extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    /**
     * 滑动速度监听，可以监听手指的滑动速度，包括垂直速度和水平速度
     */
    private VelocityTracker velocityTracker;

    /**
     * 手势监听，辅助检测用户的单击，长按，滑动，双击等行为
     */
    private GestureDetector mDetector;

    /**
     * 弹性滑动,Scroller本事没有滑动效果，只是要借助computeScroll()方法
     *Scroller工作机制：它不断让View重绘，每一次重绘距滑动起始时间会有一个时间间隔computeScrollOffset，
     * 通过这个时间间隔，Scroller就能知道View当前的滑动位置，通过scrollTo来完成view的滑动
     * 就这样，View的每一次重绘都导致view进行小幅度的滑动，多次小幅度的滑动，就构成弹性滑动
     */
    private Scroller mScroller;

    public ViewEvent(Context context) {
        this(context,null);
    }

    public ViewEvent(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewEvent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewEvent(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        //最短滑动距离，如果两个滑动之间的距离小于此值，则不认为是一次滑动
        //当需要做滑动处理的时候，可以依靠此常量来做一些过滤
        int touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        /*==============滑动速度=============================*/
         velocityTracker = VelocityTracker.obtain();

         /*================手势检测=================================*/
        mDetector = new GestureDetector(context,this);
        //手势检测双击
        mDetector.setOnDoubleTapListener(this);
        //解决长按屏幕后无法拖拽的情况
        mDetector.setIsLongpressEnabled(false);

        //===========弹性滑动==============
        mScroller = new Scroller(context);
    }

    /**
     * 如果只是监听滑动，完全可以在onTouchEvent中执行
     * 如果是监听双击，可以用手势辅助
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);
        //计算速度，在一段时间内手指滑动的像素数，比如1秒
        velocityTracker.computeCurrentVelocity(1000);
        //获取水平滑动速度和垂直滑动速度，速度可以为负数，必须在之前调用计算速度的方法
        //速度 = （终点位置 - 起始位置）/时间
        float xVelocity =velocityTracker.getXVelocity();
        float yVeloctiy = velocityTracker.getYVelocity();
        //当不需要用的时候，速度重置和回收
        velocityTracker.clear();
        velocityTracker.recycle();

        //============================================
        //使用手势监听，辅助用户的点击行为
        return mDetector.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        //computeScrollOffset 返回true表明滑动还没有结束 false表明滑动结束
        if (mScroller.computeScrollOffset()){
            //滑动到当前位置
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            //重绘界面，调用onDraw
            postInvalidate();
        }else {
            //空实现
            super.computeScroll();
        }
    }

    /**
     * 平缓的滑动
     *  @param destX 要滑动的X轴距离
     * @param destY
     */
    private void smoothScrollTo(int destX,int destY){
        //当前位置
        int scrollX = getScrollX();
        //目标位置
        int delta = destX - scrollX;
        //1000ms 内慢慢滑向destX
        mScroller.startScroll(scrollX,0,delta,0,1000);
        //重新绘制
        invalidate();
    }


    /**
     * 动画效果，实现弹性滑动
     *  @param destX 要滑动的X轴距离
     * @param destY
     */
    private void animationScroll(int destX,int destY){
        int startX =0;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //动画的完成比例
                float  animatedFraction =animation.getAnimatedFraction();
                //根据动画完成的比例，来实现要滑动的位置
                scrollTo(startX+(int) (destX*animatedFraction),0);
            }
        });
        valueAnimator.start();
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * 单击
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * 拖动
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /**
     * 长按
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 快速滑动
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /**
     * 双击
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
