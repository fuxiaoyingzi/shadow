package com.example.administrator.shadowapplication.map.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.administrator.shadowapplication.BR;

/**
 * Author : shadow
 * Desc : Android提供的定位服务api model
 * Date :2018/3/28/028
 */

public class LocationModel extends BaseObservable{
    private double lat; //纬度
    private double lng; //经度
    private String mStringAdd;


    public LocationModel(double lat, double lng, String stringAdd) {
        this.lat = lat;
        this.lng = lng;
        mStringAdd = stringAdd;
    }

    public LocationModel() {
    }

    @Bindable
    public String getLat() {
        return "纬度："+lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
        notifyPropertyChanged(BR.lat);
    }

    @Bindable
    public String getLng() {
        return "经度："+lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
        notifyPropertyChanged(BR.lng);
    }

    @Bindable
    public String getStringAdd() {
        return "转换后的地址："+mStringAdd;
    }

    public void setStringAdd(String stringAdd) {
        mStringAdd = stringAdd;
        notifyPropertyChanged(BR.stringAdd);
    }
}
