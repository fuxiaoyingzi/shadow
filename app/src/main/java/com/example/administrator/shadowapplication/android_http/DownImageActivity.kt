package com.example.administrator.shadowapplication.android_http

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_down_image.*

/**
 * 下载图片，保存到本地，显示
 */
class DownImageActivity : AppCompatActivity() {

    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down_image)
        DownImageThread("http://img05.tooopen.com/images/20150807/tooopen_sy_137117664389.jpg", mImage, handler).start()
    }
}
