package com.example.administrator.shadowapplication.anim;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.graphics.drawable.ArgbEvaluator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Author : shadow
 * Desc :
 * Date :2018/6/1/001
 */

public class ValueAnimatorUtil {
    /**
     * 向上移动
     * @param view
     * @param measure 是否需要测量获取高度
     * @param duration 动画的时间
     * @param animatorListenerAdapter 动画监听
     */
    public static void translationSlideUp(View view, boolean measure,int duration, AnimatorListenerAdapter animatorListenerAdapter ){
        float startPosition = measure ? measureHeight(view) : view.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationY",0,-startPosition);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        if (animatorListenerAdapter != null){
            animator.addListener(animatorListenerAdapter);
        }
        animator.start();
    }

    /**
     * 向上移动
     * @param view
     * @param measure 是否需要测量获取高度
     * @param duration 动画的时间
     * @param animatorListenerAdapter 动画监听
     */
    public static void translationSlideDown(View view, boolean measure,int duration, AnimatorListenerAdapter animatorListenerAdapter ){
        float startPosition = measure ? measureHeight(view) : view.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationY",0,startPosition);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        if (animatorListenerAdapter != null){
            animator.addListener(animatorListenerAdapter);
        }
        animator.start();
    }

    /**
     * 透明度动画
     * @param view
     * @param duration 动画的时间
     * @param animatorListenerAdapter 动画监听
     */
    public static void alphaFadeIn(View view, int duration, AnimatorListenerAdapter animatorListenerAdapter ){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"alpha",0,1);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        if (animatorListenerAdapter != null){
            animator.addListener(animatorListenerAdapter);
        }
        animator.start();
    }

    /**
     * 透明度动画
     * @param view
     * @param duration 动画的时间
     * @param animatorListenerAdapter 动画监听
     */
    public static void alphaFadeOut(View view, int duration, AnimatorListenerAdapter animatorListenerAdapter ){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"alpha",1,0);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        if (animatorListenerAdapter != null){
            animator.addListener(animatorListenerAdapter);
        }
        animator.start();
    }

    @SuppressLint("RestrictedApi")
    public static void animateBackgourdColor(View view, int duration, int startColor, int endColor, AnimatorListenerAdapter animatorListenerAdapter){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startColor,endColor);
        valueAnimator.setEvaluator(ArgbEvaluator.getInstance());
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                view.setBackgroundColor(color);
            }
        });
        if (animatorListenerAdapter != null){
            valueAnimator.addListener(animatorListenerAdapter);
        }
        valueAnimator.start();
    }



    private static int measureHeight(View anchor) {
        anchor.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return anchor.getMeasuredHeight();
    }
}
