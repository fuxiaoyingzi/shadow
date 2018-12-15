package com.example.administrator.shadowapplication.progress

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_progress.*
import android.content.Intent



/**
 * 测试进程保活方案，所谓保活就是指程序进入后台，不被系统杀死
 * 白色保活
 * 黑色保活
 * 灰色保活
 *
 */
class ProgressActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * 黑色唤醒广播的action
     */
    private val BLACK_WAKE_ACTION = "com.wake.black"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        tvWhite.setOnClickListener(this)
        tvBlack.setOnClickListener(this)
        tvGray.setOnClickListener(this)
        tvNormal.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null)
            return
        when (v.id) {
            R.id.tvWhite -> {  //系统正常的前台Service，白色保活手段
                val whiteIntent = Intent(applicationContext, WhiteService::class.java)
                startService(whiteIntent)
            }
            R.id.tvBlack -> { //拉帮结派，黑色保活手段，利用广播唤醒队友
                val blackIntent = Intent()
                blackIntent.action = BLACK_WAKE_ACTION
                sendBroadcast(blackIntent)
            }
            R.id.tvGray -> { //利用系统漏洞，灰色保活手段（API < 18 和 API >= 18 两种情况）
                val grayIntent = Intent(applicationContext, GrayService::class.java)
                startService(grayIntent)
            }
            R.id.tvNormal -> {//普通的后台进程
                val bgIntent = Intent(applicationContext, BackgroundService::class.java)
                startService(bgIntent)
            }
        }

    }
}
