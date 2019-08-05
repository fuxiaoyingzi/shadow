package com.example.administrator.shadowapplication.process_communication

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_second_process.*

class SecondProcessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_process)
        content.text = intent.getStringExtra("hh")

        tvCurrentProcessName.text = "当前进程的名称${getProcessName(this)}"
    }


    fun getProcessName(cxt: Context): String? {
        val pid = android.os.Process.myPid()
        val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses ?: return null
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }
}
