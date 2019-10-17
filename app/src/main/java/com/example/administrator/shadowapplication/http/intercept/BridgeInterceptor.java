package com.example.administrator.shadowapplication.http.intercept;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public class BridgeInterceptor implements Interceptor {
    @Override
    public String interceptor(Chain chain) {
        System.out.println("执行 BridgeInterceptor 拦截器之前代码");
        String proceed = chain.proceed(chain.request());
        System.out.println("执行 BridgeInterceptor 拦截器之后代码 得到最终数据："+proceed);
        return proceed;
    }
}
