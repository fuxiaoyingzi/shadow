package com.example.administrator.shadowapplication.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.CustomWatchView;

public class CustomWatchActivity extends AppCompatActivity {

    private CustomWatchView watchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_watch);
        watchView = (CustomWatchView) findViewById(R.id.customWatch);



        new Thread(watchView).start();

    }
}
