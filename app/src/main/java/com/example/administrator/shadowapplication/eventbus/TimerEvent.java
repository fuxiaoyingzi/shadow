package com.example.administrator.shadowapplication.eventbus;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/23
 *     desc   :事件传输类，主要依靠此类来传递数据
 * </pre>
 */


public class TimerEvent {
    private String timer;

    public TimerEvent(String timer) {
        this.timer = timer;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }
}
