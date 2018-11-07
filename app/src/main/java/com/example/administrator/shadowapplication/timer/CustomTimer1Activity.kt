package com.example.administrator.shadowapplication.timer

import android.content.Intent
import android.os.Bundle
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_custom_timer1.*

class CustomTimer1Activity : BaseTimeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_timer1)
        tvStartCount.setOnClickListener {
            restart(60*10*1000)//开始倒计时

        }

        tvToCountTimer2.setOnClickListener {
            startActivity(Intent(this, CustomTimer2Activity::class.java))
        }
    }
}
