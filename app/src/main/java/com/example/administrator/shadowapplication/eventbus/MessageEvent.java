package com.example.administrator.shadowapplication.eventbus;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/23
 *     desc   :事件传输类，主要依靠此类来传递数据
 * </pre>
 */


public class MessageEvent {
    private String name;
    private int age;

    public MessageEvent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
