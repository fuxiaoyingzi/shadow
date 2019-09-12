package com.example.administrator.shadowapplication.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import android.util.Log;

import com.example.administrator.shadowapplication.crash_log.ToastUtil;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MessengerService extends Service {
    public static final int MSG_SAY_HELLO = 100;

    //----------------Messenger实现服务和客户端跨进程通信===================
    /**
     *
     服务实现一个 Handler，由其接收来自客户端的每个调用的回调
     Handler 用于创建 Messenger 对象（对 Handler 的引用）
     Messenger 创建一个 IBinder，服务通过 onBind() 使其返回客户端
     客户端使用 IBinder 将 Messenger（引用服务的 Handler）实例化，然后使用后者将 Message 对象发送给服务
     服务在其 Handler 中（具体地讲，是在 handleMessage() 方法中）接收每个 Message。

     */
    //1.服务端实现handle
    public Handler serviceHanle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //服务就是在 Handler 的 handleMessage() 方法中接收传入的 Message，并根据 what 成员决定下一步操作。
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    if (msg.getData() != null){
                        String clientSend = msg.getData().getString("hh");
                        Log.d("hh",clientSend);
                        ToastUtil.showMsg(clientSend);
                    }
                    Messenger clientMessenger = msg.replyTo;
                    Message clientMessage = Message.obtain(null,1);
                    Bundle bundle = new Bundle();
                    bundle.putString("hh","hello client，hello world！！！");
                    clientMessage.setData(bundle);
                    try {
                        clientMessenger.send(clientMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };
    //2.根据handle创建messenger
    Messenger mMessenger = new Messenger(serviceHanle);

    //3.Messenger 创建一个 IBinder，服务通过 onBind() 使其返回客户端
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    //4.客户端只需根据服务返回的 IBinder 创建一个 Messenger，然后利用 send() 发送一条消息
}
