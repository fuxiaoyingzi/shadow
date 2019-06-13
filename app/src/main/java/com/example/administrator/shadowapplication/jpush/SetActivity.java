package com.example.administrator.shadowapplication.jpush;

import android.app.Activity;
import android.os.Bundle;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

public class SetActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JAnalyticsInterface.onPageStart(getApplicationContext(),this.getClass().getCanonicalName());
    }


    @Override
    protected void onPause() {
        super.onPause();
        JAnalyticsInterface.onPageEnd(getApplicationContext(),this.getClass().getCanonicalName());
    }
}
