package com.example.administrator.shadowapplication.scrollview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.example.administrator.shadowapplication.R;

public class TestScrollviewActivity extends AppCompatActivity {

    int screenHeight;
    private LinearLayout llHide;
    private LinearLayout layoutContent;

    private BehaviorScrollview.OnStateChangedListener onStateChangedListener = new BehaviorScrollview.OnStateChangedListener() {
        @Override
        public void onChanged(boolean isShow) {
            if (isShow) {
                showTools();
               // ToastUtil.showMsg("展示展示展示展示展示展示展示");
            } else {
                hideTools();
                //ToastUtil.showMsg("隱藏隱藏隱藏隱藏隱藏隱藏隱藏隱藏");
            }
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scrollview);
        llHide = findViewById(R.id.hideLayout);
        layoutContent = findViewById(R.id.layoutContent);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenHeight = dm.heightPixels;
        layoutContent.setMinimumHeight(screenHeight - dp2px(this, 92)); //最小的宽度是全屏

        LinearLayout view = findViewById(R.id.layout_bottom);
        BehaviorScrollview scaleDownShowFab = BehaviorScrollview.from(view);
        scaleDownShowFab.setOnStateChangedListener(onStateChangedListener);
    }

    /**
     * 显示工具栏
     */
    private void showTools() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(llHide, "translationY", llHide.getHeight(), 0);
        anim.setDuration(200);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                llHide.setVisibility(View.VISIBLE);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

    }

    /**
     * 隐藏工具栏
     */
    private void hideTools() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(llHide, "translationY", 0, llHide.getHeight());
        anim.setDuration(200);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //llHide.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                llHide.setVisibility(View.GONE);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }


    public static int dp2px(Context context, int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }
}
