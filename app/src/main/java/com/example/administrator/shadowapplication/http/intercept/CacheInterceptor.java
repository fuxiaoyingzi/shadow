package com.example.administrator.shadowapplication.http.intercept;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public String interceptor(Chain chain) {
        System.out.println("执行 CacheInterceptor 最后一个拦截器 返回最终数据");
        return "success";
    }
}
