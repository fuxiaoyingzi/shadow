package com.example.administrator.shadowapplication.jpush;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;


/**
 * @author user
 * 统计
 */
public class JpushMainActivity extends Activity implements OnClickListener {

    private Button mSetting;

    public static boolean isForeground = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("xxx", "MainActivity onNewIntent");
        super.onNewIntent(intent);
    }

    private void initView() {
        TextView mImei = (TextView) findViewById(R.id.tv_imei);
        String udid = ExampleUtil.getImei(getApplicationContext(), "");
        if (null != udid) {
            mImei.setText("IMEI: " + udid);
        }

        TextView mAppKey = (TextView) findViewById(R.id.tv_appkey);
        String appKey = ExampleUtil.getAppKey(getApplicationContext());
        if (null == appKey) {
            appKey = "AppKey异常";
        }
        mAppKey.setText("AppKey: " + appKey);


        String packageName = getPackageName();
        TextView mPackage = (TextView) findViewById(R.id.tv_package);
        mPackage.setText("PackageName: " + packageName);


        String versionName = ExampleUtil.GetVersion(getApplicationContext());
        TextView mVersion = (TextView) findViewById(R.id.tv_version);
        mVersion.setText("Version: " + versionName);

        //渠道标识
        ApplicationInfo appInfo;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("UMENG_CHANNEL");
            TextView tvFlavor = findViewById(R.id.tv_flavor);
            tvFlavor.setText(channel);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mSetting = (Button) findViewById(R.id.setting);
        mSetting.setOnClickListener(this);
        findViewById(R.id.analytics_example).setOnClickListener(this);
        findViewById(R.id.analytics_example_replace).setOnClickListener(this);
        findViewById(R.id.analytics_example_showhide).setOnClickListener(this);
        findViewById(R.id.analytics_example_account).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.setting:
                intent = new Intent(JpushMainActivity.this, SetActivity.class);
                startActivity(intent);
                break;
            case R.id.analytics_example:
                intent = new Intent(JpushMainActivity.this, AnalyticsMainActivity.class);
                startActivity(intent);
                break;
            case R.id.analytics_example_replace:
                intent = new Intent(JpushMainActivity.this, ReplaceActivity.class);
                startActivity(intent);
                break;
            case R.id.analytics_example_showhide:
                intent = new Intent(JpushMainActivity.this, ShowHideActivity.class);
                startActivity(intent);
                break;
            case R.id.analytics_example_account:
                intent = new Intent(JpushMainActivity.this, AccountExampleActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        JAnalyticsInterface.onPageStart(getApplicationContext(), this.getClass().getCanonicalName());
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
        JAnalyticsInterface.onPageEnd(getApplicationContext(), this.getClass().getCanonicalName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}