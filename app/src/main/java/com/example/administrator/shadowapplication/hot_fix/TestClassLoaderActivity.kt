package com.example.administrator.shadowapplication.hot_fix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.administrator.shadowapplication.R

class TestClassLoaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_class_loader)
        var classLoader = TestClassLoaderActivity::class.java.classLoader
        while (classLoader != null) {
            Log.d("hh", "====${classLoader.toString()}===============\n")
            classLoader = classLoader.parent
        }
    }
}
