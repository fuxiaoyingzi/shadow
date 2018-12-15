package com.example.administrator.shadowapplication.progress;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Author : shadow
 * Desc :
 * Date :2018/12/15/015
 * @author 付小影子
 */
public class WakeReceiver extends BroadcastReceiver {
    private static final String TAG ="hh";
    public static final int WAKE_SERVICE_ID =-10000;
    public static final String GRAY_WEAK_ACTION ="com.weak.gray";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action  = intent.getAction();
        if (GRAY_WEAK_ACTION.equals(action)){
            Log.d(TAG,"weak !! weak!!");
            Intent wakeIntent = new Intent(context, WakeNotifyService.class);
            context.startService(wakeIntent);
        }

    }
}
