package com.example.administrator.shadowapplication.design_pattern.proxy;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/12/012
 */

public class Client {
    public static void main(String[] args){
        IShop mShop = new Shadow(); //真实主题类
        IShop pruchasing = new Purchasing(mShop); //代理类，传入真实主题类对象
        pruchasing.buy(); //调用代理类的方法执行
    }
}
