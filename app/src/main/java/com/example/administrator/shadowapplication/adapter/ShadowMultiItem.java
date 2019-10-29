package com.example.administrator.shadowapplication.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/23
 */
public class ShadowMultiItem implements MultiItemEntity {

    public static final int MULTI_TYPE_ONE = 1;
    public static final int MULTI_TYPE_TWO = 2;
    public static final int MULTI_TYPE_THREE = 3;
    public static final int MULTI_TYPE_FOUR = 4;


    private int type;

    private Shadow mShadow;

    public Shadow getShadow() {
        return mShadow;
    }

    public void setShadow(Shadow shadow) {
        mShadow = shadow;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
