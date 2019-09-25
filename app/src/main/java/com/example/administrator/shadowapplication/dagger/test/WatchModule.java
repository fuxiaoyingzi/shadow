package com.example.administrator.shadowapplication.dagger.test;

import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author 付影影
 * @desc
 * @date 2019/9/24
 */
@Module
public class WatchModule {
    @Provides
    public Watch provideWatch() {
        return new Watch();
    }


    /**
     * @return
     * @Named 来区分 不同实现
     */
    @Provides
    @Named("one")
    public DaggerInterface providerInterfaceImpl() {
        return new DaggerInterfaceImpl();
    }


    @Provides
    @Named("two")
    public DaggerInterface providerInterfaceImpl2() {
        return new DaggerInterfaceImpl2();
    }

    /**
     * 使用@Qualifier 自定义的注解，来区分不同实现
     *
     * @return
     */
    @Provides
    @CustomInject
    public DaggerInterface providerInterfaceImpl3() {
        return new DaggerInterfaceImpl3();
    }

    /**
     * 提供第三方库的注入
     *
     * @return
     */
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
