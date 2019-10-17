package com.example.administrator.shadowapplication.http.intercept;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public interface Interceptor {
    String interceptor(Chain chain);

    interface Chain {
        String request();

        String proceed(String request);
    }
}
