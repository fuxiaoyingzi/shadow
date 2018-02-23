package com.example.administrator.shadowapplication.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/8
 *     desc   :AIDL基本类
 * </pre>
 */
public class Book implements Parcelable {
    private long bookId;
    private String bookName;


    public Book(long bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Book() {
    }

    protected Book(Parcel in) {
        bookId = in.readLong();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(bookId);
        dest.writeString(bookName);
    }
}
