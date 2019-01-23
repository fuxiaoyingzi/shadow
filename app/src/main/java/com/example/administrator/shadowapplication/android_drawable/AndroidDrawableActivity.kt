package com.example.administrator.shadowapplication.android_drawable

import android.graphics.PixelFormat
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.TransitionDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_android_drawable.*

class AndroidDrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_drawable)
       /* val mTransitionDraw = transitionDraw.background as TransitionDrawable
        mTransitionDraw.startTransition(1000)

        val clipDrawable = clipDrawable.background as ClipDrawable
        clipDrawable.level = 5000 //level 取值范围0 - 10000*/


    }


}
