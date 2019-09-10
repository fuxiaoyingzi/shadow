package com.example.administrator.shadowapplication.anim

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_lottie.*

class LottieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        /**
        xml 属性字段说明
        1.lottie_fileName：在app/src/main/assets目录下的动画json文件名。
        2.lottie_loop：动画是否循环播放，默认不循环播放。
        3.lottie_autoPlay：动画是否自动播放，默认不自动播放。
        4.lottie_imageAssetsFolder：动画所依赖的图片目录，在app/src/main/assets/目录下的子目录，该子目录存放所有图片。

        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("hello-world.json");//在assets目录下的动画json文件名。
        animationView.loop(true);//设置动画循环播放
        animationView.setImageAssetsFolder("images/");//assets目录下的子目录，存放动画所需的图片
        animationView.playAnimation();//播放动画

        动画缓存策略，默认不缓存
        None 无缓存
        Weak 弱引用缓存
        Strong 强引用缓存




         */
        //第二种方法 加载json动画
        //lottieView.setAnimation("load.json")

        //lottieView.setAnimation("LogoSmall.json", LottieAnimationView.CacheStrategy.Weak)
        //lottieView.setAnimation("LottieLogo1.json", LottieAnimationView.CacheStrategy.Weak)
        //lottieView.setAnimation("AndroidWave.json", LottieAnimationView.CacheStrategy.Weak)
        lottieView.setAnimation("load2.json", LottieAnimationView.CacheStrategy.Weak)
        lottieView.loop(true)
        lottieView.playAnimation()

        lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                Log.d("hh", "onAnimationRepeat")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.d("hh", "onAnimationEnd")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.d("hh", "onAnimationCancel")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.d("hh", "onAnimationStart")
            }

        })


        //动画状态监听回调
        lottieView.addAnimatorUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                Log.d("hh", "onAnimationUpdate -- ${animation?.values}")
            }
        })

        lottieView.progress = 0.5f


        /**
         * //progress范围0~1f，设置动画进度
         * animationView.setProgress(0.5f);
         * ...
         *
         * // 自定义动画时长，此处利用ValueAnimator值动画来实时更新AnimationView的进度来达到控制动画时长。
         * ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f) .setDuration(500);
         * animator.addUpdateListener(animation -> { animationView.setProgress(animation.getAnimatedValue()); });
         * animator.start();//启动动画
         * ...
         *
         * animationView.cancelAnimation();//取消动画
         */


        //一般用于加载网络json动画
        /* LottieCompositionFactory.fromAsset(this,"City.json").addListener { result ->
             if (result != null){
                 lottieView.setComposition(result)
                 lottieView.repeatCount = LottieDrawable.INFINITE
                 lottieView.playAnimation()
             }
         }*/
        val drawable = resources.getDrawable(R.drawable.ic_launchers)
        drawable.intrinsicHeight
        drawable.intrinsicWidth
    }
}
