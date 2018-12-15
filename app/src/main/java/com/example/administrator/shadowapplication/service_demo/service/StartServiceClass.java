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
 * 如何保证Service不被杀死？
 *
 * 1. onStartCommand方式中，返回START_STICKY
 *
 * 首先我们来看看onStartCommand都可以返回哪些值：
 *
 * 调用Context.startService方式启动Service时，如果Android面临内存匮乏，可能会销毁当前运行的Service，待内存充足时可以重建Service。而Service被Android系统强制销毁并再次重建的行为依赖于Service的onStartCommand()方法的返回值。
 *
 *     START_NOT_STICKY
 *     如果返回START_NOT_STICKY，表示当Service运行的进程被Android系统强制杀掉之后，不会重新创建该Service。当然如果在其被杀掉之后一段时间又调用了startService，那么该Service又将被实例化。那什么情境下返回该值比较恰当呢？
 *     如果我们某个Service执行的工作被中断几次无关紧要或者对Android内存紧张的情况下需要被杀掉且不会立即重新创建这种行为也可接受，那么我们便可将 onStartCommand的返回值设置为START_NOT_STICKY。
 *     举个例子，某个Service需要定时从服务器获取最新数据：通过一个定时器每隔指定的N分钟让定时器启动Service去获取服务端的最新数据。当执行到Service的onStartCommand时，在该方法内再规划一个N分钟后的定时器用于再次启动该Service并开辟一个新的线程去执行网络操作。假设Service在从服务器获取最新数据的过程中被Android系统强制杀掉，Service不会再重新创建，这也没关系，因为再过N分钟定时器就会再次启动该Service并重新获取数据。
 *
 *     START_STICKY
 *     如果返回START_STICKY，表示Service运行的进程被Android系统强制杀掉之后，Android系统会将该Service依然设置为started状态（即运行状态），但是不再保存onStartCommand方法传入的intent对象，然后Android系统会尝试再次重新创建该Service，并执行onStartCommand回调方法，但是onStartCommand回调方法的Intent参数为null，也就是onStartCommand方法虽然会执行但是获取不到intent信息。如果你的Service可以在任意时刻运行或结束都没什么问题，而且不需要intent信息，那么就可以在onStartCommand方法中返回START_STICKY，比如一个用来播放背景音乐功能的Service就适合返回该值。
 *
 *     START_REDELIVER_INTENT
 *     如果返回START_REDELIVER_INTENT，表示Service运行的进程被Android系统强制杀掉之后，与返回START_STICKY的情况类似，Android系统会将再次重新创建该Service，并执行onStartCommand回调方法，但是不同的是，Android系统会再次将Service在被杀掉之前最后一次传入onStartCommand方法中的Intent再次保留下来并再次传入到重新创建后的Service的onStartCommand方法中，这样我们就能读取到intent参数。只要返回START_REDELIVER_INTENT，那么onStartCommand重的intent一定不是null。如果我们的Service需要依赖具体的Intent才能运行（需要从Intent中读取相关数据信息等），并且在强制销毁后有必要重新创建运行，那么这样的Service就适合返回START_REDELIVER_INTENT。
 *
 * 2.提高Service的优先级
 * 在AndroidManifest.xml文件中对于intent-filter可以通过android:priority = "1000"这个属性设置最高优先级，1000是最高值，如果数字越小则优先级越低，同时适用于广播。
 *
 * 3.提升Service进程的优先级
 *
 * 当系统进程空间紧张时，会依照优先级自动进行进程的回收。
 * Android将进程分为6个等级，按照优先级由高到低依次为：
 *
 *     前台进程foreground_app
 *     可视进程visible_app
 *     次要服务进程secondary_server
 *     后台进程hiddena_app
 *     内容供应节点content_provider
 *     空进程empty_app
 *     可以使用startForeground将service放到前台状态，这样低内存时，被杀死的概率会低一些。
 *
 * 4.在onDestroy方法里重启Service
 * 当service走到onDestroy()时，发送一个自定义广播，当收到广播时，重新启动service。
 *
 * 5.系统广播监听Service状态
 * 6.将APK安装到/system/app，变身为系统级应用
 *
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
