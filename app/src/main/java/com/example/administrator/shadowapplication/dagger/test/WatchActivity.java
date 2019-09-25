package com.example.administrator.shadowapplication.dagger.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

public class WatchActivity extends AppCompatActivity {

    @Inject
    Watch mWatch;

    @Inject
    User mUser;

    @Inject
    @Named("one")
    DaggerInterface oneImpl;

    @Inject
    @Named("two")
    DaggerInterface twoImpl;

    @Inject
    @CustomInject
    DaggerInterface threeImpl;

    @Inject
    Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        DaggerWatchComponent.create().inject(this);
        TextView tvDagger = findViewById(R.id.tvDagger);
        tvDagger.setText(mWatch.work());

        mUser.getUserName();
        oneImpl.eat();
        twoImpl.eat();
        threeImpl.eat();
    }
}
