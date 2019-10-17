package com.example.administrator.shadowapplication.design_pattern.iterator;

/**
 * @author 付影影
 * @desc 员工想要报销，所有把报销单给组长，组长判断自己的权限，能处理就处理，不能处理就交给自己的上级处理
 * 一层层的传递下去，直到有对象处理，或者所有的对象都处理不了，退出。。
 * 在责任链模式中，请求发送者并不需要关心是谁处理了自己的请求，只需要拿到结果。。
 * 责任链模式 完美的 将请求发送者和处理者解耦
 * @date 2019/10/17
 */
public class WorkerFu {
    public static void main(String[] args) {
        //构造各级领导对象
        GroupLeader groupLeader = new GroupLeader();
        DirectorLeader directorLeader = new DirectorLeader();
        ManagerLeader managerLeader = new ManagerLeader();
        Boss boss = new Boss();

        //设置上一级领导处理者对象
        groupLeader.nextHandle = directorLeader;
        directorLeader.nextHandle = managerLeader;
        managerLeader.nextHandle = boss;

        //发起账单申请
        groupLeader.handler((int) Math.random() * 30000);

    }
}
