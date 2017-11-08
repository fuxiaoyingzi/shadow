package com.example.administrator.shadowapplication.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/11/7.
 */

@Module
public class FlowerModule {
    @Provides
    @FlowerRose
    Flower provideRose(){
        return new Rose();
    }

    @Provides
    @FlowerLily
    Flower provideLily(){
        return new Lily();
    }

}
