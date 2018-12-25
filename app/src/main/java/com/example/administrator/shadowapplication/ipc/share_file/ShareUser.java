package com.example.administrator.shadowapplication.ipc.share_file;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : shadow
 * Desc :进程通信 之文件共享 对象
 * Date :2018/12/25/025
 */
public class ShareUser implements Parcelable {
    private int userId;
    private String userName;
    private String userAddress;

    public ShareUser(int userId, String userName, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    protected ShareUser(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        userAddress = in.readString();
    }

    public static final Creator<ShareUser> CREATOR = new Creator<ShareUser>() {
        @Override
        public ShareUser createFromParcel(Parcel in) {
            return new ShareUser(in);
        }

        @Override
        public ShareUser[] newArray(int size) {
            return new ShareUser[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress == null ? "" : userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(userAddress);
    }
}
