package com.example.administrator.shadowapplication.design_pattern.Singleton;

/**
 * Created by Administrator on 2017/6/22.
 * 单例模式 就是整个全局内只有一个实例化对象,把构造方法私有化，仅仅提供一个外部公用的静态方法
 */

public class SingletonPattern {
    private static SingletonPattern singletonPattern ;
    private SingletonPattern(){}
    public SingletonPattern getInstance(){
        if (singletonPattern == null){
            synchronized (SingletonPattern.class){
                if (singletonPattern == null){
                    singletonPattern = new SingletonPattern();
                }
            }
        }

        return  singletonPattern;
    }
}
