package com.example.administrator.shadowapplication.service_demo.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.service_demo.service.BinderServiceClass
import com.example.administrator.shadowapplication.service_demo.service.IntentServiceClass
import com.example.administrator.shadowapplication.service_demo.service.StartServiceClass
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var connection: ServiceConnection

    override fun onClick(v: View?) {
        if (v == null)
            return
        when (v.id) {
            R.id.tvStartService -> {
                val intent = Intent(this, StartServiceClass::class.java)
                startService(intent)
            }

            R.id.tvBindService -> {
                val intent = Intent(this, BinderServiceClass::class.java)
                bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }

            R.id.tvStopService -> {
                val intent = Intent(this, StartServiceClass::class.java)
                stopService(intent)
            }

            R.id.tvUnBindService -> {
                unbindService(connection)
            }

            R.id.tvIntentService -> {
                val intent = Intent(this, IntentServiceClass::class.java)
                intent.putExtra("shadow", "hello shadow,启动intent service")
                startService(intent)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        tvStartService.setOnClickListener(this)
        tvBindService.setOnClickListener(this)
        tvStopService.setOnClickListener(this)
        tvUnBindService.setOnClickListener(this)
        tvIntentService.setOnClickListener(this)
        connection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                if (service != null) {
                    (service as BinderServiceClass.MyBinder).service.getServiceContent()
                }
            }

        }
    }
}
