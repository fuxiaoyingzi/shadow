package com.example.administrator.shadowapplication.process_communication

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*

import com.example.administrator.shadowapplication.crash_log.ToastUtil

/**
 * @author 付影影
 * @desc
 * @date 2019/7/30
 */
class MessageService : Service() {

    private var mMessenger = Messenger(MessengerHandle())

    inner class MessengerHandle : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    ToastUtil.showMsg(msg.data.getString("hh"))
                    val clientMessenger = msg.replyTo
                    val message = Message()
                    message.what = 1;
                    val bundle = Bundle();
                    bundle.putString("hh", "hello shadow,我是服务端，我看到你的消息啦，我给你回消息了，这是我的进程名字${getProcessName(this@MessageService)
                    }")
                    message.data = bundle
                    clientMessenger.send(message)
                }

            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return mMessenger.binder
    }

    private fun getProcessName(cxt: Context): String? {
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
