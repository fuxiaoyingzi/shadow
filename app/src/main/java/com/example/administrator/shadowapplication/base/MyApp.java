package com.example.administrator.shadowapplication.base;
import android.app.Activity;
import android.app.Application;


import com.example.administrator.shadowapplication.dagger.AppComponent;
import com.example.administrator.shadowapplication.dagger.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Administrator on 2017/11/2.
 */

public class MyApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent component = DaggerAppComponent.builder().build();
        component.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
