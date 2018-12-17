package com.example.administrator.shadowapplication.material_design.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author : shadow
 * Desc : 自定义behavior
 * Date :2018/12/16/016
 */
public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d("hh", " SampleTitleBehavior onDependentViewChanged dependency.getY() = " + dependency.getY());
        Log.d("hh", " SampleTitleBehavior onDependentViewChanged child.getHeight() = " + child.getHeight());
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();
        Log.d("hh", "SampleTitleBehavior 滑动的距离 = " + y);
        child.setTranslationY(y);

        /*
        * 透明度设置
        * if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        * */
        return true;
    }
}
