package com.example.administrator.shadowapplication.dagger.test;

import dagger.Component;

/**
 * @author 付影影
 * @desc
 * @date 2019/9/24
 */
@Component(modules = WatchModule.class)
public interface WatchComponent {
    /**
     * 完成依赖注入，在编译后会生成名为DaggerWatchComponent
     * @param activity
     */
    void inject(WatchActivity activity);
}
