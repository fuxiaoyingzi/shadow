package com.example.administrator.shadowapplication.design_pattern.observe_subject;

/**
 * @author 付影影
 * @desc 客户端调用
 * @date 2019/10/29
 */
public class Client {
    public static void main(String[] args) {
        Subject subscriptionSubject = new SubscriptionSubject();
        //创建微信用户
        WexinUser user1 = new WexinUser("付小影");
        WexinUser user2 = new WexinUser("付小影子2");
        WexinUser user3 = new WexinUser("付小影子3");
        WexinUser user4 = new WexinUser("付小影子4");
        WexinUser user5 = new WexinUser("付小影子5");
        //订阅
        subscriptionSubject.attach(user1);
        subscriptionSubject.attach(user2);
        subscriptionSubject.attach(user3);
        subscriptionSubject.attach(user4);
        subscriptionSubject.attach(user5);
        //更新 数据 发送通知
        subscriptionSubject.notify("hello 付小影子，不要纠结");

    }
}
