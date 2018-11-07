package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author : shadow
 * Desc : 商品主图
 * Date :2018/6/24/024
 */

public class GoodsImageBean implements Serializable {


    /**
     * id : 1
     * product_id : 1
     * type : 1
     * media_url : http://img.ypcang.com/product/11.jpg
     * rela_id : p1n0
     * label_ls : [{"id":"1","product_media_id":"1","name":"买一件","left_offset":"10","top_offset":"50","add_time":"2018-06-13 15:57:33"}]
     */

    private String id;
    private String product_id;
    private String type;
    private int media_url;
    private String rela_id;
    private List<GoodsLabelBean> label_ls;

    public GoodsImageBean(String product_id, String type, int media_url, String rela_id) {
        this.product_id = product_id;
        this.type = type;
        this.media_url = media_url;
        this.rela_id = rela_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMedia_url() {
        return media_url;
    }

    public void setMedia_url(int media_url) {
        this.media_url = media_url;
    }

    public String getRela_id() {
        return rela_id;
    }

    public void setRela_id(String rela_id) {
        this.rela_id = rela_id;
    }

    public List<GoodsLabelBean> getLabel_ls() {
        return label_ls;
    }

    public void setLabel_ls(List<GoodsLabelBean> label_ls) {
        this.label_ls = label_ls;
    }

}
