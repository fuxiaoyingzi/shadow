package com.example.administrator.shadowapplication.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import java.util.List;

public class AidlBindServiceActivity extends AppCompatActivity {

    private Button bindService;
    private TextView contentText;
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

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BookRemoteInterface bookRemoteInterface =  BookRemoteInterface.Stub.asInterface(service);
            try {
                if (bookRemoteInterface.getBookList() != null){
                    List<Book> books = bookRemoteInterface.getBookList();
                    StringBuilder builder = new StringBuilder();
                    for (Book book:books) {
                        builder.append(book.getBookId()+"--"+book.getBookName()+"---"+book.getBookPrice());
                        builder.append("\n\r");
                    }
                    contentText.setText(builder.toString());
                }
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
