package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 推荐商品
 * Date :2018/6/5/005
 */

public class RecommendGoodsBean implements Serializable {
    /**
     * id : 1
     * add_time : 2018-06-13 14:53:36
     * store_name : 仓库名
     * media_url : http://img.ypcang.com/product/11.jpg
     */

    private String id;
    private String add_time;
    private String store_name;
    private String media_url;

    public RecommendGoodsBean() {
    }

    public RecommendGoodsBean(String id, String add_time, String store_name, String media_url) {
        this.id = id;
        this.add_time = add_time;
        this.store_name = store_name;
        this.media_url = media_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }
}
