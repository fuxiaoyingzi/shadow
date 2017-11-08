package com.example.administrator.shadowapplication.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Administrator on 2017/11/7.
 */

@Module (includes = {FragmentModel.class,FlowerModule.class})
public abstract class ActivityModule {

    @ContributesAndroidInjector
    public abstract FlowerActivity provideFlowerActivity();
}
