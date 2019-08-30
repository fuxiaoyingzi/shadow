package com.example.administrator.shadowapplication.constraint

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_constraint.*


/**
 * 约束 布局
 */
class ConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
        photo.setOnClickListener {
            startActivity(Intent(this@ConstraintActivity, ConstraintActivity2::class.java))
        }

        rating.setOnClickListener {
            startActivity(Intent(this@ConstraintActivity, ConstraintActivity3::class.java))
        }

        rightPhone.setOnClickListener {
            startActivity(Intent(this@ConstraintActivity, ConstraintActivity4::class.java))
        }


    }


}
