package com.example.administrator.shadowapplication.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/6/7.
 */

public class TestRecycleAdapter extends RecyclerView.Adapter<TestRecycleAdapter.MyViewHolder> {
    private String[] attay;
    private Context context;
    private List<String> dataList;
    private boolean dataIsList = false;

    public TestRecycleAdapter(String[] attay, Context context) {
        this.attay = attay;
        this.context = context;
        dataIsList = false;
    }

    public TestRecycleAdapter(List attay, Context context) {
        this.dataList = attay;
        this.context = context;
        dataIsList = true;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (dataIsList) {
            holder.subjectText.setText(dataList.get(position));
        } else {
            holder.subjectText.setText(attay[position]);
        }
    }

    @Override
    public int getItemCount() {
        return dataIsList ? dataList.size() : attay.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectText;

        public MyViewHolder(View itemView) {
            super(itemView);
            subjectText = (TextView) itemView.findViewById(R.id.subjectText);
        }
    }
}
