package com.example.administrator.shadowapplication.design_pattern.observe_subject;

import java.util.Observable;

/**
 * @author 付影影
 * @desc 抽象 被观察者
 * @date 2019/10/29
 */
public interface Subject {
    /**
     * 增加 订阅者
     * @param observer
     */
    public void attach(Observer observer);

    /**
     * 删除 订阅者
     * @param observer
     */
    public void detach(Observer observer);


    /**
     * 通知订阅者 更新信息
     * @param message
     */
    public void notify(String message);


}
