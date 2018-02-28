package com.example.administrator.shadowapplication.broadcast_receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shadowapplication.R;

public class BroadcastReceiveActivity extends AppCompatActivity {
    private static final String BROAD_ACTION_ONE = "broad_action_one";
    private Button sendBroad;

    BroadcastReceiver myReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(BROAD_ACTION_ONE)) {
                sendBroad.setText(intent.getStringExtra("hh"));
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receive);
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROAD_ACTION_ONE);
        registerReceiver(myReceive, intentFilter);
        sendBroad = findViewById(R.id.sendBroad);
        sendBroad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //开启一个新线程，发布广播
                        Intent intent = new Intent();
                        intent.setAction(BROAD_ACTION_ONE);
                        intent.putExtra("hh", "hello shadow");
                        sendBroadcast(intent);
//
//                        Intent intent1 = new Intent();
//                        intent1.setAction("");
//                        intent1.setDataAndType(Uri.parse(""),"");
//                        intent.addCategory("");
//                        startActivity(intent);



                    }
                }).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册广播
        unregisterReceiver(myReceive);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}
