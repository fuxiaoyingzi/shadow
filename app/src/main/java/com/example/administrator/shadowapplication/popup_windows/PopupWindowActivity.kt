package com.example.administrator.shadowapplication.popup_windows

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.shadowapplication.R

class PopupWindowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)
        val fragment = PopupFragment()
        supportFragmentManager.beginTransaction().add(R.id.constants,fragment).commit()
    }
}
