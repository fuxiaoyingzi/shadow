package com.example.administrator.shadowapplication.design_pattern.proxy;

/**
 * Author : shadow
 * Desc :代理类，持有真实主题类的引用，在其所实现的接口方法中调用真实主题类相应的接口方法执行
 * Date :2018/4/12/012
 */

public class Purchasing implements IShop {
    private IShop mIShop;

    public Purchasing(IShop IShop) {
        mIShop = IShop;
    }

    @Override
    public void buy() {
        mIShop.buy();//调用真实主题类的接口方法执行

    }
}
