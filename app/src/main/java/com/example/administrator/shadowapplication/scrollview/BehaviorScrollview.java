package com.example.administrator.shadowapplication.scrollview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

/**
 * Author : shadow
 * Desc :自定义behavior的第二种方法，根据另一个view的行为决定自己的行为
 * 定义的view监听另一个view的状态变化，如大小位置显示状态等
 * Date :2018/3/26/026
 */

public class BehaviorScrollview extends CoordinatorLayout.Behavior<View> {
    public BehaviorScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * 用于处理滑动
     *
     * @param coordinatorLayout
     * @param child             就是用于此行为的view
     * @param target
     * @param dxConsumed        水平滑动的距离
     * @param dyConsumed        垂直滑动的距离 向上滑动为正值，向下滑动为负值
     * @param dxUnconsumed
     * @param type
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                               @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dyConsumed > 0 && dyUnconsumed == 0 && child.isShown()) {
            // ToastUtil.showMsg("上滑中。。。");
            hide(child);
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(true);
            }
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            //ToastUtil.showMsg("到边界了还在上滑。。。");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            //ToastUtil.showMsg("下滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0 && !child.isShown()) {
            //ToastUtil.showMsg("到边界了，还在下滑。。。");
            show(child);
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(false);
            }
        }
    }

    private void hide(View view) {
        ViewPropertyAnimator animator = view.animate()
                .translationY(view.getHeight())
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }

    private void show(View view) {
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();

    }

    // 外部监听显示和隐藏。
    public interface OnStateChangedListener {
        void onChanged(boolean isShow);
    }

    private OnStateChangedListener mOnStateChangedListener;

    public void setOnStateChangedListener(OnStateChangedListener mOnStateChangedListener) {
        this.mOnStateChangedListener = mOnStateChangedListener;
    }

    public static <V extends View> BehaviorScrollview from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("这个View不是CoordinatorLayout的子View");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof BehaviorScrollview)) {
            throw new IllegalArgumentException("这个View的Behaviro不是BehaviorScrollview");
        }
        return (BehaviorScrollview) behavior;
    }
}
