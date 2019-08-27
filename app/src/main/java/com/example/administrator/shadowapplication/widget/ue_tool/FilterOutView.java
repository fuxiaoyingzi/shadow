package com.example.administrator.shadowapplication.widget.ue_tool;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 不能被UEtool选中的view
 */
public class FilterOutView extends AppCompatTextView {
    public FilterOutView(Context context) {
        super(context);
    }

    public FilterOutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterOutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
