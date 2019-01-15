package com.example.administrator.shadowapplication.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author : shadow
 * Desc :由path路径决定此view的执行
 * Date :2019/1/15/015
 */
public class PathAnimatorView extends View {
    public PathAnimatorView(Context context) {
        super(context);
    }

    public PathAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 100, 20, mPaint);
    }

}
