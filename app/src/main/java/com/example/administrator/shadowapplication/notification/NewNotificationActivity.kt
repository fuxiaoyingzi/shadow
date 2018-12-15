package com.example.administrator.shadowapplication.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.RemoteViews
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.activity.NotificationActivity
import kotlinx.android.synthetic.main.activity_notification2.*

class NewNotificationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification2)
        tvSendNormalNotify.setOnClickListener(this)
        tvSendProgressNotify.setOnClickListener(this)
        tvSendCustomNotify.setOnClickListener(this)
        tvDeleteNotify.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null)
            return
        when (v.id) {
            R.id.tvSendNormalNotify -> {
                getManager().notify(1, getNotifyBuilder()!!.build())
            }

            R.id.tvSendProgressNotify -> {
                Thread(Runnable {
                    val build = getNotifyBuilder()
                    for (i in 0..100) {

                        build!!.setProgress(100, i, false)
                        getManager().notify(2, build.build())
                        try {
                            Thread.sleep(300)
                        } catch (e: InterruptedException) {
                            Log.i("hh", "sleep failure")
                        }
                    }
                    build!!.setContentText("Download complete")
                            .setProgress(0, 0, false)
                    getManager().notify(2, build.build())
                }).start()


            }

            R.id.tvSendCustomNotify -> {
                val build = getBuilder()
                val remoteView = RemoteViews(packageName, R.layout.layout_custon_notification)
                remoteView.setTextViewText(R.id.title, "壹品仓-拉夏贝尔")
                remoteView.setTextColor(R.id.title, Color.RED)
                remoteView.setTextViewTextSize(R.id.title, TypedValue.COMPLEX_UNIT_SP, 16f)
                remoteView.setTextViewText(R.id.content, "全场不要钱，不要钱不要钱不要钱")
                remoteView.setImageViewResource(R.id.leftImg, R.mipmap.ic_launcher)
                remoteView.setImageViewResource(R.id.rightImg, R.drawable.ic_launchers)
                build.setContent(remoteView)
                /*val intent = Intent(this, NotificationActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_ONE_SHOT)
                build.setContentIntent(pendingIntent)*/
                getManager().notify(3, build.build())
            }

            R.id.tvDeleteNotify -> {
                getManager().cancel(3)
            }
        }
    }

    private fun getManager(): NotificationManager {
        return getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun getNotifyBuilder(): NotificationCompat.Builder? {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "channel_desc"
            channel.lightColor = Color.RED
            channel.setShowBadge(true)
            channel.setBypassDnd(true)
            channel.enableLights(true)
            getManager().createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("notify_title")
                .setContentText("hello content")
                .setTicker("壹品仓有新消息")
                .setLargeIcon(BitmapFactory.decodeResource(resources
                        , R.drawable.ic_launchers))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
    }

    private fun getBuilder(): NotificationCompat.Builder {
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= 26) {//兼容android8.0
            builder = NotificationCompat.Builder(this, "channel_id")
            val channel = NotificationChannel("channel_id", "channel_name",
                    NotificationManager.IMPORTANCE_DEFAULT)
            getManager().createNotificationChannel(channel)//添加通知渠道
        } else {
            builder = NotificationCompat.Builder(this)
        }
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setOngoing(true)
        return builder
    }

}
