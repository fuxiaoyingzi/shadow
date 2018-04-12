package com.example.administrator.shadowapplication.Gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/9/009
 */

public class GalleryFragment extends Fragment {
    public static GalleryFragment getInstance(int position){
        GalleryFragment galleryFragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        galleryFragment.setArguments(bundle);
        return galleryFragment;
    }
    private ImageView galleryImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_viewpager_gallery,container,false);
        galleryImageView = view.findViewById(R.id.galleryImageView);
        int position = getArguments().getInt("position");
        galleryImageView.setImageResource(
             getResources().getIdentifier("pic" + position, "drawable", getContext().getPackageName()));
        return view;
    }
}
