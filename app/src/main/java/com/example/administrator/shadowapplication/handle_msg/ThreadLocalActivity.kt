package com.example.administrator.shadowapplication.handle_msg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.administrator.shadowapplication.R
import kotlin.concurrent.thread

class ThreadLocalActivity : AppCompatActivity() {

    private val mHandle = object : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_local)
        val mThreadLocal = ThreadLocal<Boolean>()
        mThreadLocal.set(true)
        Log.d("hh","UI Thread = "+mThreadLocal.get())//UI Thread = true

        thread {
            mThreadLocal.set(false)
            Log.d("hh","子线程1 = "+mThreadLocal.get()) //子线程1 = false
        }

        thread {
            Log.d("hh","子线程2 = "+mThreadLocal.get())//子线程2 = null
        }

        mHandle.post(object :Runnable{
            override fun run() {

            }

        })

        mHandle.sendEmptyMessage(14)


        thread {
            Looper.prepare()
            val threadHandle = Handler()
            Looper.loop()
            val looper = threadHandle.looper
            looper.quit()
            looper.quitSafely()
        }

    }
}
