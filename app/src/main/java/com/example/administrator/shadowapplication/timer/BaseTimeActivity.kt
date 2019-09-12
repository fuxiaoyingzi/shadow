package com.example.administrator.shadowapplication.timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import com.example.administrator.shadowapplication.eventbus.TimerEvent
import com.example.administrator.shadowapplication.sp.SPConstant
import com.example.administrator.shadowapplication.sp.SharedManager
import kotlinx.android.synthetic.main.activity_base_time.*
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat


open class BaseTimeActivity : AppCompatActivity() {
    lateinit var baseContentView: RelativeLayout
    private var currentMillisUntilFinished: Long = 0 //剩余时间保存下来
    var timer: CountDownTimer? = null


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseContentView = LayoutInflater.from(this).inflate(R.layout.activity_base_time, null) as RelativeLayout
        super.setContentView(baseContentView)

        cancel.setOnClickListener {
            cleanTimer()
            currentMillisUntilFinished = 0
        }

        confirm.setOnClickListener {
            tvTimerLayout.visibility = View.GONE
            ToastUtil.showMsg("去支付")
        }

    }

    override fun onResume() {
        super.onResume()
        currentMillisUntilFinished = 0
        val surplusTime = SharedManager.getInstance().getLong(SPConstant.SP_KEY_COUNT_DOWN, 0) - System.currentTimeMillis()
        if (surplusTime > 1000) {
            restart(surplusTime)
        } else {
            tvTimerLayout.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        cleanTimer()
        SharedManager.getInstance().setValue(SPConstant.SP_KEY_COUNT_DOWN, System.currentTimeMillis() + currentMillisUntilFinished)
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
        centerTitle.text = title.toString() + ""
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
        centerTitle.setText(titleId)
    }

    override fun setContentView(layoutResID: Int) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null), null)
    }

    override fun setContentView(view: View) {
        super.setContentView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        contentParent.removeAllViews()
        if (params != null) {
            contentParent.addView(view, params)
        } else {
            contentParent.addView(view)
        }
    }


    /**
     * 开始倒计时
     */
    fun restart(startCount: Long) {
        initTimer(startCount)
        if (timer != null) {
            timer!!.start()
            tvTimerLayout.visibility = View.VISIBLE
        }
    }


    private fun cleanTimer() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
            tvTimerLayout.visibility = View.GONE
        }
    }

    private fun initTimer(startCount: Long) {
        //十分钟倒计时
        timer = object : CountDownTimer(startCount, 1000) {
            @SuppressLint("SimpleDateFormat")
            override fun onTick(millisUntilFinished: Long) {
                currentMillisUntilFinished = millisUntilFinished
                val format = SimpleDateFormat("mm:ss")
                val countDownTime = format.format(millisUntilFinished)
                val event = TimerEvent(countDownTime)
                EventBus.getDefault().post(event)
                tvCountdown.text = countDownTime
            }

            override fun onFinish() {
                ToastUtil.showMsg("结束")
                tvTimerLayout.visibility = View.GONE
                timer = null
                SharedManager.getInstance().setValue(SPConstant.SP_KEY_COUNT_DOWN, 0)
            }
        }
    }
}
