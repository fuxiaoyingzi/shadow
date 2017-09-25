package com.example.administrator.shadowapplication.service_demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/7/31.
 */

public class ServiceUser implements Parcelable {
    String name;
    private int age;
    private String address;
    private float weight;

    protected ServiceUser(Parcel in) {
        name = in.readString();
        age = in.readInt();
        address = in.readString();
        weight = in.readFloat();
    }

    public static final Creator<ServiceUser> CREATOR = new Creator<ServiceUser>() {
        @Override
        public ServiceUser createFromParcel(Parcel in) {
            return new ServiceUser(in);
        }

        @Override
        public ServiceUser[] newArray(int size) {
            return new ServiceUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(address);
        dest.writeFloat(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
