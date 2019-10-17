package com.example.administrator.shadowapplication.design_pattern.iterator;

import java.util.logging.Handler;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/17
 */
public abstract class Leader {
    //上一级领导处理者
    protected Leader nextHandle;

    public final void handleRequest(int money){
        if (money <= limit()){
            handler(money);
        }else {
            if (null != nextHandle){
                nextHandle.handleRequest(money);
            }
        }
    }

    /**
     * 领导可以处理的范围限制
     * @return
     */
    protected abstract int limit();

    /**
     * 领导的处理结果
     * @param money
     */
    protected abstract void handler(int money);
}
