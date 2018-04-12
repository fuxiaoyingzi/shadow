package com.example.administrator.shadowapplication.design_pattern.Flyweight;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/12/012
 */

public class Client {
    public static void main(String[] args){
        Goods goods = GoodsFactory.getGoods("iphone7");
        goods.showGoodsPrice("32G");
        Goods good2 = GoodsFactory.getGoods("iphone8");
        good2.showGoodsPrice("32G");
        Goods good3 = GoodsFactory.getGoods("iphone8");
        good3.showGoodsPrice("62G");
    }
}
