package com.example.administrator.shadowapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_constraint.*

/**
 * 约束 布局
 */
class ConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
        itemView.setOnClickListener {
            startActivity(Intent(this@ConstraintActivity, ConstraintActivity2::class.java))
        }
    }
}
