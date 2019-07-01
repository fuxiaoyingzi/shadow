package com.example.administrator.shadowapplication.Drawable

import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.RotateDrawable
import android.graphics.drawable.ScaleDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_android_drawable2.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AndroidDrawableActivity : AppCompatActivity() {


    lateinit var clipDrawable: ClipDrawable
    lateinit var scaleDrawable: ScaleDrawable
    lateinit var rotateDrawable: RotateDrawable
    var reverse = false

    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            msg?.what?.let { mImageView.setImageLevel(it % 5) }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_drawable2)
        for (i in 0..15) {
            mHandler.sendEmptyMessageDelayed(i, (1000 * i).toLong())
        }

        initScaleDrawable()
        initClipDrawable()
        initRotateDrawable()
        initTransition()
    }

    private fun initTransition() {
        var transitionDrawable = img.background as TransitionDrawable

        Observable.interval(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: Long) {
                        if (!reverse) {
                            transitionDrawable.startTransition(3000)
                            reverse = true
                        } else {
                            transitionDrawable.reverseTransition(3000)
                            reverse = false
                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

    private fun initRotateDrawable() {
        mRotateDrawable.setOnClickListener {
            rotateDrawable = mRotateDrawable.background as RotateDrawable
            rotateDrawable.level = 0
            var curLevel = 0

            Observable.interval(50, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        rotateDrawable.level = curLevel
                        curLevel += 200
                        if (curLevel >= 10000) {
                            curLevel = 0
                        }
                        Log.e("gpj", "level ${curLevel}")
                    }


        }
    }

    private fun initClipDrawable() {
        mClipDrawable.setOnClickListener {
            var curLevel = 0
            clipDrawable = mClipDrawable.background as ClipDrawable
            clipDrawable.level = 0
            Observable.interval(50, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        clipDrawable.level = curLevel
                        curLevel += 200
                        if (curLevel >= 10000) {
                            curLevel = 0
                        }
                        Log.e("gpj", "level ${curLevel}")
                    }
        }
    }

    private fun initScaleDrawable() {
        button.setOnClickListener {

            var curLevel = 0
            scaleDrawable = button.background as ScaleDrawable
            scaleDrawable.level = 0

            Observable.interval(200, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        scaleDrawable.level = curLevel
                        curLevel += 200
                        if (curLevel >= 10000) {
                            curLevel = 0
                        }
                        Log.e("gpj", "level ${curLevel}")
                    }
        }


    }
}
