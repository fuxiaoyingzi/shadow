package com.example.administrator.shadowapplication.handle_msg;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author : shadow
 * Desc :
 * Date :2019/1/22/022
 * @author 付小影子
 */
public class LocalIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public LocalIntentService(String name) {
        super(name);
    }


    public LocalIntentService() {
        super("shadow");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hh","LocalIntentService onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("hh","LocalIntentService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("hh","LocalIntentService onStart");
    }


    /**
     * 运行在子线程中@WorkerThread
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("action_task");
        Log.d("hh","receive task:"+action);
        //模拟耗时任务
        SystemClock.sleep(3000);
        if ("hello shadow".equals(action)){
            Log.d("hh","handle task:"+action);
        }
    }

    @Override
    public void onDestroy() {
        Log.d("hh","service destroy");
        super.onDestroy();
    }
}
