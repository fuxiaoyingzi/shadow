package com.example.administrator.shadowapplication.material_design.behavior;

import android.content.Context;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author : shadow
 * Desc :自定义behavior的第二种方法，根据另一个view的行为决定自己的行为
 * 定义的view监听另一个view的状态变化，如大小位置显示状态等
 * Date :2018/3/26/026
 */

public class FooterBehaviorAppbar extends CoordinatorLayout.Behavior<View> {
    public FooterBehaviorAppbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 用来返回我们需要关心的类
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * 根据我们所关心的view的变化来对我们设置的behavior的view进行一系列处理
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getY());
        child.setTranslationY(translationY);
        return true;
    }
}
