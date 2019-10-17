package com.example.administrator.shadowapplication.design_pattern.iterator;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public class ManagerLeader extends Leader {
    @Override
    protected int limit() {
        return 10000;
    }

    @Override
    protected void handler(int money) {
        System.out.println("经理批复报销："+money+"元");
    }
}
