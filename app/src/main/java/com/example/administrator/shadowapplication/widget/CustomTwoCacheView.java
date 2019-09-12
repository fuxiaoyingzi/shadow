package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/1
 *     desc   :
 * </pre>
 */

public class CustomTwoCacheView extends View {
    private int preX, preY;
    private int currX, currY;
    private int firstX, firstY;
    private Bitmap cacheBit;
    private Paint paint;
    private Canvas bitCanvas;
    private Path path;

    public CustomTwoCacheView(Context context) {
        this(context, null);
    }

    public CustomTwoCacheView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTwoCacheView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTwoCacheView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (cacheBit == null) {
            cacheBit = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitCanvas = new Canvas(cacheBit);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(cacheBit, 0, 0, paint);
        canvas.drawPath(path, paint);
    }


    //手绘线条
  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //手指按下，记录第一个点的坐标
                path.reset();
                preX = x;
                preY = y;
                path.moveTo(preX, preY);
                break;
            case MotionEvent.ACTION_MOVE://手指移动，记录当前点的坐标
               *//* currX = x;
                currY = y;*//*
                int controlX = (preX + x) / 2;
                int controlY = (preY + y) / 2;
                path.quadTo(controlX, controlY, x, y);
                // bitCanvas.drawLine(preX, preY, currX, currY, paint);
                invalidate();
                //当前点的坐标成为下一个点的起始坐标
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                bitCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;

    }*/

    //正方形
   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = x;
                preY = y;
                // path.moveTo(preX,preY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.reset();
                if (x < preX && y < preY) { //当前点左上角
                    path.addRect(x, y, preX, preY, Path.Direction.CCW);
                } else if (x > preX && y > preY) { //当前点右下角
                    path.addRect(preX, preY, x, y, Path.Direction.CCW);
                } else if (x > preX && y < preY) {
                    path.addRect(preX, y, x, preY, Path.Direction.CCW);

                } else if (x < preX && y > preY) {
                    path.addRect(x, preY, preX, y, Path.Direction.CCW);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                bitCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }*/

   //圆
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.reset();
                int radius;
                if (x < preX && y < preY) { //当前点左上角
                    radius = Math.min(preX - x, preY - y);
                    path.addCircle(preX, preY, radius, Path.Direction.CCW);
                } else if (x > preX && y > preY) { //当前点右下角
                    radius = Math.min(x - preX, y - preY);
                    path.addCircle(preX, preY, radius, Path.Direction.CCW);
                } else if (x > preX && y < preY) {
                    radius = Math.min(x - preX, preY - y);
                    path.addCircle(preX, preY, radius, Path.Direction.CCW);

                } else if (x < preX && y > preY) {
                    radius = Math.min(preX - x, y - preY);
                    path.addCircle(preX, preY, radius, Path.Direction.CCW);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                bitCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }
}
