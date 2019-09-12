package com.example.administrator.shadowapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.CustomShadowTextView;

/**
 * 解决在onCreate中获取view的高度宽度为0的情况
 */
public class CustomShaderTextViewActivity extends AppCompatActivity {


    private CustomShadowTextView shadowTextView;//阴影文字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_sharde_text_view);
        shadowTextView = (CustomShadowTextView) findViewById(R.id.shadowText);
        //第二种方法
        shadowTextView.post(new Runnable() {
            @Override
            public void run() {
                int width = shadowTextView.getWidth();
                int height = shadowTextView.getHeight();
                Log.d("hh", "onCreate post width = " + width + "---height = " + height);
            }
        });
    }

    /**
     * 第一种方法：此时view已经初始化完毕，宽高已经准备好，所以此时获取没问题
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //activity在得到焦点或失去焦点的时候都会被调用,
        if (hasFocus) {
            int width = shadowTextView.getMeasuredWidth();
            int height = shadowTextView.getMeasuredHeight();
            Log.d("hh", "onWindowFocusChanged MeasuredWidth = " + width + "---MeasuredHeight = " + height);
        }

    }


    /**
     * 第三种方法：ViewTreeObserver众多回调可以实现获取view的高度宽度
     * 比如 OnGlobalLayoutListener这个接口，当view树的状态发生改变，或者view树内部view的可见性发生改变，都会频繁调用此接口
     */
    @Override
    protected void onStart() {
        super.onStart();
        ViewTreeObserver treeObserver = shadowTextView.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                shadowTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width = shadowTextView.getMeasuredWidth();
                int height = shadowTextView.getMeasuredHeight();
                Log.d("hh", "onWindowFocusChanged MeasuredWidth = " + width + "---MeasuredHeight = " + height);
            }
        });
    }

    private Handler myHandle;
    private Looper myLooper;

    /**
     * 子线程中不能创建handle对象，因为handle主要依赖于looper，而子线程中并没有创建looper对象
     * main线程中默认创建looper对象，如果再创建反而会出错
     */
    public void startHandle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ///必须调用Looper的prepare方法为当前线程创建一个Looper对象，然后启动循环
                //prepare方法中实质是给ThreadLocal对象创建了一个Looper对象
                //如果当前线程已经创建过Looper对象了，那么会报错
                Looper.prepare();
                myHandle = new Handler();
                //获取Looper对象
                myLooper = Looper.myLooper();
                //启动消息循环
                Looper.loop();
                //在适当的时候退出Looper的消息循环，防止内存泄漏
                myLooper.quit();

            }
        }).start();
    }
}
