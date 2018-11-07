package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 商品标签
 * Date :2018/6/22/022
 */

public class GoodsLabelBean implements Serializable{
    /**
     * {
     "id": "1", //标签的id
     "product_media_id": "1", //媒体文件的id
     "name": "买一件", //标签的内容
     "left_offset": "10", //距离左边的百分比
     "top_offset": "50", //距离上边的百分比
     "add_time": "2018-06-13 15:57:33" //标签添加的时间
     }
     */

    private String id;
    private String product_media_id;
    private String name;
    private String left_offset;
    private String top_offset;
    private String add_time;
    public static final int LABEL_TYPE_CONTENT = 1; //内容性质标签，由直播员控制位置内容，跳转尺码选择页面
    public static final int LABEL_TYPE_BUY = 2;    //购买性质标签，由直播员控制位置内容，跳转尺码选择页面
    public static final int LABEL_TYPE_EMPTY = 3; //售光标签， 位置随机 与购买性标签互斥，跳转尺码选择（需要服务器给相应的文字内容）
    public static final int LABEL_TYPE_ORDER = 4; //订单标签， 位置随机 跳转订单详情页（需要服务器给用户购买数量）
    public static final int LABEL_TYPE_GOODS = 5; //商品标签， 位置随机 跳转商品详情页
    public static final int LABEL_TYPE_NORMAL = 4; //普通标签， 位置随机 点击无反应
    private int labelType;//标签类型

    public GoodsLabelBean(String name, String left_offset, String top_offset, int labelType) {
        this.name = name;
        this.left_offset = left_offset;
        this.top_offset = top_offset;
        this.labelType = labelType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_media_id() {
        return product_media_id;
    }

    public void setProduct_media_id(String product_media_id) {
        this.product_media_id = product_media_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeft_offset() {
        return left_offset;
    }

    public void setLeft_offset(String left_offset) {
        this.left_offset = left_offset;
    }

    public String getTop_offset() {
        return top_offset;
    }

    public void setTop_offset(String top_offset) {
        this.top_offset = top_offset;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getLabelType() {
        return labelType;
    }

    public void setLabelType(int labelType) {
        this.labelType = labelType;
    }
}
