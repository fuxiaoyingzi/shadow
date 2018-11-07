package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 媒体图片列表
 * Date :2018/6/28/028
 */

public class MedalImageBean implements Serializable {
    /**
     * pic_url : http://img.ypcang.com/3.jpg
     * video_url :
     * rela_id : r25n0
     */

    private int pic_url;
    private String video_url;
    private String rela_id;

    public MedalImageBean(int pic_url,  String rela_id) {
        this.pic_url = pic_url;
        this.rela_id = rela_id;
    }

    public int getPic_url() {
        return pic_url;
    }

    public void setPic_url(int pic_url) {
        this.pic_url = pic_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getRela_id() {
        return rela_id;
    }

    public void setRela_id(String rela_id) {
        this.rela_id = rela_id;
    }

}
