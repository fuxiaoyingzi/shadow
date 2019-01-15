package com.example.administrator.shadowapplication.anim

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_lottie.*

class LottieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        //第二种方法 加载json动画
        lottieView.setAnimation("City.json")
        lottieView.loop(true)
        lottieView.playAnimation()
        //一般用于加载网络json动画
       /* LottieCompositionFactory.fromAsset(this,"City.json").addListener { result ->
            if (result != null){
                lottieView.setComposition(result)
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()
            }
        }*/
    }
}
