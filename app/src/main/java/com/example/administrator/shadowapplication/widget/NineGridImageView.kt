package com.gongchangtemai.myhome.widgets.view

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Author : shadow
 * Desc : 九宫格图片 点击效果
 * Date :2019/1/5/005
 */
class NineGridImageView  :ImageView {

    constructor(context: Context) : this(context,null) {}

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr:Int) :super(context,attrs,defStyleAttr){
        initView(context)
    }

    lateinit var mContext: Context
    private fun initView(context: Context) {
        mContext = context;
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null){
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val drawable = drawable
                    drawable?.mutate()?.setColorFilter(Color.GRAY,
                            PorterDuff.Mode.MULTIPLY)
                }

                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    val drawableUp = drawable
                    drawableUp?.mutate()?.clearColorFilter()
                }
            }
        }

        return super.onTouchEvent(event)
    }


     fun setImageUrl(url: String?) {
        if (url != null) {
            //GlideApp.with(mContext).load(url).centerCrop().into(this)
            Glide.with(mContext).load(url).into(this)
        }
    }
}