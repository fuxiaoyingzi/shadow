package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/31
 *     desc   :
 * </pre>
 */


public class CustomRulerView extends View {
    private Paint paint;

    public CustomRulerView(Context context) {
        this(context,null);
    }

    public CustomRulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int limit = 1;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        RectF rectF = new RectF(20f,20f,width-20,220f);
        canvas.drawRoundRect(rectF,20,20,paint);
        float intercept = (float) ((width-120)/100.0);//总宽度/总刻度 = 每个刻度直接的间隔
        float startX = 60;
        float startY = 220;
        float endX = 60;
        float endY;
        for (int i = 0;i<=100;i++){
            if (i % 10 == 0){
                paint.setStrokeWidth(2);
                endY = 100;
                String text;
                if (i == 0){
                    text = "0 cm";
                }else {
                    text = String.valueOf(limit++);
                }
                Rect textBound = new Rect();
                paint.setColor(Color.RED);
                paint.getTextBounds(text, 0, text.length(), textBound);
                canvas.drawText(text,endX-(textBound.right - textBound.left)/2,endY-(textBound.bottom - textBound.top+10),paint);
            }else if (i % 5 == 0){
                paint.setStrokeWidth(2);
                endY = 140;
            }else {
                paint.setStrokeWidth(1);
                endY = 180;
            }
            paint.setColor(Color.GRAY);
            canvas.drawLine(startX,startY,endX,endY,paint);
            startX = startX+intercept;
            endX = endX+intercept;
        }
    }
}
