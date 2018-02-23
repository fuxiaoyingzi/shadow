package com.example.administrator.shadowapplication.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.shadowapplication.R;

public class IPCServiceActivity extends AppCompatActivity {

    private Messenger messenger;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IPCService.HANDLE_MESSAGE_TYPE_TWO:
                    Log.d("hh","服务端回应："+msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    };

    private Messenger handleMessenger = new Messenger(handler); //用于接受回调的handleMessage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipcservice);
        Intent intent = new Intent(this, IPCService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message message = Message.obtain(null, IPCService.HANDLE_MESSAGE_TYPE_ONE);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello shadow");
            message.setData(bundle);
            message.replyTo = handleMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
