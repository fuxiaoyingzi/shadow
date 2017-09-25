package com.example.administrator.shadowapplication.design_pattern.AbstractFactoryPattern;

/**
 * Created by Administrator on 2017/6/22.
 */

public class AbstractFactoryPattern {
    public AbstractFactoryPattern(){
        FactoryA mFactoryA = new FactoryA();
        FactoryB mFactoryB = new FactoryB();
        //A厂当地客户需要容器产品A
        mFactoryA.ManufactureContainer().show();
        //A厂当地客户需要模具产品A
        mFactoryA.ManufactureMould().show();

        //B厂当地客户需要容器产品B
        mFactoryB.ManufactureContainer().show();
        //B厂当地客户需要模具产品B
        mFactoryB.ManufactureMould().show();

    }
}
