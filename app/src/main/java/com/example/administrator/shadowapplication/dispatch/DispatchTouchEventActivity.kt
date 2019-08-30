package com.example.administrator.shadowapplication.dispatch

import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_dispatch_touchevent.*

/**
 * 事件分发
 */
class DispatchTouchEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch_touchevent)
        mTransparent.serListener { event -> mMap.dispatchTouchEvent(event) }

        mScrollView.setListener { event ->
            val rect = Rect()
            mTransparent.getLocalVisibleRect(rect)
            rect.contains(event?.x?.toInt()?:0,event?.y?.toInt()?:0)
        }
    }
}
