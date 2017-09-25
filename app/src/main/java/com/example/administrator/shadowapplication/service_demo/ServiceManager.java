package com.example.administrator.shadowapplication.service_demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ServiceManager {
    private static ServiceManager serviceManager;
    ServiceConnection connection;
    private MyBinder binder;
    MusicService musicService;

    //构造方法私有化
    private ServiceManager() {

    }

    //单例模式生成对象
    public static ServiceManager getServiceManager() {
        if (serviceManager == null) {
            synchronized (ServiceManager.class) {
                if (serviceManager == null) {
                    serviceManager = new ServiceManager();
                }
            }
        }
        return serviceManager;
    }

    //启动服务
    public void startService(Context context) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putExtra("name", "shadow");
        intent.putExtra("age", "18");
        context.startService(intent);
    }

    //关闭服务
    public void stopService(Context context) {
        context.stopService(new Intent(context, MusicService.class));
        context.unbindService(connection);
    }

    /**
     * 绑定服务
     * @param context
     */
    public void bindService(Context context){
        Intent intent = new Intent(context,MusicService.class);
       connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (MyBinder) service;
                //通过binder与服务进行沟通
                musicService = binder.getService();
                binder.getData();



            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        context.bindService(intent,connection,Context.BIND_AUTO_CREATE);
    }
}
