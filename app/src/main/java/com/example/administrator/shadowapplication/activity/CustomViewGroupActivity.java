package com.example.administrator.shadowapplication.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.CustomAnimView;
import com.example.administrator.shadowapplication.widget.CustomView2;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class CustomViewGroupActivity extends AppCompatActivity {

    private TextView three;
    private CustomView2 customSun;
    private CustomAnimView customAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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

        customAnimView = (CustomAnimView) findViewById(R.id.customAnimView);
        new Thread(customAnimView).start();

    }

    public void toast(String content) {
        Toast.makeText(CustomViewGroupActivity.this, content, Toast.LENGTH_SHORT).show();
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
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


}
