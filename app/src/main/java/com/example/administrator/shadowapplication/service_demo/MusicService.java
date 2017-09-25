package com.example.administrator.shadowapplication.service_demo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/29.
 */

public class MusicService extends Service {
    MyBinder myBinder;


    /**
     * onBind是bindService的回调接口，返回的是客户端和服务进行通信的Ibinder接口
     * 此种方式开启的服务的生命周期与绑定它的组件是相同的，同生共死
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    /**
     * 以startService启动服务的时候才会调用此方法，生命周期与启动此服务的组件无关，也不会返回数据给组件
     * 以此种方式启动的服务，会一直运行下去，直到任务结束或者stopSelf 或者客户端主动调用stopService销毁次服务
     * 组件通过intent与服务传递数据，如果服务运行结束或者中间需要与组件传递数据，可以通过广播
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }





}
