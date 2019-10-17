package com.example.administrator.shadowapplication.design_pattern.iterator;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public class GroupLeader extends Leader {
    @Override
    protected int limit() {
        return 1000;
    }

    @Override
    protected void handler(int money) {
        System.out.println("组长批复报销："+money+"元");
    }
}
