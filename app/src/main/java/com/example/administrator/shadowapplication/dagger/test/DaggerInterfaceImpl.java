package com.example.administrator.shadowapplication.dagger.test;

import com.example.administrator.shadowapplication.crash_log.LogUtil;

/**
 * @author 付影影
 * @desc
 * @date 2019/9/25
 */
public class DaggerInterfaceImpl implements DaggerInterface {
    @Override
    public void eat() {
        LogUtil.d("hh","hello shadow ,DaggerInterfaceImpl eat");
    }

    @Override
    public void sleep() {
        LogUtil.d("hh","hello shadow ,DaggerInterfaceImpl sleep");
    }
}
