package com.example.administrator.shadowapplication.Gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;

public class GalleryActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Gallery mGallery;
    private ImageAdapter imageAdapter;
    private int[] myImageIds = {R.drawable.like, R.drawable.ic_my_selector, R.drawable.like, R.drawable.ic_my_selector, R.drawable.like,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageAdapter = new ImageAdapter(this);
        mGallery = findViewById(R.id.gallery);
        mImageView = findViewById(R.id.ivGallery);
        mGallery.setSpacing(10);
        mGallery.setAdapter(imageAdapter);
        mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageAdapter.setSelectItem(position);
                imageAdapter.notifyDataSetChanged();//当滑动时，事件响应，通知适配器更新数据
                mImageView.setImageResource(myImageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
