package com.example.administrator.shadowapplication.constraint

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_constraint.constraintLayout
import kotlinx.android.synthetic.main.activity_constraint5.*

class ConstraintActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint5)
        applyBtn.setOnClickListener {
            onApplyClick()
        }

        resetBtn.setOnClickListener {
            constraintUtil.reSetWidthAnim()
        }
    }

    private lateinit var constraintUtil: ConstraintUtil
    private fun onApplyClick() {
        constraintUtil = ConstraintUtil(constraintLayout)
        val begin = constraintUtil.beginWithAnim()

        begin.clear(R.id.button1, R.id.button2, R.id.button3)

        begin.Left_toLeftOf(R.id.button1, R.id.parent)

        begin.Top_toTopOf(R.id.button1, R.id.parent)

        begin.Left_toLeftOf(R.id.button2, R.id.parent)
        begin.Top_toBottomOf(R.id.button2, R.id.button1)
        begin.setMargin(R.id.button2, 0, 30, 0, 0)

        begin.Left_toLeftOf(R.id.button3, R.id.parent)
        begin.Top_toBottomOf(R.id.button3, R.id.button2)
        begin.setMargin(R.id.button3, 0, 30, 0, 0)


        begin.setWidth(R.id.button1, ConstraintSet.WRAP_CONTENT)
        begin.setWidth(R.id.button2, ConstraintSet.WRAP_CONTENT)
        begin.setWidth(R.id.button3, ConstraintSet.WRAP_CONTENT)
        begin.setHeight(R.id.button1, ConstraintSet.WRAP_CONTENT)
        begin.setHeight(R.id.button2, ConstraintSet.WRAP_CONTENT)
        begin.setHeight(R.id.button3, ConstraintSet.WRAP_CONTENT)

        begin.commit()
    }

}
