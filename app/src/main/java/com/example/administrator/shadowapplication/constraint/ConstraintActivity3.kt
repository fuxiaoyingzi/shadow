package com.example.administrator.shadowapplication.constraint

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_constraint3.*

class ConstraintActivity3 : AppCompatActivity() {


    private lateinit var firstconstraintSet: ConstraintSet
    private lateinit var secondconstraintSet: ConstraintSet
    private lateinit var constraintLayout: ConstraintLayout

    var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint3)

        constraintLayout = findViewById(R.id.activity_main_constraintlayout)
        /* firstconstraintSet = ConstraintSet()
         secondconstraintSet = ConstraintSet()

         firstconstraintSet.clone(constraintLayout)
         secondconstraintSet.clone(this, R.layout.activity_constraint4)

         start.setOnClickListener {
             if (flag) {
                 flag = false
                 TransitionManager.beginDelayedTransition(constraintLayout)// 动画效果
                 secondconstraintSet.applyTo(constraintLayout)
             } else {
                 flag = true
                 TransitionManager.beginDelayedTransition(constraintLayout)
                 firstconstraintSet.applyTo(constraintLayout)
             }
         };*/

        val one = ConstraintSet().apply {
            this.clone(this@ConstraintActivity3, R.layout.activity_constraint3)
        }

        val two = ConstraintSet().apply {
            this.clone(this@ConstraintActivity3, R.layout.activity_constraint4)
        }

        start.setOnToggleListener { toggle ->
            Log.d("hh", "toggle = $toggle")
            flag = !flag
            val constraintSet = if (toggle) two else one
            TransitionManager.beginDelayedTransition(constraintLayout)
            constraintSet.applyTo(constraintLayout)
        }


    }

    //扩展函数
    private fun View.setOnToggleListener(action: (Boolean) -> Unit) {

        this.setOnClickListener {
            action(true)
        }
        this.setOnClickListener(object : Toggle() {
            override fun onToggle(toggle: Boolean, view: View) {
                Log.d("hh","onToggle")
                action(toggle)
            }
        })
    }


    abstract class Toggle : View.OnClickListener {
        private var toggle = false


        final override fun onClick(v: View) {
            Log.d("hh","onClick")
            toggle = !toggle
            onToggle(toggle, v)
        }

        abstract fun onToggle(toggle: Boolean, view: View)
    }


}
