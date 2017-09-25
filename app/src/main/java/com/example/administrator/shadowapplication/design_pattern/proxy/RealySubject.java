package com.example.administrator.shadowapplication.design_pattern.proxy;

import android.util.Log;

/**
 * Created by Administrator on 2017/6/22.
 * 真正的实体类，告知对方自己要做的操作，but自己并不执行
 */

public class RealySubject implements Subject {
    @Override
    public void buyMac() {
        Log.d("hh","买一个最新的Mac本本");

    }
}
