package com.example.administrator.shadowapplication.timer

import android.os.Bundle
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.eventbus.TimerEvent
import kotlinx.android.synthetic.main.activity_custom_timer2.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CustomTimer2Activity : BaseTimeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_timer2)
        EventBus.getDefault().register(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getTimer(timerEvent: TimerEvent) {
        tvTime.text = timerEvent.timer
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this);
    }
}
