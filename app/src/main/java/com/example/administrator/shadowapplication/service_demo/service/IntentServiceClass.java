package com.example.administrator.shadowapplication.service_demo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.administrator.shadowapplication.crash_log.LogUtil;

/**
 * Author : shadow
 * Desc : IntentService
 * Date :2018/11/7/007
 * IntentService是继承并处理异步请求的一个类，在IntentService内有一个工作线程来处理耗时操作，
 * 启动IntentService的方式和启动传统的Service一样，同时，当任务执行完后，IntentService会自动停止，
 * 而不需要我们手动去控制或stopSelf()。另外，可以启动IntentService多次，
 * 而每一个耗时操作会以工作队列的方式在IntentService的onHandleIntent回调方法中执行，
 * 并且，每次只会执行一个工作线程，执行完第一个再执行第二个，以此类推。
 *
 * @author 付小影子
 */
public class IntentServiceClass extends IntentService {


    public IntentServiceClass() {
        super("MIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceClass(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("hh", "IntentServiceClass onCreate");
    }

    /**
     * Intent服务开启后，执行完onHandleIntent里面的任务就自动销毁结束，
     * 通过打印的线程名称可以发现是新开了一个线程来处理耗时操作的，
     * 即是耗时操作也可以被这个线程管理和执行，同时不会产生ANR的情况。
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String intentString   =intent.getStringExtra("shadow");
        LogUtil.d("hh","IntentServiceClass onHandleIntent"+intentString);
        for (int i = 0; i < 100; i++) {
            LogUtil.d("onHandleIntent--", i + "--" + Thread.currentThread().getName());
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        LogUtil.d("hh","IntentServiceClass onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("hh","IntentServiceClass onDestroy");
    }
}
