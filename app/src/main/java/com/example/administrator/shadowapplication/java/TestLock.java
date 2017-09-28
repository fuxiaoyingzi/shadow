package com.example.administrator.shadowapplication.java;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/9/28.
 *
 * java.util.concurrent.locks.Lock接口用作线程同步机制，类似于同步块。
 * 新的锁定机制更灵活，提供比同步块更多的选项。 锁和同步块之间的主要区别如下：
 序列的保证 - 同步块不提供对等待线程进行访问的序列的任何保证，但Lock接口处理它。
 无超时，如果未授予锁，则同步块没有超时选项。Lock接口提供了这样的选项。
 单一方法同步块必须完全包含在单个方法中，而Lock接口的方法lock()和unlock()可以以不同的方式调用。
 */

public class TestLock {
    public static void main(String args[]) {
        PrintsDemo PD = new PrintsDemo();

        ThreadsDemo t1 = new ThreadsDemo("Thread - 1 ", PD);
        ThreadsDemo t2 = new ThreadsDemo("Thread - 2 ", PD);
        ThreadsDemo t3 = new ThreadsDemo("Thread - 3 ", PD);
        ThreadsDemo t4 = new ThreadsDemo("Thread - 4 ", PD);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Task());
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
        poolExecutor.shutdown();
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Task!");
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class PrintsDemo {

    private final Lock queueLock = new ReentrantLock();

    protected void print() {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "  Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("%s printed the document successfully.\n", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}


class ThreadsDemo extends Thread {
    PrintsDemo printsDemo;

    ThreadsDemo(String name, PrintsDemo printDemo) {
        super(name);
        this.printsDemo = printDemo;
    }

    @Override
    public void run() {
        System.out.printf("%s starts printing a document\n", Thread.currentThread().getName());
        printsDemo.print();
    }
}
