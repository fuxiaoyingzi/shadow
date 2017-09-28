package com.example.administrator.shadowapplication.java;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Administrator on 2017/9/28.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TestJavaThread {
    public static void main(String args[]) {
        PrintDemo PD = new PrintDemo();

        ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD);
        ThreadDemo T2 = new ThreadDemo("Thread - 2 ", PD);

        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println("threadLocalRandom.nextInt() = "+threadLocalRandom.nextInt());

    }


}

class PrintDemo {
    public void printCount() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    PrintDemo PD;

    ThreadDemo(String name, PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        synchronized (PD) {
            PD.printCount();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}



