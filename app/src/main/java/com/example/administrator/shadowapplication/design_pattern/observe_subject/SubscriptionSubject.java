package com.example.administrator.shadowapplication.design_pattern.observe_subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 付影影
 * @desc 具体 被观察者
 * @date 2019/10/29
 */
public class SubscriptionSubject implements Subject {
    private List<Observer> weixinUserList = new ArrayList<>();


    @Override
    public void attach(Observer observer) {
        if (weixinUserList.indexOf(observer) == -1) {
            weixinUserList.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        if (weixinUserList.indexOf(observer) != -1) {
            weixinUserList.remove(observer);
        }
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserList) {
            observer.update(message);
        }
    }
}
