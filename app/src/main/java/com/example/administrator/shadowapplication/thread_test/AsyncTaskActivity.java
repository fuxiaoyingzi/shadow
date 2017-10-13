package com.example.administrator.shadowapplication.thread_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shadowapplication.R;

public class AsyncTaskActivity extends AppCompatActivity {

    /**
     * 测试异步线程处理数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        Button startTask = (Button) findViewById(R.id.btn_start_task);
        startTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTaskSelf().execute();
            }
        });
    }
}
