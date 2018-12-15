package com.example.administrator.shadowapplication.progress;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author : shadow
 * Desc : 给 API >= 18 的平台上用的灰色保活手段
 * Date :2018/12/15/015
 *
 * @author 付小影子
 */
public class WakeGrayInnerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("hh", "WakeGrayInnerService->onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hh", "WakeGrayInnerService->onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("hh", "WakeGrayInnerService->onStartCommand");
        startForeground(WakeReceiver.WAKE_SERVICE_ID, new Notification());
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hh", "WakeGrayInnerService->onDestroy");
    }
}
