package com.example.administrator.shadowapplication.service_demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.shadowapplication.R;

/**
 * 通过Messenger实现跨进程通信
 *
 * 当您需要执行 IPC 时，为您的接口使用 Messenger 要比使用 AIDL 实现它更加简单
 * ，因为 Messenger 会将所有服务调用排入队列，而纯粹的 AIDL 接口会同时向服务发送多个请求，服务随后必须应对多线程处理。
 * 对于大多数应用，服务不需要执行多线程处理，因此使用 Messenger 可让服务一次处理一个调用。
 * 如果您的服务必须执行多线程处理，则应使用 AIDL 来定义接口。
 */
public class MessengerServiceActivity extends AppCompatActivity {
    private ServiceConnection connection;
    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_service);
    }

    public void bindService(){
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //客户端只需根据服务返回的 IBinder 创建一个 Messenger，然后利用 send() 发送一条消息
                messenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 通过messenger发送数据给服务
     * 如果您想让服务作出响应，则还需要在客户端中创建一个 Messenger。
     * 然后，当客户端收到 onServiceConnected() 回调时，
     * 会向服务发送一条 Message，并在其 send() 方法的 replyTo 参数中包含客户端的 Messenger。
     *
     */
     class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    //客户端的messenger 主要用于接受服务端的数据
    Messenger clientMessenger = new Messenger(new MyHandle());


    public void sayHello(){
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        msg.replyTo  = clientMessenger;
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
