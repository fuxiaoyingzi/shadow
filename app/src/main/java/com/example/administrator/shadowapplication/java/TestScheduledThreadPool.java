package com.example.administrator.shadowapplication.java;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2017/9/28.
 */

public class TestScheduledThreadPool {

    public static void main(String[] argument){
        //可以通过调用Executors类的static newScheduledThreadPool()方法获得一个调度的线程池。
        ExecutorService executorService = Executors.newScheduledThreadPool(5);

    }
}
