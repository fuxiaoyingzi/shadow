package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.administrator.shadowapplication.crash_log.LogUtil;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/31
 *     desc   :自定义时钟
 * </pre>
 */
public class CustomWatchView extends View implements Runnable {
    private Paint paint;
    private int mWidth, mHeight;
    private Calendar calendar;
    private Context mContext;

    public CustomWatchView(Context context) {
        this(context, null);
    }

    public CustomWatchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }



    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 10, 1000);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int len = Math.min(mWidth, mHeight);
        //绘制表盘
        drawPlate(canvas, len);
        //绘制指针
        drawPoints(canvas, len);
    }

    private void drawPlate(Canvas canvas, int len) {
        canvas.save();
        int r = len / 2;
        canvas.translate(r, r);//中心点移动
        canvas.drawCircle(0, 0, r, paint);//画圆

        for (int i = 0; i < 60; i++) {//刻度
            paint.setStyle(Paint.Style.STROKE);
            if (i % 5 == 0) {//长刻度,长刻度占圆半径的 1/10
                paint.setColor(Color.RED);
                canvas.drawLine(0, r - r / 10, 0, r, paint);

                //文字
                paint.setTextSize(20);
                String text = ((i / 5) == 0 ? 12 : (i / 5)) + "";
                Rect textBound = new Rect();
                paint.getTextBounds(text, 0, text.length(), textBound);
                canvas.save();
                canvas.translate(0, -r + 10 + r / 10 + (textBound.bottom - textBound.top));
                canvas.rotate(-6 * i);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(text, -(textBound.right - textBound.left) / 2, textBound.bottom, paint);
                canvas.restore();
            } else { //短刻度,长刻度占圆半径的 1/15
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(1);
                canvas.drawLine(0, r - r / 15, 0, r, paint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }


    private void drawPoints(Canvas canvas, int len) {
        //获取系统时间
        calendar.setTimeInMillis(System.currentTimeMillis());
        //获取时分秒
        int hours = calendar.get(Calendar.HOUR) % 12;
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        //当前小时对应的角度
        int degree = 360 / 12 * hours;
        //角度转换为弧度
        double radians = Math.toRadians(degree);
        //根据当前时计算时针两个点的坐标
        //起点（圆中心），终点：计算得到
        int r = len / 2;
        int startX = r;
        int startY = r;
        int endX = (int) (startX + r * 0.5 * Math.cos(radians));
        int endY = (int) (startY + r * 0.5 * Math.sin(radians));

        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);
        canvas.rotate(-90, r, r); //从12点开始
        canvas.drawLine(startX, startY, endX, endY, paint); //画时针
        canvas.restore();

        //画分针
        //计算角度
        degree = 360 / 60 * minutes;
        radians = Math.toRadians(degree);
        endX = (int) (startX + r * 0.6 * Math.cos(radians));
        endY = (int) (startY + r * 0.6 * Math.sin(radians));
        canvas.save();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90, r, r);
        //画时针
        canvas.drawLine(startX, startY, endX, endY, paint);
        canvas.restore();


        //画秒针
        degree = 360 / 60 * seconds;
        radians = Math.toRadians(degree);
        endX = (int) (startX + r * 0.8 * Math.cos(radians));
        endY = (int) (startY + r * 0.8 * Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(1);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90, r, r);
        //画时针
        canvas.drawLine(startX, startY, endX, endY, paint);
        //再给秒针画个“尾巴”
        radians = Math.toRadians(degree - 180);
        endX = (int) (startX + r * 0.15 * Math.cos(radians));
        endY = (int) (startY + r * 0.15 * Math.sin(radians));
        canvas.drawLine(startX, startY, endX, endY, paint);
        canvas.restore();

    }


    private int lastX, lastY;

    /**
     * view移动方法1，通过layout重新设置左上右下参数，刷新界面
     */
   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://点击
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE://移动
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                break;
        }
        return true;
    }*/

    /**
     * view的移动方法2 offsetLeftAndRight，offsetTopAndBottom
     * @param event
     * @return
     */
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
        }
        return true;
    }*/

    /**
     * view的移动方法3 layoutParams
     * @param event
     * @return
     */
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                //父类group view必须是LinearLayout
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft()+offsetX;
                layoutParams.topMargin = getTop()+offsetY;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }*/

    /**
     * view的移动方法4 view动画
     * @param event
     * @return
     */
    /*
    1.
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate android:fromXDelta="0"
            android:toXDelta="300"
            android:fromYDelta="0"
            android:fillAfter = "true"
            android:duration = "1000"
            android:toYDelta="250"/>

    </set>
    2.
      watchView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_view));
      view动画不能改变view的位置参数

     或者利用属性动画来移动

     ObjectAnimator.ofFloat(watchView,"translationX",0,300)
                .setDuration(1000)
                .start();
    * */

    /**
     * 移动到一个具体的点
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        LogUtil.d("hh","customView scrollTo");
    }

    /**
     * 移动的增量，最终还是会调用scrollTo
     * @param x
     * @param y
     */
    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
        LogUtil.d("hh","customView scrollBy");

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                //父类group view必须是LinearLayout
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
        }
        return true;
    }


    /**
     * view的移动方法5 Scroller
     * scrollTo，scrollBy移动的是瞬间完成的，没有过渡效果，可以用Scroller来完成
     * @param event
     * @return
     */
    private Scroller mScroller = new Scroller(mContext);
    private void initView(Context context) {
        this.mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStrokeJoin(Paint.Join.ROUND);
        calendar = Calendar.getInstance();

        //控制view的滑动
        mScroller = new Scroller(mContext);
    }

    /**
     * Scroller本身是不能实现view的滑动的，它需要和view的computeScroll()方法配合才能实现弹性滑动的效果
     * 系统会在绘制view的时候在draw()方法中调用该方法
     * 调用父类的scrollTo()方法，并通过Scroller来不断获取当前的滚动值，每滑动一小段距离
     * 我们就调用invalidate()方法不断重绘，重绘就会调用computeScroll()，这样就不断的移动一个小的距离并连贯起来就实现了平滑的移动
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 在2000ms内沿X轴平移deltaX像素，在需要用到的地方调用即可
     * @param destX
     */
    public void smoothScrollTo(int destX){
        int scrollX = getScrollX();
        int deltaX = destX-scrollX;//x轴增量
        mScroller.startScroll(scrollX,0,deltaX,0,2000);
    }
}
