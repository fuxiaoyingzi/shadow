package com.example.administrator.shadowapplication.progress;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author : shadow
 * Desc :用于其他进程来唤醒UI进程用的Service
 * Date :2018/12/15/015
 *
 * @author 付小影子
 */
public class WakeNotifyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hh", "WakeNotifyService->onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("hh", "WakeNotifyService->onBind");
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("hh", "WakeNotifyService->onStartCommand");
        //设置service为前台服务，提高优先级
        if (Build.VERSION.SDK_INT < 18) {
            //Android4.3以下 ，此方法能有效隐藏Notification上的图标
            startForeground(WakeReceiver.WAKE_SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT > 18 && Build.VERSION.SDK_INT < 25) {
            //Android4.3 - Android7.0，此方法能有效隐藏Notification上的图标
            Intent innerService = new Intent(this, WakeGrayInnerService.class);
            startService(innerService);
            startForeground(WakeReceiver.WAKE_SERVICE_ID, new Notification());
        } else {
            //Android7.1 google修复了此漏洞，暂无解决方法（现状：Android7.1以上app启动后通知栏会出现一条"正在运行"的通知消息）
            startForeground(WakeReceiver.WAKE_SERVICE_ID, new Notification());
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hh", "WakeNotifyService->onDestroy");
    }
}
