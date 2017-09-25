package com.example.administrator.shadowapplication.sport.activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SportBean implements Parcelable {
    private String title;
    private String time = "60"; //默认60秒


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SportBean(String title) {
        this.title = title;
    }

    public SportBean(String title, String time) {
        this.title = title;
        this.time = time;
    }

    protected SportBean(Parcel in) {
        title = in.readString();
        time = in.readString();
    }

    public static final Creator<SportBean> CREATOR = new Creator<SportBean>() {
        @Override
        public SportBean createFromParcel(Parcel in) {
            return new SportBean(in);
        }

        @Override
        public SportBean[] newArray(int size) {
            return new SportBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(time);
    }
}
