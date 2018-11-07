package com.example.administrator.shadowapplication.Gallery;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 底部gallery数据
 * Date :2018/6/26/026
 */

public class GalleryListBean implements Serializable {
    private String mainId;
    private String subId;
    private Integer imageUrl;
    private int type;
    public static final int TYPE_GOODS = 1;
    public static final int TYPE_MEDAL = 2;


    public GalleryListBean(String mainId, String subId, Integer imageUrl, int type) {
        this.mainId = mainId;
        this.subId = subId;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public GalleryListBean() {
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
