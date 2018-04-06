package com.example.administrator.shadowapplication.date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/2/002
 */

public class GridViewAdapter extends BaseAdapter {
    private List<DayBean> mDayBeans;
    private Context mContext;
    private LayoutInflater mInflater;

    public GridViewAdapter(Context context, List<DayBean> dayBeans) {
        mDayBeans = new ArrayList<>();
        mDayBeans = dayBeans;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDayBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mDayBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (mDayBeans == null || mDayBeans.size() == 0)
            return null;
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_date_gridview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.day.setText(String.valueOf(mDayBeans.get(i).getDayOfMonth()));
        if (i % 3 == 0) {
            // holder.day.setBackgroundColor(mContext.getResources().getColor(R.color.md_pink_200));
            holder.day.setBackground(mContext.getResources().getDrawable(R.drawable.date_item_bg));
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView day;

        public ViewHolder(View view) {
            day = view.findViewById(R.id.day);
        }
    }
}
