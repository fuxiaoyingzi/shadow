package com.example.administrator.shadowapplication.date;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridView;

import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateActivity extends AppCompatActivity {

    private GridView mGridView;
    private List<DayBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        mGridView = findViewById(R.id.gridView);
        beans = new ArrayList<>();
        setCalendarList(System.currentTimeMillis());
    }

    private void setCalendarList(long millSecs) {
        beans.clear();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millSecs);
        int max = DateUtil.getCurrentMonthDay(millSecs);
        /**
         * 当月日历
         */
        for (int i = 1; i <= max; i++) {
            DayBean bean = new DayBean();
            Calendar cc = Calendar.getInstance();
            cc.setTimeInMillis(millSecs);
            cc.add(Calendar.DAY_OF_MONTH, i-2);
            setBean(bean, cc);
            bean.setCurrentMonth(true);
            beans.add(bean);
        }
        //上月结余
        /*int fir_day_of_week = beans.get(0).getCalendar().get(Calendar.DAY_OF_WEEK);
        Log.e("AAA", "week_last:" + fir_day_of_week);
        if (fir_day_of_week != 2) {
            if (fir_day_of_week == 1) {
                for (int i = 0; i < 6; i++) {
                    DayBean bean = new DayBean();
                    Calendar cc = Calendar.getInstance();
                    cc.setTimeInMillis(millSecs);
                    cc.add(Calendar.DAY_OF_MONTH, -i - 1);
                    setBean(bean, cc);
                    bean.setCurrentMonth(false);
                    beans.add(0, bean);
                }
            } else {
                for (int i = 0; i < fir_day_of_week - 2; i++) {
                    DayBean bean = new DayBean();
                    Calendar cc = Calendar.getInstance();
                    cc.setTimeInMillis(millSecs);
                    cc.add(Calendar.DAY_OF_MONTH, -i - 1);
                    setBean(bean, cc);
                    bean.setCurrentMonth(false);
                    beans.add(0, bean);
                }
            }

        }*/
        //下月
      /*  int last_day_of_week = beans.get(beans.size() - 1).getCalendar().get(Calendar.DAY_OF_WEEK);
        Log.e("AAA", "week_next:" + last_day_of_week);
        if (last_day_of_week != 1) {
            for (int i = last_day_of_week; i < 8; i++) {
                DayBean bean = new DayBean();
                Calendar cc = Calendar.getInstance();
                cc.setTimeInMillis(millSecs);
                cc.add(Calendar.DAY_OF_MONTH, i - last_day_of_week);
                setBean(bean, cc);
                bean.setCurrentMonth(false);
                beans.add(bean);
            }
        }*/
        GridViewAdapter adapter = new GridViewAdapter(this, beans);
        mGridView.setAdapter(adapter);
    }

    private void setBean(DayBean bean, Calendar cc) {
        bean.setCalendar(cc);
        bean.setDayOfMonth(cc.get(Calendar.DAY_OF_MONTH));
    }


}
