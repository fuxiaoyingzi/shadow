package com.example.administrator.shadowapplication.eventbus;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.activity.CustomViewGroupActivity;
import com.example.administrator.shadowapplication.timer.CustomTimer2Activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusTestActivity extends AppCompatActivity {
    private TextView nameValue;
    private Button sendEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        EventBus.getDefault().register(this);
        nameValue = findViewById(R.id.nameValue);
        sendEvent = findViewById(R.id.sendEvent);
        sendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启一个子线程来发送数据,因为我们传递的是自定义类型，其实也可以传递基本类型比如string等
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //取代Handel进行线程间通信
                        MessageEvent event = new MessageEvent("小影子",18);
                        EventBus.getDefault().post(event);
                    }
                }).start();
            }
        });

        Intent intent = new Intent(this, CustomViewGroupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    //因为获取的数据需要更新UI显示出来，所以我们设置了线程模式为主线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMessage(MessageEvent event){
        //订阅事件，获取相应的数据
        nameValue.setText(event.getName()+"---"+event.getAge());
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("saveInstance","hello 付小影子，我是恢复数据");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nameValue.setText(savedInstanceState.getString("saveInstance"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hh","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("hh","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hh","onPause()");
    }
}
