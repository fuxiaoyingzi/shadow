package com.example.administrator.shadowapplication.activity;

import android.content.Context;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/22
 *     desc   :
 * </pre>
 */


public class UserManger {
    private static UserManger userManger;
    private Context context;

    private UserManger(Context context) {
        this.context = context;
    }

    public static UserManger getInstance(Context context){
        if (userManger == null){
            userManger = new UserManger(context);
        }
        return userManger;
    }
}
