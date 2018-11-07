package com.example.administrator.shadowapplication.android_http

import android.os.Handler
import android.webkit.WebView
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Author : shadow
 * Desc : 网页下载 线程
 * Date :2018/9/19/019
 */
class DownWebPageThread(val url: String, private val mWebView: WebView, private val handle: Handler) : Thread() {
    var str: String? = null
    override fun run() {
        super.run()
        val url = URL(url)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.connectTimeout = 5000
        connection.requestMethod = "GET"
        val stringBuilder = StringBuffer()

        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        str = reader.readLine()
        while (str != null) {
            stringBuilder.append(str)
            str = reader.readLine()
        }
        handle.post {
            ToastUtil.showMsg("hello shadow")
            mWebView.loadData(stringBuilder.toString(), "text/html;charset=utf-8", null)
        }
        reader.close()

    }
}