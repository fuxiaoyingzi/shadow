package com.example.administrator.shadowapplication.progress;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author : shadow
 * Desc :普通后台的进程
 * Date :2018/12/15/015
 */
public class BackgroundService extends Service {
    private final static String TAG = "hh";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"BackgroundService onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"BackgroundService onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"BackgroundService onCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"BackgroundService onDestroy");

    }
}
