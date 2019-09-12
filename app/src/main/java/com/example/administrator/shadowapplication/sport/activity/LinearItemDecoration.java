package com.example.administrator.shadowapplication.sport.activity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by yjwfn on 2016/4/29.
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private Paint   mLinePaint;
    private int     mDividerHeight;

    public LinearItemDecoration(@ColorInt int color, int dividerHeight){
        mLinePaint = new Paint();
        mLinePaint.setColor(color);
        mDividerHeight = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childIndex ;
        View childView;

        int left, top, right, bottom;
        int height = mDividerHeight;
        for(childIndex = 0; childIndex < parent.getChildCount(); childIndex++){
            childView = parent.getChildAt(childIndex);
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            top = childView.getBottom();
            bottom = top + height;
            c.drawRect(left, top, right, bottom, mLinePaint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mDividerHeight;
    }
}
