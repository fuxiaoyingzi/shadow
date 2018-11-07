package com.example.administrator.shadowapplication.android_http

import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Handler
import android.widget.ImageView
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * Author : shadow
 * Desc : 网页下载 线程
 * Date :2018/9/19/019
 */
class DownImageThread(val url: String, private val mImageView: ImageView, private val handle: Handler) : Thread() {
    override fun run() {
        super.run()
        val url = URL(url)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.connectTimeout = 5000
        connection.requestMethod = "GET"
        connection.doInput = true
        try {
            val inputStream = connection.inputStream
            val byte = ByteArray(2 * 1024)
            var fileOut: FileOutputStream? = null
            val imagePath = System.currentTimeMillis().toString()
            val parent: File
            var imageFile: File? = null
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                parent = Environment.getExternalStorageDirectory()
                imageFile = File(parent, imagePath)
                fileOut = FileOutputStream(imageFile)
            }
            var line: Int
            if (fileOut != null) {
                line = inputStream.read(byte)
                while (line != -1) {
                    fileOut.write(byte, 0, line)
                    line = inputStream.read(byte)
                }
                val bitmap = BitmapFactory.decodeFile(imageFile!!.absolutePath)
                handle.post {
                    ToastUtil.showMsg("hello shadow")
                    mImageView.setImageBitmap(bitmap)
                }
            }
            inputStream.close()
            if (fileOut != null) {
                fileOut.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}