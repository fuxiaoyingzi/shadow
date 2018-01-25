package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.shadowapplication.R;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/23
 *     desc   :
 * </pre>
 */


public class CustomView2 extends View {
    private ShapeDrawable shapeDrawable;
    private Paint mPaint;

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
       /* TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView2, defStyleAttr, defStyleRes);
        paintWidth = array.getInteger(R.styleable.CustomView2_paint_width, 3);
        paintColor = array.getColor(R.styleable.CustomView2_paint_color, Color.RED);
        fillColor = array.getColor(R.styleable.CustomView2_fill_color, Color.BLUE);
        array.recycle();*/
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

    }

    /**
     * 构造函数的初始化，一般用于初始化一些数据，获取自定义属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        Log.d("hh", "onMeasure widthSize = " + widthSize + ", widthModel = " + widthModel);
        Log.d("hh", "onMeasure heightSize = " + heightSize + ", heightModel = " + heightMode);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("hh", "onSizeChanged");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("hh", "onLayout");

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
        Log.d("hh", "layout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("hh", "onDraw");
        shapeDrawable.draw(canvas);
    }

}
