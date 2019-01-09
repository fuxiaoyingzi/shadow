package com.example.administrator.shadowapplication.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.baidu.mapapi.SDKInitializer;
import com.example.administrator.shadowapplication.crash_log.CrashHandle;
import com.example.administrator.shadowapplication.crash_log.OtherProcessCrashListener;
import com.example.administrator.shadowapplication.dagger.AppComponent;
import com.example.administrator.shadowapplication.dagger.DaggerAppComponent;
import com.example.administrator.shadowapplication.hot_fix.andfix.FixPackageManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Administrator on 2017/11/2.
 */

public class MyApp extends Application implements HasActivityInjector {

    private static MyApp sContext;
    private boolean isDebug = false;


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        AppComponent component = DaggerAppComponent.builder().build();
        component.inject(this);
        initBaiduMap();
        initCrash();
        initPatch();
    }

    private void initPatch() {
        FixPackageManager.getInstance().initPatch(this);
    }


    public static MyApp getInstance() {
        return sContext;
    }

    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public void initBaiduMap() {
        SDKInitializer.initialize(this);
    }

    public void initCrash() {
        CrashHandle.getCrashHandleInstance().init(this, new OtherProcessCrashListener());
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            isDebug = applicationInfo.metaData.getBoolean("DEBUG_MODE", false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isDebugModel() {
        return isDebug;
    }

    public int getScannWidth(){
           return getResources().getDisplayMetrics().widthPixels;
    }


}
