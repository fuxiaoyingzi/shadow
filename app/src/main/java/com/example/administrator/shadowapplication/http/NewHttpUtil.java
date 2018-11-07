package com.example.administrator.shadowapplication.http;

import android.content.Context;

import com.baidu.mapapi.http.HttpClient;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author : shadow
 * Desc :
 * Date :2018/5/31/031
 */

public class NewHttpUtil {
    private static NewHttpUtil mNewHttpUtil;
    private OkHttpClient mHttpClient;

    public static NewHttpUtil getInstance(Context context) {
        if (mNewHttpUtil == null) {
            synchronized (NewHttpUtil.class) {
                if (mNewHttpUtil == null) {
                    mNewHttpUtil = new NewHttpUtil(context);
                }
            }
        }
        return mNewHttpUtil;
    }


    private NewHttpUtil(Context context) {
        File file = context.getExternalCacheDir();
        int cacheSize = 10*1024*1024;
        assert file != null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .cache(new Cache(file,cacheSize));
        mHttpClient = builder.build();
    }

    public static final MediaType MEDIA_TYPE = MediaType.parse("text/x-markdowm;charset =utf-8;image/png");
    private void postFile(String path){
        File file = new File(path,"shadow.txt");
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE,file);
        RequestBody requestBody1 = new FormBody.Builder().add("name","shadow").build();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(requestBody)
                .build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.showMsg("上传文件失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    private void downloadImage(){
        Request request = new Request.Builder().url("http://www.baidu.com")
                .get()
                .build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                String filePath = "file/shadow/download/image";

                File file = new File(filePath,"shadow.jpg");
                fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1){
                    fileOutputStream.write(buffer,0,len);
                }
                fileOutputStream.flush();

            }
        });
    }

    /**
     * 异步上传图片
     * @param file
     */
    private void pushImage(String file){
       RequestBody requestBody = new MultipartBody.Builder()
               .setType(MultipartBody.FORM)
               .addFormDataPart("name","shadow")
               .addFormDataPart("image","shadow.jpg",RequestBody.create(MEDIA_TYPE,new File(file)))
               .build();

       Request request= new Request.Builder()
               .url("http://www.baidu.com")
               .post(requestBody)
               .build();
       mHttpClient.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {

           }
       });

    }
}
