package com.example.administrator.shadowapplication.http;

import android.os.Environment;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/27
 *     desc   :
 * </pre>
 */


public class HttpUtil {
    private static OkHttpClient client;
    private static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getDataDirectory());
        stringBuilder.append(File.separator);
        stringBuilder.append("shadow/cache");
        File directory = new File(stringBuilder.toString());
        long maxSize = 1024 * 1024 * 10;//10M
        builder.cache(new Cache(directory, maxSize));//设置缓存

       /* if (showLog()){
            builder.addInterceptor(new HttpLoggingInterceptor());
        }*/
        client = builder.build();

    }

    private static Request buildRequest(){
        if (client == null) {
            init();
        }
        Request.Builder builder = new Request.Builder()
                .addHeader("Accept","application/json")
                .addHeader("content-type","application/json")
                .url("http://www.baidu.com/");
        return builder.build();
    }

    private static boolean ensureMakeDirResult(File directory) {
        return directory.exists() && directory.isDirectory() || directory.mkdir();
    }

    private static boolean showLog(){
        return true;
    }

    public static Call testHttp(){
        Request request = buildRequest();
        return client.newCall(request);
    }


}
