package com.example.administrator.shadowapplication.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;

public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivLauncherIcon;
    private Button move, scale, rotate, alpha, valueAnima, animationUtil;
    private TextView tvValueAnim;
    private PointView mPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        ivLauncherIcon = findViewById(R.id.iv_launcher_icon);
        move = findViewById(R.id.btn_move);
        scale = findViewById(R.id.btn_scale);
        rotate = findViewById(R.id.btn_rotate);
        alpha = findViewById(R.id.btn_alpha);
        move.setOnClickListener(this);
        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        alpha.setOnClickListener(this);
        animationUtil = findViewById(R.id.util);
        animationUtil.setOnClickListener(this);
        findViewById(R.id.uti2).setOnClickListener(this);
        findViewById(R.id.btn_valueAnimator).setOnClickListener(this);
        findViewById(R.id.value).setOnClickListener(this);
        findViewById(R.id.pointValue).setOnClickListener(this);
        tvValueAnim = findViewById(R.id.tvValueAnim);
        mPointView = findViewById(R.id.pointView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_move:
                ObjectAnimator.ofInt(new BtnWrapper(alpha), "width", 400).setDuration(500).start();
                break;
            case R.id.btn_scale:
                ValueAnimator valueAnimator = ObjectAnimator.ofInt(ivLauncherIcon, "backgroundColor", 0xffff0000, 0xff00ff00);
                valueAnimator.setDuration(500);
                valueAnimator.setEvaluator(new ArgbEvaluator());
                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                    }
                });

                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                break;
            case R.id.btn_rotate:
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(ivLauncherIcon, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(ivLauncherIcon, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(ivLauncherIcon, "scaleX", 1, 2.5f),
                        ObjectAnimator.ofFloat(ivLauncherIcon, "scaleY", 1, 1.5f),
                        ObjectAnimator.ofFloat(ivLauncherIcon, "alpha", 1, 0.5f, 1f)
                );
                set.setDuration(1000).start();
                break;
            case R.id.btn_alpha:
                ObjectAnimator.ofFloat(ivLauncherIcon, "translationY", ivLauncherIcon.getHeight() + 30).start();
                break;
            case R.id.btn_valueAnimator:
                ValueAnimator valueAnimator1 = ValueAnimator.ofInt(1, 500);
                valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    IntEvaluator evaluator = new IntEvaluator();

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //获取当前动画的进度 1- 500之间
                        int currentValue = (int) animation.getAnimatedValue();
                        Log.d("hh", "valueAnimator currentValue = " + currentValue);
                        float fraction = animation.getAnimatedFraction();
                        rotate.getLayoutParams().width = evaluator.evaluate(fraction, rotate.getWidth(), 500);
                        rotate.requestLayout();
                    }
                });
                valueAnimator1.setDuration(500).start();
                break;
            case R.id.util:
                ValueAnimatorUtil.translationSlideUp(ivLauncherIcon, false, 500, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ToastUtil.showMsg("上移动画结束了");
                    }
                });
                break;
            case R.id.uti2:
                ValueAnimatorUtil.translationSlideDown(ivLauncherIcon, false, 500, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ToastUtil.showMsg("下移动画结束了");
                    }
                });
                break;

            case R.id.value:
                ValueAnimator valueAnima = ValueAnimator.ofObject(new ChartEvaluator(), new Character('a'), new Character('z'));
                valueAnima.setDuration(5000);
                valueAnima.start();
                valueAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        char value = (char) animation.getAnimatedValue();
                        tvValueAnim.setText("测试:" + value);
                    }
                });
                break;
            case R.id.pointValue:
                mPointView.startAnimator();
                break;
        }

    }

    /**
     * 自定义 char类型 计算规格
     */
    public class ChartEvaluator implements TypeEvaluator<Character> {
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int result = (int) (startValue + fraction * (endValue - startValue));
            return (char) result;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ivLauncherIcon.clearAnimation();
    }

    /**
     * 通过包装对象，设置属性动画
     */
    class BtnWrapper {
        Button alpha;

        public BtnWrapper(Button alpha) {
            this.alpha = alpha;
        }

        public void setWidth(int width) {
            if (alpha != null) {
                alpha.getLayoutParams().width = width;
                alpha.requestLayout();
            }

        }

        public int getWidth() {
            if (alpha != null) {
                return alpha.getLayoutParams().width;
            } else {
                return 0;
            }

        }
    }
}
