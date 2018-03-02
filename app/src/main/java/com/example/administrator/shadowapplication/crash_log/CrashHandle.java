package com.example.administrator.shadowapplication.crash_log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.v4.BuildConfig;
import android.support.v4.app.NavUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/2
 *     desc   :
 * </pre>
 */


public class CrashHandle implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "crash";
    private static final boolean DEBUG = true;
    private static final String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/crash/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private static CrashHandle crashHandle = new CrashHandle();
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private Context mContext;
    private CrashListener crashListener;
    private static final ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();

    private CrashHandle() {
    }

    public static CrashHandle getCrashHandleInstance() {
        return crashHandle;
    }

    public void init(Context context,CrashListener crashListener){
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
        this.crashListener = crashListener;
    }

    /**
     * 当系统有未捕获的异常，会自动调用此方法
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        writeToSDCard(t,e);
        Future<?>future =  THREAD_POOL.submit(new Runnable() {
            @Override
            public void run() {
                if (crashListener != null){
                    crashListener.afterSaveLog(e);
                }
            }
        });
        if (!future.isDone()){
            try {
                future.get();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        e.printStackTrace();
        if(defaultUncaughtExceptionHandler != null){
            defaultUncaughtExceptionHandler.uncaughtException(t,e);
        }else {
            Process.killProcess(Process.myPid());
        }
    }

    /**
     * 保存到crash信息到本地sd卡
     * @param t
     * @param e
     */
    private void writeToSDCard(Thread t, Throwable e){
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if (DEBUG){
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }

        long currentTime = System.currentTimeMillis();
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date(currentTime));
        File file = new File(PATH+FILE_NAME+time+FILE_NAME_SUFFIX);

        PrintWriter printWriter = null;
        try {
             printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            printWriter.println(time);
            dumpPhoneInfo(printWriter,t,e);
            printWriter.println();
            e.printStackTrace(printWriter);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        finally {
            if ( printWriter!= null){
                printWriter.close();
            }
        }

    }

    private void dumpPhoneInfo(PrintWriter printWriter,Thread thread, Throwable ex){
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
            printWriter.print("APP Version");
            printWriter.print(info.versionCode);
            printWriter.print("-");
            printWriter.print(info.versionName);
            printWriter.println();
            //android 版本号
            printWriter.print("OS Version:");
            printWriter.print(Build.MANUFACTURER);
            printWriter.print("-");
            printWriter.print(Build.DEVICE);
            printWriter.print("-");
            printWriter.print(Build.MODEL);
            printWriter.print("-");
            printWriter.print(Build.VERSION.RELEASE);
            printWriter.print("-");
            printWriter.print(BuildConfig.VERSION_NAME);
            printWriter.println();
            printWriter.print(thread.toString());
            printWriter.print(ex.getMessage());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
