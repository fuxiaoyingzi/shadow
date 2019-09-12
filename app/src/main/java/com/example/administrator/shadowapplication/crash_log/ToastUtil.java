package com.example.administrator.shadowapplication.crash_log;

import androidx.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.shadowapplication.base.MyApp;


/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/7
 *     desc   : 吐司封装工具
 * </pre>
 */
public class ToastUtil {
    public static void showMsg(String msg) {
         Toast.makeText(MyApp.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showMsg(@StringRes int resId) {
        Toast.makeText(MyApp.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showMsgLong(String msg) {
        Toast.makeText(MyApp.getInstance().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showMsgLong(@StringRes int resId) {
        Toast.makeText(MyApp.getInstance().getApplicationContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showCustomMsg(View view){
        Toast toast = new Toast(MyApp.getInstance().getApplicationContext());
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showCustomMsg(View view,int duration){
        Toast toast = new Toast(MyApp.getInstance().getApplicationContext());
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(duration);
        toast.show();
    }

    public static void showCustomMsg(View view,int duration,int gravity){
        Toast toast = new Toast(MyApp.getInstance().getApplicationContext());
        toast.setView(view);
        toast.setGravity(gravity,0,0);
        toast.setDuration(duration);
        toast.show();
    }


}
