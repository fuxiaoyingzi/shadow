package com.example.administrator.shadowapplication.service_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.shadowapplication.crash_log.LogUtil;

/**
 * Author : shadow
 * Desc : startService
 * Date :2018/11/7/007
 *
 * @author 付小影子
 */
public class StartServiceClass extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("hh", "StartServiceClass -- onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("hh", "StartServiceClass -- onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d("hh", "StartServiceClass -- onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        LogUtil.d("hh", "StartServiceClass -- onDestroy");
        super.onDestroy();
    }
}
