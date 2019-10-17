package com.example.administrator.shadowapplication.handle_msg;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/16
 */
public class CallableTest {
    public static class ThreadCallAble implements Callable {

        @Override
        public String call() throws Exception {
            return "hello world";
        }
    }
    public static void main(String args[]) {
        ThreadCallAble mCall = new ThreadCallAble();
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        Future mFuture = singleThreadPool.submit(mCall);
        try {
            Log.d("hh", "mFuture.get() = " + mFuture.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }















    }
}
