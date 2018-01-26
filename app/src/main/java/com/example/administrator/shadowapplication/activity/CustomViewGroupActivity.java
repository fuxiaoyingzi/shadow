package com.example.administrator.shadowapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.CustomView2;

public class CustomViewGroupActivity extends AppCompatActivity {

    private TextView three;
    private CustomView2 customSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
        three = (TextView) findViewById(R.id.tree);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("hello 小 三 ！！！");
            }
        });
        customSun = (CustomView2) findViewById(R.id.customSun);
        customSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("hello 小 太 阳 ！！！");
            }
        });

    }

    public void toast(String content) {
        Toast.makeText(CustomViewGroupActivity.this, content, Toast.LENGTH_SHORT).show();
    }
}
