package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/30
 *     desc   :
 * </pre>
 */


public class CustomSportCircle extends View {
    private int x;
    private int y = 100;
    private static final int RADIUS = 30;
    private boolean direction; //true 右边
    private Paint paint;

    public CustomSportCircle(Context context) {
        this(context, null);
    }

    public CustomSportCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSportCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomSportCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    public void initPaint() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        x = RADIUS;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        canvas.drawCircle(x, y, RADIUS, paint);
        if (x <= RADIUS) { //在左边
            direction = true;
        } else if (width - x <= RADIUS) {//在右边
            direction = false;
        }
        x = direction ? x + 5 : x - 1;
    }
}
