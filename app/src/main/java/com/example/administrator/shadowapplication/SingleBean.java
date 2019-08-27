package com.example.administrator.shadowapplication;

import android.content.Context;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author 付影影
 * @desc 静态内部类 实现单例
 * @date 2019/8/19
 */
public class SingleBean implements Serializable {
    private SingleBean() {
    }

    public static SingleBean getInstance() {
        return SingleHolder.mSingleBean;
    }

    private static class SingleHolder {
        private static final SingleBean mSingleBean = new SingleBean();
    }


    private Object readResolve(Context mContext) throws ObjectStreamException {
        return SingleHolder.mSingleBean;
    }

}
