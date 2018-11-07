package com.example.administrator.shadowapplication.Gallery.bean;

import com.example.administrator.shadowapplication.multitype.Title;

import java.io.Serializable;
import java.util.List;

/**
 * Author : shadow
 * Desc : 产品详情和媒体详情的集合实体类
 * Date :2018/6/28/028
 */
public class PagerDataBean implements Serializable {
    private String id; //产品id ；媒体图片id； 视频id
    private String distinctionId; //区分id
    private String type; //1图文类型 2短视频 3产品
    private int imageUrl; //产品主图；媒体图片；视频缩略图
    private List<GoodsLabelBean> mLabelBeanList; //标签
    private List<RecommendGoodsBean> recommendGoodsList; //推荐列表
    private String title; //标题
    private String isCheck; // 商品详情 对应的是收藏，媒体详情对应的是  1是 0否
    private GoodsDetailBean mGoodsDetailBean; //产品详情
    private MedalDetailBean mMedalDetailBean; //媒体详情

    public PagerDataBean(String id, String distinctionId, String type, int imageUrl, List<GoodsLabelBean> labelBeanList, List<RecommendGoodsBean> recommendGoodsList, GoodsDetailBean goodsDetailBean, MedalDetailBean medalDetailBean) {
        this.id = id;
        this.distinctionId = distinctionId;
        this.type = type;
        this.imageUrl = imageUrl;
        mLabelBeanList = labelBeanList;
        this.recommendGoodsList = recommendGoodsList;
        mGoodsDetailBean = goodsDetailBean;
        mMedalDetailBean = medalDetailBean;
    }

    public PagerDataBean(String id, String distinctionId, String type, int imageUrl, String title, String isCheck, GoodsDetailBean goodsDetailBean, MedalDetailBean medalDetailBean) {
        this.id = id;
        this.distinctionId = distinctionId;
        this.type = type;
        this.imageUrl = imageUrl;
        this.title = title;
        this.isCheck = isCheck;
        mGoodsDetailBean = goodsDetailBean;
        mMedalDetailBean = medalDetailBean;
    }

    public PagerDataBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistinctionId() {
        return distinctionId;
    }

    public void setDistinctionId(String distinctionId) {
        this.distinctionId = distinctionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<GoodsLabelBean> getLabelBeanList() {
        return mLabelBeanList;
    }

    public void setLabelBeanList(List<GoodsLabelBean> labelBeanList) {
        mLabelBeanList = labelBeanList;
    }

    public List<RecommendGoodsBean> getRecommendGoodsList() {
        return recommendGoodsList;
    }

    public void setRecommendGoodsList(List<RecommendGoodsBean> recommendGoodsList) {
        this.recommendGoodsList = recommendGoodsList;
    }

    public GoodsDetailBean getGoodsDetailBean() {
        return mGoodsDetailBean;
    }

    public void setGoodsDetailBean(GoodsDetailBean goodsDetailBean) {
        mGoodsDetailBean = goodsDetailBean;
    }

    public MedalDetailBean getMedalDetailBean() {
        return mMedalDetailBean;
    }

    public void setMedalDetailBean(MedalDetailBean medalDetailBean) {
        mMedalDetailBean = medalDetailBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
