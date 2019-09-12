package com.example.administrator.shadowapplication.anim;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author : shadow
 * Desc : 路径view
 * Date :2019/1/15/015
 */
public class PathView extends View {
    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private Paint mPaint;

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.argb(255, 255, 0, 0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    private float cx = 500, cy = 300;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //弧线
        path.moveTo(300, 300);
        RectF rect = new RectF(100, 100, 300, 300);
        path.addRect(rect, Path.Direction.CW);
        path.addArc(rect, 0, 90);

        //二级贝塞尔曲线
        path.moveTo(400, 400);
        path.quadTo(cx, cy, 800, 400);
        canvas.drawPath(path, mPaint);
    }

    /**
     * 动态修改二级贝塞尔曲线的控制点
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cx = event.getRawX();
                cy = event.getRawY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                cx = event.getRawX();
                cy = event.getRawY();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
