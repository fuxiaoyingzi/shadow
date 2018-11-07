package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 商品详情底部gallery 数据
 * Date :2018/6/28/028
 */

public class GalleryDataBean implements Serializable{

    /**
   {
     "pic_url": "http://img.ypcang.com/11.jpg";, //图片的url
     "type": "3", //1图文类型 2短视频 3产品
     "id": "27", //type为1时id为radioId 为2时id为radioId 为3时为产品id 1和2通过接口Detail/radioDetail 获取详情 3通过Detail/productDetail获取详情
     "leftId": "28", //上一个ID，没有数据时为0
     "rightId": "26", //下一个ID，没有数据时为0
     "radioId":"16", //调用当前接口的previous和next时使用
     "rela_id": "r2151n0" //仅仅用来和产品详情和媒体详情的图片在滑动时进行一一对应,且是唯一的
     }
     */

    private String id;
    private String leftId;
    private String rightId;
    private int radioId;
    private Integer pic_url; //为了测试，暂时设置为本地图片
    private String type;
    private String rela_id;
    public static final String TYPE_GOODS = "3"; //商品
    public static final String TYPE_MEDAL = "2"; //视频
    public static final String TYPE_VEDIO = "1"; //媒体图片

    public GalleryDataBean(String id, String leftId, String rightId,  Integer pic_url, String type, String rela_id) {
        this.id = id;
        this.leftId = leftId;
        this.rightId = rightId;
        this.pic_url = pic_url;
        this.type = type;
        this.rela_id = rela_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeftId() {
        return leftId;
    }

    public void setLeftId(String leftId) {
        this.leftId = leftId;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public int getRadioId() {
        return radioId;
    }

    public void setRadioId(int radioId) {
        this.radioId = radioId;
    }

    public Integer getPic_url() {
        return pic_url;
    }

    public void setPic_url(Integer pic_url) {
        this.pic_url = pic_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRela_id() {
        return rela_id;
    }

    public void setRela_id(String rela_id) {
        this.rela_id = rela_id;
    }
}
