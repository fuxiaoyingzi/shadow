package com.example.administrator.shadowapplication.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author : shadow
 * Desc :
 * Date :2018/12/1/001
 */
public class CustomBitmap extends View {
    public CustomBitmap(Context context) {
        this(context,null);
    }

    public CustomBitmap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    private Paint mPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(getBitmap(), 100, 100, mPaint);
    }

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);
        Rect mRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        bitmapCanvas.drawRect(mRect, mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(5);
        bitmapCanvas.drawLine(0, 0, 200, 200, mPaint);
        bitmapCanvas.drawLine(200, 0, 0, 200, mPaint);
        return bitmap;
    }
}
