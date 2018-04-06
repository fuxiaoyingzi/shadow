package com.example.administrator.shadowapplication.date.decorators;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.administrator.shadowapplication.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Use a custom selector
 */
public class MySelectorDecorator implements DayViewDecorator {

    private final Drawable drawable;

    public MySelectorDecorator(Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.like);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.getDay() % 3 == 0;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
