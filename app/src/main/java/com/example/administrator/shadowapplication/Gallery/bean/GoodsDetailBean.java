package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author : shadow
 * Desc :
 * Date :2018/6/28/028
 */

public class GoodsDetailBean implements Serializable {
    /**
     * {
         * "id": "1", //产品id
         "seller_id": "1", //直播员（代购）id
         "seller_group_id": "1", //直播小组id
         "freight_template_id": "1", //运费模板id
         "store_name": "存储名", //所在仓库
         "product_no": "201806131452", //产品编号
         "text": "一个商品简介", //文字简介
         "brand_id": "1", //品牌ID
         "brand_name": "报喜鸟", //品牌名
         "discount": "88", //折扣
         "is_refundable": "1", //是否支持7天退货
         "on_sale": "1", //是否正在上架
         "add_time": "2018-06-13 14:53:36", //产品添加的时间
         "send_type": "1", //产品的发布类型 1传统发布2极速发布3智慧仓库发布 暂时还没有第三种方式发布的产品
         "longitude": "111", //经度
         "dimension": "222", //纬度
         "box_no": "1A2B", //产品所在的库位号
         "commodity_code": "123", //产品的69码
         "company_code": "123", //产品的货号（厂家定义的编号）
         "weight": "11", //产品的重量
         "sell_out": "0", //是否已售罄，售罄时goods_ls里的所有库存都为0
         "seller_nickname": "xiaoming", //直播员昵称
         "brand_logo_url": "http://img.ypcang.com/brand/bxn.jpg";, //品牌LOGO的URL
         "seller_profile_pic_url": "http://img.ypcang.com/41.jpg";, //直播员的头像
     ========================================================
         "goods_ls": //产品所拥有的单品列表
             [
                 {
                     "id": "1", //单品id
                     "product_id": "1", //产品id
                     "original_price": "11.20", //原价（吊牌价）
                     "current_price": "9.90", //现价（售价，订单金额计算使用的此价格）
                     "stock_qty": "5", //库存还有多少件（产品为售罄时此字段为0）
                     "commodity_code": "9691235", //单品的69码
                     "company_code": "S12345677", //货号，厂家定义的编码
                     "weight": "11", //重量
                     "box_no": "12", //库位号
                     "spec_name": "颜色", //规格名
                     "spec_value": "红色", //规格值
                     "spec_pic_url": "http://img.ypcang.com/123.jpg"; //规格图的url
                 }
             ],
     =========================================================
         "media_ls": //产品的媒体文件，目前只有主图片（也就是主图）
             [
                 {
                 "id": "1", //媒体的ID
                 "product_id": "1", //产品id
                 "type": "1", //媒体类型 1是图片
                 "media_url": "http://img.ypcang.com/product/11.jpg";, //媒体文件的url
                 "rela_id":"p1n0", //用来和detailSlideLs接口的图片进行一一对应
                 "label_ls": //媒体文件对应标签
                     [
                         {
                         "id": "1", //标签的id
                         "product_media_id": "1", //媒体文件的id
                         "name": "买一件", //标签的内容
                         "left_offset": "10", //距离左边的百分比
                         "top_offset": "50", //距离上边的百分比
                         "add_time": "2018-06-13 15:57:33" //标签添加的时间
                         }
                     ]
                 }
             ],
         "product_favorite": "0", //产品是否已被用户收藏
         "brand_follow": "1", //品牌是否已被用户订阅
     =================================================
         "about": //相关推荐的产品，最多4个
             [
                { "id": "1", //产品ID
                 "add_time": "2018-06-13 14:53:36", //产品添加的时间
                 "store_name": "仓库名", //仓库名
                 "media_url": "http://img.ypcang.com/product/11.jpg"; //首图
                 }
             ]
     }
     */
    private String id;
    private String seller_id;
    private String seller_group_id;
    private String freight_template_id;
    private String store_name;
    private String product_no;
    private String text;
    private String brand_id;
    private String brand_name;
    private String discount;
    private String is_refundable;
    private String note;
    private String on_sale;
    private String add_time;
    private String send_type;
    private String longitude;
    private String dimension;
    private String box_no;
    private String commodity_code;
    private String company_code;
    private String weight;
    private String sell_out;
    private String seller_nickname;
    private String brand_logo_url;
    private String seller_profile_pic_url;
    private String product_favorite;
    private String brand_follow;
    private List<GoodsSpecificationBean> goods_ls; //商品规格
    private List<GoodsImageBean> media_ls; //商品主图列表
    private List<RecommendGoodsBean> about; //商品推荐列表

    public GoodsDetailBean(String id, List<GoodsImageBean> media_ls) {
        this.id = id;
        this.media_ls = media_ls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_group_id() {
        return seller_group_id;
    }

    public void setSeller_group_id(String seller_group_id) {
        this.seller_group_id = seller_group_id;
    }

    public String getFreight_template_id() {
        return freight_template_id;
    }

    public void setFreight_template_id(String freight_template_id) {
        this.freight_template_id = freight_template_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIs_refundable() {
        return is_refundable;
    }

    public void setIs_refundable(String is_refundable) {
        this.is_refundable = is_refundable;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOn_sale() {
        return on_sale;
    }

    public void setOn_sale(String on_sale) {
        this.on_sale = on_sale;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getBox_no() {
        return box_no;
    }

    public void setBox_no(String box_no) {
        this.box_no = box_no;
    }

    public String getCommodity_code() {
        return commodity_code;
    }

    public void setCommodity_code(String commodity_code) {
        this.commodity_code = commodity_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSell_out() {
        return sell_out;
    }

    public void setSell_out(String sell_out) {
        this.sell_out = sell_out;
    }

    public String getSeller_nickname() {
        return seller_nickname;
    }

    public void setSeller_nickname(String seller_nickname) {
        this.seller_nickname = seller_nickname;
    }

    public String getBrand_logo_url() {
        return brand_logo_url;
    }

    public void setBrand_logo_url(String brand_logo_url) {
        this.brand_logo_url = brand_logo_url;
    }

    public String getSeller_profile_pic_url() {
        return seller_profile_pic_url;
    }

    public void setSeller_profile_pic_url(String seller_profile_pic_url) {
        this.seller_profile_pic_url = seller_profile_pic_url;
    }

    public String getProduct_favorite() {
        return product_favorite;
    }

    public void setProduct_favorite(String product_favorite) {
        this.product_favorite = product_favorite;
    }

    public String getBrand_follow() {
        return brand_follow;
    }

    public void setBrand_follow(String brand_follow) {
        this.brand_follow = brand_follow;
    }

    public List<GoodsSpecificationBean> getGoods_ls() {
        return goods_ls;
    }

    public void setGoods_ls(List<GoodsSpecificationBean> goods_ls) {
        this.goods_ls = goods_ls;
    }

    public List<GoodsImageBean> getMedia_ls() {
        return media_ls;
    }

    public void setMedia_ls(List<GoodsImageBean> media_ls) {
        this.media_ls = media_ls;
    }

    public List<RecommendGoodsBean> getAbout() {
        return about;
    }

    public void setAbout(List<RecommendGoodsBean> about) {
        this.about = about;
    }



}
