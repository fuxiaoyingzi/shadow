package com.example.administrator.shadowapplication.open_gl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author 付影影
 * @desc
 * @date 2019/5/7
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    /**
     * http://hukai.me/android-training-course-in-chinese/graphics/opengl/environment.html
     * 在一个使用OpenGL ES的应用中，一个GLSurfaceView.Renderer类的实现（或者我们将其称之为渲染器），
     * 正是事情变得有趣的地方。该类会控制和其相关联的GLSurfaceView，具体而言，它会控制在GLSurfaceView上绘制的内容。
     * 在渲染器中，一共有三个方法会被Android系统调用，以此来明确要在GLSurfaceView上绘制的内容以及如何绘制：
     *
     *     onSurfaceCreated()：调用一次，用来配置View的OpenGL ES环境。
     *     onDrawFrame()：每次重新绘制View时被调用。
     *     onSurfaceChanged()：如果View的几何形态发生变化时会被调用，例如当设备的屏幕方向发生改变时。
     * @param unused
     * @param config
     */

    private Triangle mTriangle;
    private Square   mSquare;

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
