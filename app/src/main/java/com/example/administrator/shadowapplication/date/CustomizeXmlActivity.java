package com.example.administrator.shadowapplication.date;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomizeXmlActivity extends AppCompatActivity {

    @BindView(R.id.calendarView)
    MaterialCalendarView widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);
        ButterKnife.bind(this);
    }

}
