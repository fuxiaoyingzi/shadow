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
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/31
 *     desc   :
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

    private void initView(Context context) {
        this.mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStrokeJoin(Paint.Join.ROUND);

        calendar = Calendar.getInstance();

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
        canvas.translate(r,r);//中心点移动
        canvas.drawCircle(0, 0, r, paint);//画圆

        for (int i = 0; i < 60; i++) {//刻度
            paint.setStyle(Paint.Style.STROKE);
            if (i % 5 == 0) {//长刻度,长刻度占圆半径的 1/10
                paint.setColor(Color.RED);
                canvas.drawLine(0,r - r/10, 0, r, paint);

                //文字
                paint.setTextSize(20);
                String text = ((i / 5) == 0 ? 12 : (i / 5)) + "";
                Rect textBound = new Rect();
                paint.getTextBounds(text, 0, text.length(), textBound);
                canvas.save();
                canvas.translate(0, -r + 10 + r/10 + (textBound.bottom - textBound.top));
                canvas.rotate(-6 * i);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(text, -(textBound.right - textBound.left) / 2,textBound.bottom, paint);
                canvas.restore();
            } else { //短刻度,长刻度占圆半径的 1/15
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(1);
                canvas.drawLine(0,r - r/15, 0, r, paint);
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

}
