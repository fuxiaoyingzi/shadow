package com.example.administrator.shadowapplication.Gallery;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 底部gallery数据
 * Date :2018/6/26/026
 */

public class PagerMedalBean implements Serializable {
    private String mainId;
    private String subId;
    private Integer imageUrl;

    public PagerMedalBean(String mainId, String subId, Integer imageUrl) {
        this.mainId = mainId;
        this.subId = subId;
        this.imageUrl = imageUrl;
    }

    public PagerMedalBean() {
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
