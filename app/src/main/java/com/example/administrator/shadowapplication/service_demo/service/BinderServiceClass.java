package com.example.administrator.shadowapplication.service_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.shadowapplication.crash_log.LogUtil;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;

/**
 * Author : shadow
 * Desc : 绑定方式启动service
 * Date :2018/11/7/007
 *
 * @author 付小影子
 */
public class BinderServiceClass extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("hh","BinderServiceClass onCreate");
    }

    public void getServiceContent() {
        ToastUtil.showMsg("hello shadow!! BinderServiceClass");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("hh","BinderServiceClass onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d("hh","BinderServiceClass onBind");
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        LogUtil.d("hh","BinderServiceClass onDestroy");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public BinderServiceClass getService() {
            return BinderServiceClass.this;
        }
    }

}
