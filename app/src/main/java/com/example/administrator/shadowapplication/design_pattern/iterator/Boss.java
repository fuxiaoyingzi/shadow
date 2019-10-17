package com.example.administrator.shadowapplication.design_pattern.iterator;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public class Boss extends Leader {
    @Override
    protected int limit() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void handler(int money) {
        System.out.println("老板批复报销：" + money + "元");
    }
}
