package com.example.administrator.shadowapplication.service_demo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/23
 *     desc   :
 * </pre>
 */


public class IntentServerTest extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServerTest(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
