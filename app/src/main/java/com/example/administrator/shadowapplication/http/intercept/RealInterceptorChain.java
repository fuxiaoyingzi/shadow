package com.example.administrator.shadowapplication.http.intercept;

import java.util.List;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public class RealInterceptorChain implements Interceptor.Chain {

    private List<Interceptor> interceptors;

    private int index;

    private String request;

    public RealInterceptorChain(List<Interceptor> interceptors, int index, String request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public String request() {
        return request;
    }

    @Override
    public String proceed(String request) {
        if (index >= interceptors.size()) {
            return null;
        }

        //获取下一个责任链
        RealInterceptorChain next = new RealInterceptorChain(interceptors, index+1, request);
        // 执行当前的拦截器
        Interceptor interceptor = interceptors.get(index);

        return interceptor.interceptor(next);
    }
}
