package com.example.administrator.shadowapplication.design_pattern.proxy;

/**
 * Author : shadow
 * Desc :真实主题类，代理类所代表的真实主题
 * 客户端通过代理类间接的调用真实主题类的方法
 * Date :2018/4/12/012
 */

public class Shadow implements IShop {
    @Override
    public void buy() {
        System.out.println("购买红肠");
    }
}
