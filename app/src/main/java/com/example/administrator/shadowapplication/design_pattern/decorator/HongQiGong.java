package com.example.administrator.shadowapplication.design_pattern.decorator;

/**
 * Author : shadow
 * Desc :装饰者的具体实现类
 * Date :2018/4/12/012
 */

public class HongQiGong extends Master {
    public HongQiGong(Swordsman swordsman) {
        super(swordsman);
    }

    public void teachAttacMagin(){
        System.out.println("洪七公教授打狗棒法");
        System.out.print("杨过使用打狗棒法");
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttacMagin();
    }
}
