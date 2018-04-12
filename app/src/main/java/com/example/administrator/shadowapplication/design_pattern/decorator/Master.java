package com.example.administrator.shadowapplication.design_pattern.decorator;

/**
 * Author : shadow
 * Desc :抽象装饰者，包含被装饰者的引用
 * Date :2018/4/12/012
 */

public abstract class Master extends Swordsman {
    private Swordsman mSwordsman;

    public Master(Swordsman swordsman) {
        mSwordsman = swordsman;
    }

    @Override
    public void attackMagic() {
        mSwordsman.attackMagic();

    }
}
