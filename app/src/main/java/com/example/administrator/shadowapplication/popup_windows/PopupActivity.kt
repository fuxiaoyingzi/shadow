package com.example.administrator.shadowapplication.popup_windows

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_popupwindows.*
import org.jetbrains.anko.toast


class PopupActivity : AppCompatActivity() {
    private lateinit var popupWindow: PopupWindow
    private var popupWindowX: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupwindows)
    }

    override fun onResume() {
        super.onResume()
        tvTopWindow.setOnClickListener {
            //不滚动 popup
            /* val view = LayoutInflater.from(this).inflate(R.layout.popupwindows_top_layout, null, false)
             popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
             view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
             val popupWidth = view.getMeasuredWidth()
             val popupHeight = view.getMeasuredHeight()
             val location = IntArray(2)

             popupWindow.setBackgroundDrawable(BitmapDrawable())
             popupWindow.isOutsideTouchable = false
             popupWindow.isFocusable = false
             // 获得位置
             tvTopWindow.getLocationOnScreen(location)
             popupWindowX = location[0] + tvTopWindow.getWidth() / 2 - popupWidth / 2
             popupWindow.showAtLocation(tvTopWindow, Gravity.NO_GRAVITY, popupWindowX, location[1] - popupHeight)*/

            //滚动 popup
            showScrollPopup()
        }

        tv2.setOnClickListener {
            toast("hello shadow!!")
        }

    }

    var popWidth: Int = 0
    var popHeight: Int = 0

    var orginalX: Int = 0
    var originalY: Int = 0

    /**
     * 跟着滚动的popupWindow
     */
    private fun showScrollPopup() {
        val view = LayoutInflater.from(this).inflate(R.layout.popupwindows_top_layout, null, false)
        popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val popupWidth = view.measuredWidth
        val popupHeight = view.measuredHeight


        popupWindow.setBackgroundDrawable(BitmapDrawable())
        popupWindow.isOutsideTouchable = false
        popupWindow.isFocusable = false

        val location = IntArray(2)
        tvTopWindow.getLocationOnScreen(location)
        popupWindow.showAtLocation(tvTopWindow, Gravity.NO_GRAVITY, location[0] + tvTopWindow.measuredWidth / 2 - popupWidth / 2, location[1] - popupHeight);

        orginalX = location[0] + tvTopWindow.measuredWidth / 2 - popupWidth / 2
        originalY = location[1] - popupHeight

        popWidth = popupWidth
        popHeight = popupHeight

        scroll.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = scroll.scrollY
            val currentY = originalY - scrollY
            popupWindow.update(orginalX, currentY, popWidth, popHeight)
        }
    }
}
