package com.example.administrator.shadowapplication.Gallery;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 商品和媒体共有的实体类
 * Date :2018/6/26/026
 */

public class PagerBean implements Serializable {
    private String mainId;
    private String subId;
    private int type;
    private Integer imageUrl;

    public PagerBean(String mainId, String subId, Integer imageUrl, int type) {
        this.mainId = mainId;
        this.subId = subId;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
