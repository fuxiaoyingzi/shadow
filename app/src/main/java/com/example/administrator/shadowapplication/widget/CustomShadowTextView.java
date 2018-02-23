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
 *     time   : 2018/2/6
 *     desc   : 阴影发光文字效果
 * </pre>
 */


public class CustomShadowTextView extends View {
    private Paint mPaint;

    public CustomShadowTextView(Context context) {
        this(context, null);
    }

    public CustomShadowTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomShadowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomShadowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    /**
     * shader	 layer 有 两 种 类 型 ： View.LAYER_TYPE_SOFTWARE 和
     * View.LAYER_TYPE_HARDWARE， layer 的默认类型为 LAYER_TYPE_HARDWARE， 但阴影只能在
     * View.LAYER_TYPE_SOFTWARE 环 境 下 工 作 ， 所 以 ， 我 们 需 要 调 用 View 类 的 public	 void
     * setLayerType(int	 layerType,	 Paint	 paint) 方 法 为 Paint 对 象 指 定 层 的 类 型 ：
     * setLayerType(View.LAYER_TYPE_SOFTWARE,	paint)。
     *
     * @param context
     */
    public void initView(Context context) {
        mPaint = new Paint();
        mPaint.setTextSize(50);
        mPaint.setAntiAlias(true);
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint); //修改layoutType 为softWare
        mPaint.setShadowLayer(10,5,5,Color.RED);
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("hello shadow", 20, 50, mPaint);
    }
}
