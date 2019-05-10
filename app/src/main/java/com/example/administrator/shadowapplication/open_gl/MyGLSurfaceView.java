package com.example.administrator.shadowapplication.open_gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * @author 付影影
 * @desc
 * @date 2019/5/7
 */
public class MyGLSurfaceView extends GLSurfaceView {
    /**
     * GLSurfaceView是一种比较特殊的View，我们可以在该View中绘制OpenGL ES图形，不过它自己并不做太多和绘制图形相关的任务。
     * 绘制对象的任务是由你在该View中配置的GLSurfaceView.Renderer所控制的
     */
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        this(context, null);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}
