package com.example.administrator.shadowapplication.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.shadowapplication.IOnNewBookArrivedListener;
import com.example.administrator.shadowapplication.R;

import java.util.List;

public class AidlBindServiceActivity extends AppCompatActivity {

    private Button bindService;
    private TextView contentText;
    private BookRemoteInterface bookRemoteInterface;
    private static final  int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case MESSAGE_NEW_BOOK_ARRIVED :
                    Log.d("hh","receive new book:"+msg.obj);
                    break;
                    default:
                        super.handleMessage(msg);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_bind_service);
        bindService = findViewById(R.id.bindService);
        contentText = findViewById(R.id.contentText);
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AidlBindServiceActivity.this,BookManagerService.class);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });
    }

    /**
     * 客户端调用远程服务端的方法，被调用的方法运行在服务端的Binder线程池中，同时客户端线程被挂起，
     * 这个时候如果服务端的方法比较耗时，导致客户端线程长时间阻塞在这里，如果客户端是在UI线程调用方法，客户端就会出现ANR错误
     * onServiceConnected，onServiceDisconnected都是运行在UI线程的
     * 所以如果知道要调用的方法是比较耗时的，就要开启新的线程调用服务端的方法
     */

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             bookRemoteInterface =  BookRemoteInterface.Stub.asInterface(service);
            try {
                //客户端访问服务端的方法，如果服务端方法比较耗时，可以放在新的线程中调用
              /*  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Book> books = bookRemoteInterface.getBookList();
                    }
                }).start();*/
                if (bookRemoteInterface.getBookList() != null){
                    List<Book> books = bookRemoteInterface.getBookList();
                    StringBuilder builder = new StringBuilder();
                    for (Book book:books) {
                        builder.append(book.getBookId()+"--"+book.getBookName()+"---"+book.getBookPrice());
                        builder.append("\n\r");
                    }
                    contentText.setText(builder.toString());
                }
                bookRemoteInterface.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bookRemoteInterface = null;
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener() {
        @Override
        public void onNewBookArried(Book book) throws RemoteException {
            //此方法运行在客户端的Binder线程池中，所以需要handle 切换到UI线程
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED,book).sendToTarget();
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    };

    @Override
    protected void onDestroy() {
        if (bookRemoteInterface != null && bookRemoteInterface.asBinder().isBinderAlive()){
            try {
                bookRemoteInterface.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();

    }
}
