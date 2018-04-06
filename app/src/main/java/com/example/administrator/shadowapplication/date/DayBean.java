package com.example.administrator.shadowapplication.date;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/2/002
 */

public class DayBean implements Parcelable {
    private int eventId;
    private int dayOfMonth;
    private Calendar mCalendar;
    private boolean IsCurrentMonth;



    public DayBean() {
    }


    protected DayBean(Parcel in) {
        eventId = in.readInt();
        dayOfMonth = in.readInt();
        IsCurrentMonth = in.readByte() != 0;
    }

    public static final Creator<DayBean> CREATOR = new Creator<DayBean>() {
        @Override
        public DayBean createFromParcel(Parcel in) {
            return new DayBean(in);
        }

        @Override
        public DayBean[] newArray(int size) {
            return new DayBean[size];
        }
    };

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    public boolean isCurrentMonth() {
        return IsCurrentMonth;
    }

    public void setCurrentMonth(boolean currentMonth) {
        IsCurrentMonth = currentMonth;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(eventId);
        parcel.writeInt(dayOfMonth);
        parcel.writeByte((byte) (IsCurrentMonth ? 1 : 0));
    }
}
