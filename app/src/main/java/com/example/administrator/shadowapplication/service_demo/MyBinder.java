package com.example.administrator.shadowapplication.service_demo;

import android.os.Binder;

/**
 * Created by Administrator on 2017/7/28.
 * 主要用于bindService的回调接口实例
 *
    包含客户端可调用的公共方法
    返回当前 Service 实例，其中包含客户端可调用的公共方法
    或返回由服务承载的其他类的实例，其中包含客户端可调用的公共方法
    此类的方法主要是客户端和服务进行通信的
    扩展 Binder 类

    如果您的服务仅供本地应用使用，不需要跨进程工作，则可以实现自有 Binder 类，让您的客户端通过该类直接访问服务中的公共方法。

 */

public class MyBinder extends Binder {
    public MusicService getService(){
        return new MusicService();
    }

    /**
     * 用户客户端访问此方法
     * @return
     */
    public String getData(){
        return "hello shadow";
    }
}
