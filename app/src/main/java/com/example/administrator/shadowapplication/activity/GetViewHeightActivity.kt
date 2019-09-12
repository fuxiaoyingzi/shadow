package com.example.administrator.shadowapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_get_view_height.*

class GetViewHeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_view_height)
    }

    /**
     * 方法1 会被多次调用
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val viewHeight = tvGetViewHeight.measuredHeight
    }

    override fun onStart() {
        super.onStart()
        /*方法二 会被调用多次，当view数的状态发生变化时候或者view树内部view的状态发生变化，比如显示隐藏*/
        val viewTreeObserver = tvGetViewHeight.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener {
            val viewHeight = tvGetViewHeight.measuredHeight
        }

        /*方法三*/
        tvGetViewHeight.post {
            val viewHeight = tvGetViewHeight.measuredHeight
        }

    }
}
