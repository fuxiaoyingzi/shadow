package com.example.administrator.shadowapplication.open_gl

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class OpenGlActivity : AppCompatActivity() {

    private lateinit var mGLView: GLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_open_gl_acticity)
        mGLView = MyGLSurfaceView(this);
        setContentView(mGLView);
    }
}


