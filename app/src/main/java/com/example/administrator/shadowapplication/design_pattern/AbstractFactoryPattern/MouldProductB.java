package com.example.administrator.shadowapplication.design_pattern.AbstractFactoryPattern;

/**
 * Created by Administrator on 2017/6/22.
 */

public class MouldProductB extends MouldProduct{
    @Override
    public void show() {
        super.show();
        System.out.println("生产出了模具产品B");
    }
}
