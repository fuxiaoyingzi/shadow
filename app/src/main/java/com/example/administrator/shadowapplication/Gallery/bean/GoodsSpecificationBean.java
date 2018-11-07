package com.example.administrator.shadowapplication.Gallery.bean;

import java.io.Serializable;

/**
 * Author : shadow
 * Desc : 商品规格实体类
 * Date :2018/6/28/028
 */

public class GoodsSpecificationBean implements Serializable {

        /**
         * id : 1
         * product_id : 1
         * original_price : 11.20
         * current_price : 9.90
         * stock_qty : 5
         * commodity_code : 9691235
         * company_code : S12345677
         * weight : 11
         * box_no : 12
         * spec_name : 颜色
         * spec_value : 红色
         * spec_pic_url : http://img.ypcang.com/123.jpg
         */

        private String id;
        private String product_id;
        private String original_price;
        private String current_price;
        private String stock_qty;
        private String commodity_code;
        private String company_code;
        private String weight;
        private String box_no;
        private String spec_name;
        private String spec_value;
        private String spec_pic_url;

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

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public String getStock_qty() {
            return stock_qty;
        }

        public void setStock_qty(String stock_qty) {
            this.stock_qty = stock_qty;
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

        public String getBox_no() {
            return box_no;
        }

        public void setBox_no(String box_no) {
            this.box_no = box_no;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public String getSpec_value() {
            return spec_value;
        }

        public void setSpec_value(String spec_value) {
            this.spec_value = spec_value;
        }

        public String getSpec_pic_url() {
            return spec_pic_url;
        }

        public void setSpec_pic_url(String spec_pic_url) {
            this.spec_pic_url = spec_pic_url;
        }

}
