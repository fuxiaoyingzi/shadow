package com.example.administrator.shadowapplication.hot_fix

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.hot_fix.andfix.AndFixActivity
import kotlinx.android.synthetic.main.activity_hot_restore.*

class HotRestoreActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v == null)
            return
        when (v.id) {
            R.id.tvClassLoader -> {
                startActivity(Intent(this, TestClassLoaderActivity::class.java))
            }

            R.id.tvAndFix -> {
                startActivity(Intent(this, AndFixActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_restore)
        tvClassLoader.setOnClickListener(this)
        tvAndFix.setOnClickListener(this)
    }
}
