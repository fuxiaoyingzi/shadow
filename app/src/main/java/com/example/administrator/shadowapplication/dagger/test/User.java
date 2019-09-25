package com.example.administrator.shadowapplication.dagger.test;

import android.util.Log;

import javax.inject.Inject;

/**
 * @author 付影影
 * @desc
 * @date 2019/9/25
 */
public class User {
    private String name;

    /**
     * 标记构造方法 表明dagger2可以使用User，构造方法构建对象
     */
    @Inject
    public User() {
    }

    public void getUserName() {
        Log.d("hh", " hello shadow，User");
    }
}
