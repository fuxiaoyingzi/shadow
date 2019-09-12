package com.example.administrator.shadowapplication.handle_msg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TimeUtils
import com.example.administrator.shadowapplication.R
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.newSingleThreadContext
import rx.internal.schedulers.CachedThreadScheduler
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 *线程池
 */
class ThreadPoolActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)


    }
}
