package com.example.administrator.shadowapplication.http.retrofit;

import android.os.Environment;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/8
 *     desc   :
 * </pre>
 */


public class RetrofitUtil {
    private  Retrofit retrofit;

    private static RetrofitUtil retrofitUtil;
    public static RetrofitUtil getInstance(){
        if (retrofitUtil == null){
            synchronized (RetrofitUtil.class){
                if (retrofitUtil == null){
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }

    private RetrofitUtil(){
        String dir = Environment.getDataDirectory()+"/shadow/cache";
        File cacheDir = new File(dir.toString());
        long maxSize = 1024 * 1024 * 10;//10M

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .cache(new Cache(cacheDir, maxSize))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    //获取相应的APIService对象
    public <T> T getAPIService(Class<T> service) {
        return retrofit.create(service);
    }


}
