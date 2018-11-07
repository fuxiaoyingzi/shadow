package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author : shadow
 * Desc : 媒体详情
 * Date :2018/6/28/028
 */

public class MedalDetailBean implements Serializable {
    /**
     * id : 25
     * text : 现在时刻:2018-06-24 10:30:05
     * resource : [{"pic_url":"http://img.ypcang.com/3.jpg","video_url":"","rela_id":"r25n0"}]
     * type : 1
     * radio_praise : 0
     */

    private String id;
    private String text;
    private String type;
    private String radio_praise;
    private List<MedalImageBean> resource;

    public MedalDetailBean(String id,  String type, String radio_praise, List<MedalImageBean> resource) {
        this.id = id;
        this.type = type;
        this.radio_praise = radio_praise;
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRadio_praise() {
        return radio_praise;
    }

    public void setRadio_praise(String radio_praise) {
        this.radio_praise = radio_praise;
    }

    public List<MedalImageBean> getResource() {
        return resource;
    }

    public void setResource(List<MedalImageBean> resource) {
        this.resource = resource;
    }



}
