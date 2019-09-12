package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.shadowapplication.R;

/**
 * Created by Administrator on 2017/8/21.
 */

public class CustomView extends View {
    private int paintColor;
    private  float paintWidth;
    private float paintTestSize;
    private float circleWidth;
    private int rectColor;
    private Paint paint;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs){
        TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        paintColor = a.getColor(R.styleable.CustomView_paint_color,context.getResources().getColor(R.color.black));
        paintWidth = a.getDimension(R.styleable.CustomView_paint_width,10f);
        paintTestSize = a.getDimension(R.styleable.CustomView_paint_text_size,20f);
        circleWidth = a.getDimension(R.styleable.CustomView_circle_width,10f);
        rectColor = a.getColor(R.styleable.CustomView_rect_color,context.getResources().getColor(R.color.md_pink_A100));
        a.recycle();
        paint = new Paint();
        paint.setColor(paintColor);
        paint.setStrokeWidth(paintWidth);
        paint.setTextSize(paintTestSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("矩形",50,150,paint);
        RectF rectF = new RectF(200,50,400,250);
        canvas.drawRect(rectF,paint);
        canvas.drawText("圆" ,50,350,paint);
        paint.setColor(rectColor);
        paint.setAntiAlias(true);
        canvas.drawCircle(300,360,100,paint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        canvas.drawBitmap(bitmap, 250,500, paint);
        postInvalidate();

    }
}
