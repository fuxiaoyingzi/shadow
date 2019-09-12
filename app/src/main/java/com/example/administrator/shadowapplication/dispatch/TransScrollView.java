package com.example.administrator.shadowapplication.dispatch;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author 付影影
 * @desc
 * @date 2019/8/29
 */
public class TransScrollView extends NestedScrollView {
    public TransparentView.TouchEventListener mListener;

    public TransScrollView(@NonNull Context context) {
        super(context);
    }

    public TransScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TransScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mListener != null && mListener.dispatchTouchEvent(ev)) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setListener(TransparentView.TouchEventListener listener) {
        this.mListener = listener;

    }
}
