package com.example.administrator.shadowapplication.dagger;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/11/7.
 */

public class Pot {
    Flower flower;

    @Inject
    public Pot(@FlowerLily Flower flower) {
        this.flower = flower;
    }


    public String showFlower(){
        return flower.whisper();
    }
}
