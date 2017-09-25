package com.example.administrator.shadowapplication.design_pattern.AbstractFactoryPattern;

/**
 * Created by Administrator on 2017/6/22.
 */

public class ContainerProductB extends ContainerProduct {
    @Override
    public void show() {
        super.show();
        System.out.println("生产出了容器产品B");
    }
}
