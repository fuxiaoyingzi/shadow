package com.example.administrator.shadowapplication.handle_msg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_intent_setvice.*
import org.jetbrains.anko.toast

class IntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_setvice)
        Log.d("hh", "IntentServiceActivity onCreate")

        //启动多个任务，只有最后一个任务执行结束之后，才会退出服务
        tvStartService.setOnClickListener {
            toast("开启服务")
            val intentService = Intent(this@IntentServiceActivity, LocalIntentService::class.java)
            intent.putExtra("action_task", "hello shadow")
            startService(intentService)

            intent.putExtra("action_task", "hello shadow1")
            startService(intent)

            intent.putExtra("action_task", "hello shadow2")
            startService(intent)
        }


    }
}
