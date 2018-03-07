package com.example.administrator.shadowapplication.crash_log;

import android.util.Log;

import com.example.administrator.shadowapplication.base.MyApp;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/7
 *     desc   :log工具类
 * </pre>
 */

public class LogUtil {
    public static void v(String tag, String msg) {
        if (MyApp.getInstance().isDebugModel()){
             Log.v(tag,msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (MyApp.getInstance().isDebugModel()){
            Log.v(tag,msg,tr);
        }
    }

    public static void d(String tag, String msg) {
        if (MyApp.getInstance().isDebugModel()){
            Log.d(tag,msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (MyApp.getInstance().isDebugModel()){
            Log.d(tag,msg,tr);
        }
    }

    public static void i(String tag, String msg) {
        if (MyApp.getInstance().isDebugModel()){
            Log.i(tag,msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (MyApp.getInstance().isDebugModel()){
            Log.i(tag,msg,tr);
        }
    }

    public static void w(String tag, String msg) {
        if (MyApp.getInstance().isDebugModel()){
            Log.w(tag,msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (MyApp.getInstance().isDebugModel()){
            Log.w(tag,msg,tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (MyApp.getInstance().isDebugModel()){
            Log.w(tag,tr);
        }
    }


    public static void e(String tag, String msg) {
        if (MyApp.getInstance().isDebugModel()){
            Log.e(tag,msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (MyApp.getInstance().isDebugModel()){
            Log.e(tag,msg,tr);
        }
    }

}
