package com.example.administrator.shadowapplication.databing;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrator.shadowapplication.BR;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2017/12/11
 *     desc   : dataBing bean
 * </pre>
 */


public class User extends BaseObservable implements Parcelable {
    private String name;
    private String sex;
    private int age;
    private String desc;

    public User() {
    }

    public User(String name, String sex, int age, String desc) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.desc = desc;
    }

    protected User(Parcel in) {
        name = in.readString();
        sex = in.readString();
        age = in.readInt();
        desc = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyPropertyChanged(BR.desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeInt(age);
        dest.writeString(desc);
    }
}
