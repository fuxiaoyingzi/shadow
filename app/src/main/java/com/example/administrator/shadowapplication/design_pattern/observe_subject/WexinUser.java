package com.example.administrator.shadowapplication.design_pattern.observe_subject;

/**
 * @author 付影影
 * @desc 具体观察者
 * @date 2019/10/29
 */
public class WexinUser implements Observer {
    private String userName;

    public WexinUser(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String message) {
        System.out.println(userName + "---" + message);
    }
}
