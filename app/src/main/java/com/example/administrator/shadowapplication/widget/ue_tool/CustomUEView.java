package com.example.administrator.shadowapplication.widget.ue_tool;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 自定义实现你的 View 的属性
 */
public class CustomUEView extends AppCompatTextView {

    private String moreAttribution;

    public CustomUEView(Context context) {
        super(context);
    }

    public CustomUEView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomUEView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getMoreAttribution() {
        return moreAttribution;
    }

    public void setMoreAttribution(String moreAttribution) {
        this.moreAttribution = moreAttribution;
    }
}
