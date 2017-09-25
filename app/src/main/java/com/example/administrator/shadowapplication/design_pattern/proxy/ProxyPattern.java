package com.example.administrator.shadowapplication.design_pattern.proxy;

/**
 * Created by Administrator on 2017/6/22.
 * 客户端调用，仅仅知道proxy代理类要执行的操作，买电脑，但是并不知道是用户S买电脑，类似现在的代购
 */

public class ProxyPattern {
    Proxy proxy;
    public ProxyPattern(){
        proxy = new Proxy();
        proxy.buyMac();
    }
}
