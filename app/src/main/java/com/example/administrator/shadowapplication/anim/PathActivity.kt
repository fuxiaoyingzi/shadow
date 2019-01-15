package com.example.administrator.shadowapplication.anim

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_path.*

/**
 * path动画
 * 贝塞尔曲线
 */
class PathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)
        val path = Path()
        path.moveTo(0f, 120f)
        path.lineTo(300f, 120f)
        path.lineTo(0f, 420f)
        path.lineTo(200f, 0f)
        path.lineTo(300f, 420f)
        path.close()

        //五角星路径动画
        pathAnimator.setOnClickListener {
            val anim = ObjectAnimator.ofFloat(pathAnimator, "translationX", "translationY", path)
            anim.duration = 5000
            anim.start()
        }
    }
}
