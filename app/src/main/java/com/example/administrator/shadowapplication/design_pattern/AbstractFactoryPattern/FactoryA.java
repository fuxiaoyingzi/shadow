package com.example.administrator.shadowapplication.design_pattern.AbstractFactoryPattern;

/**
 * Created by Administrator on 2017/6/22.
 */

public class FactoryA implements Factory {
    @Override
    public Product ManufactureContainer() {
        return new MouldProductA();
    }

    @Override
    public Product ManufactureMould() {
        return new MouldProductA();
    }
}
