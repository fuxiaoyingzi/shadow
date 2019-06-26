package com.example.administrator.shadowapplication.anim

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ArrayAdapter
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_tween_animation.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * 布局动画
 */
class TweenAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween_animation)
        val titleList = ArrayList<String>()
        for (i in 0..20) {
            titleList.add("shadow$i")
        }
        val mAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList)

        listView.adapter = mAdapter
        val lac = LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.alpha_animation))
        lac.order = LayoutAnimationController.ORDER_NORMAL
        listView.layoutAnimation = lac
        listView.startLayoutAnimation()

        listView.setOnItemClickListener { adapterView, view, i, l ->
            toast("hello shadow")
            val animation = imageView.background as AnimationDrawable
            animation.start()
        }

    }
}
