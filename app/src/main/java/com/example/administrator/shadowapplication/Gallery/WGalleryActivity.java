package com.example.administrator.shadowapplication.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.shadowapplication.R;

/**
 * 首页。
 *
 * @author wuzhen
 * @version Version 1.0, 2016-05-10
 */
public class WGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxgallery);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, BaseWGalleryActivity.class));
                break;

            case R.id.btn2:
                startActivity(new Intent(this, BaseWGalleryActivity.class));
                break;

            default:
                break;
        }
    }

}
