package com.example.administrator.shadowapplication.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class IPCService extends Service {
    public static final int HANDLE_MESSAGE_TYPE_ONE = 1;
    public static final int HANDLE_MESSAGE_TYPE_TWO = 1;

    private static class HandleMessage extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_MESSAGE_TYPE_ONE:
                    Log.d("hh", "客户端发来消息：" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message message = Message.obtain(null, HANDLE_MESSAGE_TYPE_TWO);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "hello shadow,my name is 付小影子");
                    message.setData(bundle);
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    Messenger messenger = new Messenger(new HandleMessage());

    public IPCService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();
    }
}
