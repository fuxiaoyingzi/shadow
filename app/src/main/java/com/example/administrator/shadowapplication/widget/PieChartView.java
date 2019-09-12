package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.shadowapplication.widget.bean.PieData;

import java.util.List;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/24
 *     desc   :自定义饼图
 * </pre>
 */


public class PieChartView extends View {
    private int[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA, Color.YELLOW};
    private Paint mPaint;
    private List<PieData> pieDataList;
    private float mStartAngle = 0;

    private int mWidth, mHeight;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs, defStyleAttr);
    }

    public void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
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
        Log.d("hh", "onDraw ");
        if (pieDataList == null) {
            return;
        }
        Log.d("hh", "onDraw " + pieDataList.size());
        float currentAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2); //移动到中心点
        float r = (float) ((Math.min(mWidth, mHeight)) / 2 * 0.8); //饼的半径
        RectF rectF = new RectF(-r, -r, r, r); //范围

        for (int i = 0; i < pieDataList.size(); i++) {
            PieData pieData = pieDataList.get(i);
            mPaint.setColor(pieData.getColor()); //填充的颜色
            canvas.drawArc(rectF, currentAngle, pieData.getAngle(), true, mPaint);
            currentAngle += pieData.getAngle();
        }
    }

    public float getmStartAngle() {
        return mStartAngle;
    }

    public void setmStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
        requestLayout();
    }

    public void setPieDataList(List<PieData> pieDataList) {
        this.pieDataList = pieDataList;
        initData(pieDataList);
        invalidate();
    }

    public void initData(List<PieData> pieDataList) {
        if (pieDataList == null || pieDataList.size() == 0)
            return;
        float totalValue = 0;
        for (int i = 0; i < pieDataList.size(); i++) { //遍历获取总数据，并且设置对应的颜色
            PieData pieData = pieDataList.get(i);
            totalValue += pieData.getValue();
            int j = i % colors.length;
            pieData.setColor(colors[j]);
        }

        float totalAngle = 0;
        for (int i = 0; i < pieDataList.size(); i++) { //根据总数值和自己的数值对比，得到百分比，已经对应的角度
            PieData pieData = pieDataList.get(i);
            float percentage = pieData.getValue() / totalValue;
            float angle = percentage * 360;
            pieData.setAngle(angle);
            pieData.setPercentage(percentage);
            totalAngle += angle;

        }

    }
}
