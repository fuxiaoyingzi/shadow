package com.example.administrator.shadowapplication.nine_gridlayout;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : shadow
 * Desc : RecycleView adapter 基类
 * Date :2018/5/15/015
 */

public abstract class BaseListAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {
    /**
     * 列表数据集合
     */
    protected List<T> dataSet;
    public OnItemClickListener mOnItemClickListener;


    /**
     * Context上下文对象
     */
    protected Context mContext;
    protected LayoutInflater mInflater;
    private boolean loadingMore = false;
    private boolean loadMoreEnabled = false;
    private OnLoadEndListener onLoadEndListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public interface OnLoadEndListener {
        /**
         * 加载完成时调用
         */
        void onLoadEnd();
    }


    public BaseListAdapter(Context context) {
        this(context, null);
        mInflater = LayoutInflater.from(context);
    }

    public BaseListAdapter(Context context, List<T> dataSet) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null !");
        }
        this.mContext = context;
        this.dataSet = dataSet;
        if (dataSet == null) {
            this.dataSet = new ArrayList<>();
        }
    }

    public void setOnLoadEndListener(OnLoadEndListener onLoadEndListener) {
        this.onLoadEndListener = onLoadEndListener;
    }

    public void setLoadingMoreFinished() {
        loadingMore = false;
    }

    public void setLoadMoreEnabled(boolean loadMoreEnabled) {
        this.loadMoreEnabled = loadMoreEnabled;
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    /**
     * 更新数据集合，并提醒列表界面更新
     *
     * @param data  数据集
     * @param clear 是否清除集合原数据
     */
    public void update(List<T> data, boolean clear) {
        if (data == null)
            return;
        if (clear)
            this.dataSet.clear();
        this.dataSet.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getDataSet() {
        return dataSet;
    }

    /**
     * 插入新的数据对象
     *
     * @param obj 数据对象
     */
    public void insert(T obj) {
        dataSet.add(obj);
        notifyDataSetChanged();
    }

    /**
     * 插入新的数据对象
     *
     * @param location 插入位置
     * @param obj      数据对象
     */
    public void insert(int location, T obj) {
        dataSet.add(location, obj);
        notifyDataSetChanged();
    }

    /**
     * 插入新的数据对象
     *
     * @param location 插入位置
     * @param list     数据对象集合
     */
    public void insert(int location, List<T> list) {
        dataSet.addAll(location, list);
        notifyDataSetChanged();
    }

    /**
     * 插入新的数据对象集合
     *
     * @param list 数据集合
     */
    public void insert(List<T> list) {
        dataSet.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 删除数据集中指定位置的列表元素
     *
     * @param location 删除位置
     * @return 返回删除的元素
     */
    public T delete(int location) {
        T obj = dataSet.remove(location);
        notifyDataSetChanged();
        return obj;
    }

    /**
     * 删除数据集中指定的对象
     *
     * @param obj 删除的对象
     * @return 是否删除成功
     */
    public boolean delete(T obj) {
        boolean result = dataSet.remove(obj);
        if (result)
            notifyDataSetChanged();
        return result;
    }

    /**
     * 删除数据集中所有元素
     */
    public void deleteAll() {
        dataSet.clear();
        notifyDataSetChanged();
    }
}
