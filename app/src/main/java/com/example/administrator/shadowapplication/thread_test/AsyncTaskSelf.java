package com.example.administrator.shadowapplication.thread_test;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Administrator on 2017/10/12.
 * 使用 AsyncTask 的诀窍就是，在 doInBackground()方法中去执行具体的耗时
 任务，在 onProgressUpdate()方法中进行 UI 操作，在 onPostExecute()方法中执行一些任务的
 收尾工作。
 */

public class AsyncTaskSelf extends AsyncTask<Void, Integer, Boolean> {
    /**
     * 子线程处理任务
     *
     * @param params
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            while (true) {
                int downloadPercent = doDownload(); // 这是一个虚构的方法
                publishProgress(downloadPercent);//调用此方法，接下来就会调用onProgressUpdate,传递的参数就是downloadPercent
                if (downloadPercent >= 90) {
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 任务开始前
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("hh", "任务开始前");
    }

    /**
     * 任务更新中
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("hh", "任务更新进度：" + String.valueOf(values[0]));
    }

    /**
     * 任务执行结束
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.d("hh", "任务执行结果：" + aBoolean);
    }


    private int doDownload() {
        return (int) (Math.random() * 100);
    }
}
