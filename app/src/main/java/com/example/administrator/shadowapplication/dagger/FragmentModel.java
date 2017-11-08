package com.example.administrator.shadowapplication.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Administrator on 2017/11/8.
 */
@Module
public abstract class FragmentModel {
    @ContributesAndroidInjector
    abstract FlowerFragment provideFragment();
}
