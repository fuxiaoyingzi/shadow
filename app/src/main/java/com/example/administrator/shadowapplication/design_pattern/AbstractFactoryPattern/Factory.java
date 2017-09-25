package com.example.administrator.shadowapplication.design_pattern.AbstractFactoryPattern;

/**
 * Created by Administrator on 2017/6/22.
 * 抽象工厂类，由具体的工厂类来实现，执行不同的操作
 * 比如A厂生产容器类，也能生产模具产品
 * B厂能生产容器类也能模具产品
 */

public interface Factory {
    public  Product ManufactureContainer();
    public  Product ManufactureMould();
}
