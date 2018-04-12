package com.example.administrator.shadowapplication.design_pattern.decorator;

/**
 * Author : shadow
 * Desc :组件具体实现类，被装饰的具体对象
 * Date :2018/4/12/012
 */

public class YangGou extends Swordsman {
    @Override
    public void attackMagic() {
        System.out.println("杨过使用全真剑法");
    }
}
