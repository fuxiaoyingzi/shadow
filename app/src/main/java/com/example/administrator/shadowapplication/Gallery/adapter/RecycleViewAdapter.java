package com.example.administrator.shadowapplication.Gallery.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : shadow
 * Desc :
 * Date :2018/5/14/014
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9));
    private Context mContext;
    private static final int TYPE_SEL = 1;
    private static final int TYPE_NORMAL = 2;
    private int selectItem;

    public RecycleViewAdapter(Context context) {
        mContext = context;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_NORMAL) {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_sample, parent, false);
            viewHolder = new RecycleViewHolder(convertView);
        } else {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_selete, parent, false);
            viewHolder = new RecycleViewSelect(convertView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecycleViewHolder) {
            ((RecycleViewHolder) holder).iv.setImageResource(mDatas.get(position));
        } else {
            ((RecycleViewSelect) holder).iv.setImageResource(mDatas.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (selectItem == position) {
            return TYPE_SEL;
        } else {
            return TYPE_NORMAL;
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    public class RecycleViewSelect extends RecyclerView.ViewHolder {

        ImageView iv;

        public RecycleViewSelect(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
