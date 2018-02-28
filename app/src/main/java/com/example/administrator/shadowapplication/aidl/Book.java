package com.example.administrator.shadowapplication.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/28
 *     desc   :
 * </pre>
 */

public class Book  implements Parcelable{
    private long bookId;
    private String bookName;
    private float bookPrice;

    public Book() {
    }

    public Book(long bookId, String bookName, float bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }

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

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    protected Book(Parcel in) {
        bookId = in.readLong();
        bookName = in.readString();
        bookPrice = in.readFloat();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(bookId);
        dest.writeString(bookName);
        dest.writeFloat(bookPrice);
    }
}
