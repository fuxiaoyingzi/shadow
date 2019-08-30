package com.example.administrator.shadowapplication.dispatch;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author 付影影
 * @desc
 * @date 2019/8/29
 */
public class TransparentView extends View {
    public interface TouchEventListener {
        boolean dispatchTouchEvent(MotionEvent event);
    }

    TouchEventListener mListener;

    public TransparentView(Context context) {
        super(context);
    }

    public TransparentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TransparentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TransparentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mListener != null) {
            return mListener.dispatchTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

    public void serListener(TouchEventListener listener) {
        this.mListener = listener;
    }
}
