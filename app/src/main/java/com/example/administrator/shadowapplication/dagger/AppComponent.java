package com.example.administrator.shadowapplication.dagger;

import android.app.Application;

import com.example.administrator.shadowapplication.base.MyApp;

import dagger.Component;

/**
 * Created by Administrator on 2017/11/7.
 */

@Component(modules = {ActivityModule.class})
public interface AppComponent {
    void inject(MyApp application);
}

