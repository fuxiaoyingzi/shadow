package com.example.administrator.shadowapplication.design_pattern.observe_subject;

/**
 * @author 付影影
 * @desc 抽象观察者
 * @date 2019/10/29
 */
public interface Observer {
    /**
     * 更新数据
     * @param message
     */
    public void update(String message);
}
