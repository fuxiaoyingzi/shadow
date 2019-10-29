package com.example.administrator.shadowapplication.design_pattern.Flyweight;

/**
 * Author : shadow
 * Desc : 具体享元角色 实现抽象享元角色定义的业务
 * Date :2018/4/12/012
 */

public class Goods implements IGoods {
    private String name;
    private String version;

    public Goods(String name) {
        this.name = name;
    }

    @Override
    public void showGoodsPrice(String version) {
        if (version.equals("32G")) {
            System.out.println("价格为1999元");
        } else if (version.equals("64G")) {
            System.out.println("价格为2999元");
        }
    }
}
