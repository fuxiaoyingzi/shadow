package com.example.administrator.shadowapplication.design_pattern.proxy;

import android.util.Log;

/**
 * Created by Administrator on 2017/6/22.
 * 代理类，帮助别人执行某种操作，中间商
 */

public class Proxy implements Subject {
    @Override
    public void buyMac() {
        RealySubject subject = new RealySubject();
        //调用真实对象的方法，进行代理购买Mac
        subject.buyMac();
        //代理对象额外做的操作
        this.WrapMac();

    }

    public void WrapMac(){
        Log.d("hh","包装好看点");
    }
}
