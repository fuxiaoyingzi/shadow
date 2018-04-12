package com.example.administrator.shadowapplication.design_pattern.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : shadow
 * Desc :享元工厂模式
 * Date :2018/4/12/012
 */

public class GoodsFactory {
    private static Map<String,Goods> pool = new HashMap<>();
    public static Goods getGoods(String name){
        if (pool.containsKey(name)){
            System.out.println("使用缓存，key值为："+name);
            return pool.get(name);
        }else {
            Goods goods = new Goods(name);
            pool.put(name,goods);
            System.out.print("创建商品，key值为："+name);
            return goods;
        }
    }
}
