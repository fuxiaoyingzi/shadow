package com.example.administrator.shadowapplication.process_communication

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.process_communication.content_provider.ContentProviderActivity
import kotlinx.android.synthetic.main.activity_process_commonication.*


/**
 * 进程间通信
 */
class ProcessCommunicationActivity : AppCompatActivity() {

    companion object {
        lateinit var tvServiceReturnContent: TextView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_commonication)

        tvProcessName.text = "当前进程的名字：${getProcessName(this)}"

        tvBundle.setOnClickListener {
            val intent = Intent(this, SecondProcessActivity::class.java)
            intent.putExtra("hh", "我是从 ${getProcessName(this)}进程过来的")
            startActivity(intent)
        }

        tvMessenger.setOnClickListener {
            val intent = Intent(this, MessageService::class.java)
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
        }
        tvServiceReturnContent = findViewById(R.id.tvServiceReturn)

        tvContentProvider.setOnClickListener {
            startActivity(Intent(this@ProcessCommunicationActivity, ContentProviderActivity::class.java))
        }

    }


    class ClientHandle : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg == null)
                return
            when (msg.what) {
                1 -> {
                    tvServiceReturnContent.text = msg.data.getString("hh")
                }
            }
        }
    }

    val messenger = Messenger(ClientHandle())

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            if (p1 == null)
                return
            val messageService = Messenger(p1)
            val message = Message()
            message.what = 1
            val bundle = Bundle()
            bundle.putString("hh", "hello shadow，我是客户端，我给你发消息了")
            message.data = bundle
            message.replyTo = messenger
            messageService.send(message)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {

        }

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
