package com.example.administrator.shadowapplication.Gallery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/9/009
 */

public class GalleryFragment extends Fragment {

    public static GalleryFragment getInstance(PagerBean viewPagerListBean) {
        GalleryFragment galleryFragment = new GalleryFragment();
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

        PagerBean viewPagerListBean = (PagerBean) getArguments().getSerializable("bean");
        if (viewPagerListBean == null) return null;
        galleryImageView.setImageResource(viewPagerListBean.getImageUrl());
        String type;
        if (viewPagerListBean.getType() == GalleryListBean.TYPE_GOODS) {
            type = "商品详情";
        } else {
            type = "媒体详情";
        }
        desc.setText(viewPagerListBean.getMainId() + "----" + viewPagerListBean.getSubId() + "---" + type);
        return view;
    }
}
