package com.example.administrator.shadowapplication.android_http

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_down_web_page.*

/**
 * 下载网页源码 显示在webview中
 */
class DownWebPageActivity : AppCompatActivity() {
    var mHandler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down_web_page)

        DownWebPageThread("https://www.baidu.com", mWebView, mHandler).start()
    }
}
