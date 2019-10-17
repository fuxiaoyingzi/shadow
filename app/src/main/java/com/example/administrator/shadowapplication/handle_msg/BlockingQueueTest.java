package com.example.administrator.shadowapplication.handle_msg;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 付影影
 * @desc 阻塞队列
 * @date 2019/10/15
 */
public class BlockingQueueTest {
    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> mArrayBlockingQueue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) {
        BlockingQueueTest mBlockingQueue = new BlockingQueueTest();
        Consumer consumer = mBlockingQueue.new Consumer();
        Producer producer = mBlockingQueue.new Producer();
        consumer.start();
        producer.start();

    }

    /**
     * 使用阻塞队列实现 生产者和消费者模式，无需考虑同步和线程间同步的问题
     */

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    mArrayBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    mArrayBlockingQueue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
