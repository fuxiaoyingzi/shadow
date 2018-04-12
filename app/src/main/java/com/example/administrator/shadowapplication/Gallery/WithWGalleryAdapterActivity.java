package com.example.administrator.shadowapplication.Gallery;

import android.widget.BaseAdapter;

import com.example.administrator.shadowapplication.Gallery.adapter.WGalleryAdapter;


/**
 * 实现 IWGalleryAdapter 的示例页面。
 *
 * @author wuzhen
 * @version Version 1.0, 2016-05-10
 */
public class WithWGalleryAdapterActivity extends BaseWGalleryActivity {

    @Override
    protected BaseAdapter initGalleryAdapter() {
        return new WGalleryAdapter();
    }
}
