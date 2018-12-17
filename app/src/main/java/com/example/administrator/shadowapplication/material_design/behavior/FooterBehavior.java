package com.example.administrator.shadowapplication.material_design.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.example.administrator.shadowapplication.crash_log.ToastUtil;

/**
 * Author : shadow
 * Desc :定义view监听coordinatorLayout里面的滑动状态，做出相应的操作
 * Date :2018/3/26/026
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {
    private int directionChanger;//累计滑动的距离

    /**
     * 必须添加构造函数
     *
     * @param context
     * @param attrs
     */
    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 返回值表明这次滑动我们要不要关心，因为只关心垂直滑动，所以横向滑动不关心
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                       @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;

    }

    /**
     * 用于处理滑动
     *
     * @param coordinatorLayout
     * @param child             就是用于此行为的view
     * @param target
     * @param dx                水平滑动的距离
     * @param dy                垂直滑动的距离 向上滑动为正值，向下滑动为负值
     * @param consumed
     * @param type
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
       /* if (dy > 0 && directionChanger < 0 || dy < 0 && directionChanger > 0) {
            child.animate().cancel();
            directionChanger = 0;
        }

        directionChanger += dy;
        if (directionChanger > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else {
            show(child);
        }*/

    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dyConsumed > 0 && dyUnconsumed == 0 && child.isShown()) {
            // ToastUtil.showMsg("上滑中。。。");
            hide(child);
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            ToastUtil.showMsg("到边界了还在上滑。。。");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            //ToastUtil.showMsg("下滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0 && !child.isShown()) {
            //ToastUtil.showMsg("到边界了，还在下滑。。。");
            show(child);
        }
    }

    public void hide(View view) {
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
}
