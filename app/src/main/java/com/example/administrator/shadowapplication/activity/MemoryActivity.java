package com.example.administrator.shadowapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.shadowapplication.R;

public class MemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        UserManger userManger = UserManger.getInstance(this);
    }
}
