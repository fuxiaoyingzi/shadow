package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/23
 *     desc   :画布操作
 * </pre>
 */


public class CustomView2 extends View {
    private ShapeDrawable shapeDrawable;
    private Paint mPaint;
    private int mWidth, mHeight;

    public CustomView2(Context context) {
        this(context, null);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs, defStyleAttr);


    }

    /**
     * 构造函数的初始化，一般用于初始化一些数据，获取自定义属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
         /* TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView2, defStyleAttr, defStyleRes);
        paintWidth = array.getInteger(R.styleable.CustomView2_paint_width, 3);
        paintColor = array.getColor(R.styleable.CustomView2_paint_color, Color.RED);
        fillColor = array.getColor(R.styleable.CustomView2_fill_color, Color.BLUE);
        array.recycle();*/
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        int x = 10;
        int y = 10;
        int width = 100;
        int height = 50;
        shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(Color.YELLOW);
        shapeDrawable.setBounds(x, y, x + width, y + height);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d("hh", " CustomView2 onMeasure widthSize = " + widthSize + ", widthModel = " + widthModel);
        Log.d("hh", "CustomView2 onMeasure heightSize = " + heightSize + ", heightModel = " + heightMode);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("hh", "CustomView2 onSizeChanged");
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("hh", "CustomView2 onLayout");

    }

    /**
     * @param l getLeft
     * @param t getTop
     * @param r getRight
     * @param b getBottom
     */
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.d("hh", "CustomView2 layout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("hh", "CustomView2 onDraw");
        /*shapeDrawable.draw(canvas);
        canvas.drawColor(Color.CYAN);
        RectF rectF = new RectF(200, 200, 500, 400);
        canvas.drawRoundRect(rectF, 150, 100, mPaint);*/

        /*移动画布
        canvas.drawCircle(0, 0, 50, mPaint);
        //canvas.drawColor(Color.RED);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        //canvas.drawColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
        移动到中心点
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 100, mPaint);*/

        //画布缩放
       /* canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(0, -100, 100, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(0.5f, 0.5f);//缩小0.5倍，但是不翻转
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(4f, 4f); //放大4倍，不反转
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(0.5f, 0.1f);//缩放是叠加的
        canvas.scale(0.5f, 0.5f, 500, 0);//调用两次缩放则 x轴实际缩放为0.5x0.5=0.25 y轴实际缩放为0.5x0.1=0.05
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(-2f, -2f);//放大2倍，根据中心点进行x轴Y轴翻转
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rectF, mPaint);*/

        //绘制无限回形图
       /* canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(-200, -200, 200, 200);
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, mPaint);
        }*/

        //移动
        /*canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(0, -100, 100, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.rotate(180);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);

        canvas.rotate(90, 200, 0);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);*/

        //小太阳
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 100, mPaint);
        canvas.drawCircle(0, 0, 80, mPaint);
        mPaint.setColor(Color.RED);
        for (int i = 0; i <= 360; i += 10) {
            canvas.drawLine(0, 100, 0, 120, mPaint);
            canvas.rotate(10);
        }


        //保存与回滚操作画布的操作是不可逆的，而且很多画布操作会影响后续的步骤，
        // 例如第一个例子，两个圆形都是在坐标原点绘制的，而因为坐标系的移动绘制出来的实际位置不同。所以会对画布的一些状态进行保存和回滚。
       /* canvas.save();//保存当前状态
        //各种操作。。。。。
        canvas.restore();//回滚到保存的状态*/
    }

    /**
     * @param event
     * @return true 不处理onTouchEvent，直接返回false 调用父类的onTouchEvent，
     * 返回super.dispatchTouchEvent(event),根据情况判断调用此类的onTouchEvent
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("hh", "customview2 dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    /**
     * @param event
     * @return false 表示此类不处理onTouchEvent ，提交给父类onTouchEvent执行
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("hh", "customview2 onTouchEvent");
        return super.onTouchEvent(event);
    }
}
