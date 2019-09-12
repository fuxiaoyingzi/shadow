package com.example.administrator.shadowapplication.material_design.behavior;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author : shadow
 * Desc :
 * Date :2018/12/16/016
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public ScrollBehavior() {
    }

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //这里的返回值表明这次滑动我们要不要关心，我们要关心什么样的滑动？当然是y轴方向上的。
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
          return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //那如何让它滑动起来呢？还是要看onNestedPreScroll的实现
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled);
    }

    //这里是处理fling动作的
    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
        ((NestedScrollView) child).fling((int)velocityY);
        return true;
    }
}
