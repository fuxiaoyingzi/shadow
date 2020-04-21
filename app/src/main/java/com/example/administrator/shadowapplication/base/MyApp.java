package com.example.administrator.shadowapplication.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.bugtags.library.Bugtags;
import com.example.administrator.shadowapplication.BuildConfig;
import com.example.administrator.shadowapplication.crash_log.CrashHandle;
import com.example.administrator.shadowapplication.crash_log.OtherProcessCrashListener;
import com.example.administrator.shadowapplication.dagger.AppComponent;
import com.example.administrator.shadowapplication.dagger.DaggerAppComponent;
import com.example.administrator.shadowapplication.hot_fix.andfix.FixPackageManager;
import com.example.administrator.shadowapplication.widget.ue_tool.CustomAttribution;
import com.example.administrator.shadowapplication.widget.ue_tool.FilterOutView;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.service.PatchResult;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;
import com.tinkerpatch.sdk.server.callback.RollbackCallBack;
import com.tinkerpatch.sdk.server.callback.TinkerPatchRequestCallback;
import com.tinkerpatch.sdk.tinker.callback.ResultCallBack;
import com.tinkerpatch.sdk.tinker.service.TinkerServerResultService;

import java.util.HashMap;

import javax.inject.Inject;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import me.ele.uetool.UETool;

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
        initJpush();
        initTinkerPatch();
        initUeTool();
        //在这里初始化
        //BTGInvocationEventNone    // 静默模式，只收集 Crash 信息（如果允许，默认为允许）
        //
        //BTGInvocationEventShake   // 通过摇一摇呼出 Bugtags
        //
        //BTGInvocationEventBubble  // 通过悬浮小球呼出 Bugtags
        Bugtags.start("d0793be8fdbd9fe0b9eaf08e7ed93d73 ", this, Bugtags.BTGInvocationEventBubble);
    }

    /**
     * 初始化UETool
     */
    private void initUeTool() {
        //过滤掉你不需要选中的 View
        UETool.putFilterClass(FilterOutView.class);
        //自定义实现你的 View 的属性
        UETool.putAttrsProviderClass(CustomAttribution.class);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            private int visibleActivityCount;
            private int uetoolDismissY = -1;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                visibleActivityCount++;
                if (visibleActivityCount == 1 && uetoolDismissY >= 0) {
                    UETool.showUETMenu(uetoolDismissY);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                visibleActivityCount--;
                if (visibleActivityCount == 0) {
                    uetoolDismissY = UETool.dismissUETMenu();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /**
     * 初始化极光
     */
    private void initJpush() {
        JAnalyticsInterface.init(this);
        JAnalyticsInterface.setDebugMode(true);
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

    public int getScannWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }


    private ApplicationLike tinkerApplicationLike;

    /**
     * 我们需要确保至少对主进程跟patch进程初始化 TinkerPatch
     */
    private void initTinkerPatch() {
        // 我们可以从这里获得Tinker加载过程的信息
        if (BuildConfig.TINKER_ENABLE) {
            tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
            // 初始化TinkerPatch SDK
            TinkerPatch.init(
                    tinkerApplicationLike
//                new TinkerPatch.Builder(tinkerApplicationLike)
//                    .requestLoader(new OkHttp3Loader())
//                    .build()
            )
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true)
                    .setFetchPatchIntervalByHours(3)
            ;
            // 获取当前的补丁版本
            Log.d("hh", "Current patch version is " + TinkerPatch.with().getPatchVersion());

            // fetchPatchUpdateAndPollWithInterval 与 fetchPatchUpdate(false)
            // 不同的是，会通过handler的方式去轮询
            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
        }
    }

    /**
     * 在这里给出TinkerPatch的所有接口解释
     * 更详细的解释请参考:http://tinkerpatch.com/Docs/api
     */
    private void useSample() {
        TinkerPatch.init(tinkerApplicationLike)
                //是否自动反射Library路径,无须手动加载补丁中的So文件
                //注意,调用在反射接口之后才能生效,你也可以使用Tinker的方式加载Library
                .reflectPatchLibrary()
                //向后台获取是否有补丁包更新,默认的访问间隔为3个小时
                //若参数为true,即每次调用都会真正的访问后台配置
                .fetchPatchUpdate(false)
                //设置访问后台补丁包更新配置的时间间隔,默认为3个小时
                .setFetchPatchIntervalByHours(3)
                //向后台获得动态配置,默认的访问间隔为3个小时
                //若参数为true,即每次调用都会真正的访问后台配置
                .fetchDynamicConfig(new ConfigRequestCallback() {
                    @Override
                    public void onSuccess(HashMap<String, String> hashMap) {

                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                }, false)
                //设置访问后台动态配置的时间间隔,默认为3个小时
                .setFetchDynamicConfigIntervalByHours(3)
                //设置当前渠道号,对于某些渠道我们可能会想屏蔽补丁功能
                //设置渠道后,我们就可以使用后台的条件控制渠道更新
                .setAppChannel("default")
                //屏蔽部分渠道的补丁功能
                .addIgnoreAppChannel("googleplay")
                //设置tinkerpatch平台的条件下发参数
                .setPatchCondition("test", "1")
                //设置补丁合成成功后,锁屏重启程序
                //默认是等应用自然重启
                .setPatchRestartOnSrceenOff(true)
                //我们可以通过ResultCallBack设置对合成后的回调
                //例如弹框什么
                //注意，setPatchResultCallback 的回调是运行在 intentService 的线程中
                .setPatchResultCallback(new ResultCallBack() {
                    @Override
                    public void onPatchResult(PatchResult patchResult) {
                        Log.i("hh", "onPatchResult callback here");
                    }
                })
                //设置收到后台回退要求时,锁屏清除补丁
                //默认是等主进程重启时自动清除
                .setPatchRollbackOnScreenOff(true)
                //我们可以通过RollbackCallBack设置对回退时的回调
                .setPatchRollBackCallback(new RollbackCallBack() {
                    @Override
                    public void onPatchRollback() {
                        Log.i("hh", "onPatchRollback callback here");
                    }
                });
    }

    /**
     * 自定义Tinker类的高级用法, 使用更灵活，但是需要对tinker有更进一步的了解
     * 更详细的解释请参考:http://tinkerpatch.com/Docs/api
     */
    private void complexSample() {
        //修改tinker的构造函数,自定义类
        TinkerPatch.Builder builder = new TinkerPatch.Builder(tinkerApplicationLike)
                .listener(new DefaultPatchListener(this))
                .loadReporter(new DefaultLoadReporter(this))
                .patchReporter(new DefaultPatchReporter(this))
                .resultServiceClass(TinkerServerResultService.class)
                .upgradePatch(new UpgradePatch())
                .patchRequestCallback(new TinkerPatchRequestCallback());
        //.requestLoader(new OkHttpLoader());

        TinkerPatch.init(builder.build());
    }


}
