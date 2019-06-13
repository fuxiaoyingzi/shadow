package com.example.administrator.shadowapplication.jpush;

import android.app.Application;
import android.util.Log;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化
 */
public class ExampleApplication extends Application {
    private static final String TAG = "JPush";

    @Override
    public void onCreate() {    	     
    	 Log.d(TAG, "[ExampleApplication] onCreate");
         super.onCreate();

        JAnalyticsInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JAnalyticsInterface.init(this);
    }
}
