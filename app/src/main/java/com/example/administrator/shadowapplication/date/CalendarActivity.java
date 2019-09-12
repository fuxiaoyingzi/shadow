package com.example.administrator.shadowapplication.date;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;
import com.example.administrator.shadowapplication.date.decorators.MySelectorDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;
import java.util.Date;

import static com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_MULTIPLE;

public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener {

    MaterialCalendarView mMaterialCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mMaterialCalendarView = findViewById(R.id.calendarView);
        mMaterialCalendarView.setOnDateChangedListener(this);
        mMaterialCalendarView.setOnMonthChangedListener(this);
        mMaterialCalendarView.setSelectedDate(new Date());
        mMaterialCalendarView.setSelectionMode(SELECTION_MODE_MULTIPLE);
        mMaterialCalendarView.addDecorator(new MySelectorDecorator(this));


    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        ToastUtil.showMsg("第几天："+date.getCalendar().get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        ToastUtil.showMsg("onMonthChanged");

    }
}
