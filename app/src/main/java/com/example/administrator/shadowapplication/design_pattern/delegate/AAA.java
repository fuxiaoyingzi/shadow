package com.example.administrator.shadowapplication.design_pattern.delegate;

import android.util.Log;

/**
 * Created by Administrator on 2017/6/22.
 * 委托模式，就是两个类，参数，方法完全一致，但是为了不让客户端知道具体怎么实现的，所以通过B来委托A的实现，
 * 这样当需要修改的时候，A并不需要修改，只需要修改B的实现就行，客户端也不需要做任何的修改
 * 直接实例A的对象，然后调用各种方法就好
 */

public class AAA {
    BBBDelegate delegate = new BBBDelegate();

    public void printHello(){
        delegate.printHello();
    }
}
