package com.example.administrator.shadowapplication.kotlin

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        hello.setOnClickListener {
            ToastUtil.showMsg("hello shadow")

        }

    }
}
