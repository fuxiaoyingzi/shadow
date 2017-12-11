package com.example.administrator.shadowapplication.databing;

import android.text.TextUtils;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2017/12/11
 *     desc   : DATA BIND MODEL
 * </pre>
 */


public class UserModel {
    private User user;

    public UserModel(User user) {
        this.user = user;
    }

    public String getUserName() {
        return user != null && !TextUtils.isEmpty(user.getName()) ? user.getName() : "not set";
    }

    public String getUserSex() {
        return user != null && !TextUtils.isEmpty(user.getSex()) ? user.getSex() : "not set";
    }

    public String getUserAge() {
        return user != null && user.getAge() > 0 ? user.getAge() + "岁" : "18岁";
    }

    public String getDesc() {
        return user != null && !TextUtils.isEmpty(user.getDesc()) ? user.getDesc() : "HELLO SHADOW";
    }


}
