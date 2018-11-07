package com.example.administrator.shadowapplication.Gallery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shadowapplication.Gallery.bean.GalleryDataBean;
import com.example.administrator.shadowapplication.Gallery.bean.PagerDataBean;
import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/9/009
 */

public class GoodsDetailFragment extends Fragment {

    public static GoodsDetailFragment getInstance(PagerDataBean viewPagerListBean) {
        GoodsDetailFragment galleryFragment = new GoodsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", viewPagerListBean);
        galleryFragment.setArguments(bundle);
        return galleryFragment;
    }

    private ImageView galleryImageView;
    private TextView desc;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_viewpager_gallery, container, false);
        galleryImageView = view.findViewById(R.id.galleryImageView);
        desc = view.findViewById(R.id.desc);

        PagerDataBean viewPagerListBean = (PagerDataBean) getArguments().getSerializable("bean");
        if (viewPagerListBean == null) return null;
        galleryImageView.setImageResource(viewPagerListBean.getImageUrl());
        String type;
        if (viewPagerListBean.getType() == GalleryDataBean.TYPE_GOODS) {
            type = viewPagerListBean.getGoodsDetailBean().getText();
        } else if (viewPagerListBean.getType() == GalleryDataBean.TYPE_VEDIO){//只有一张视频缩略图
            type = viewPagerListBean.getMedalDetailBean().getText()+viewPagerListBean.getMedalDetailBean().getResource().get(0).getVideo_url();
        }else {
            type = viewPagerListBean.getMedalDetailBean().getText();
        }
        desc.setText(viewPagerListBean.getId() + "----" + viewPagerListBean.getDistinctionId() + "---" + type);
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg("hello shadow");
            }
        });
        return view;
    }
}
