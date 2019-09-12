package com.example.administrator.shadowapplication.dagger;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/11/7.
 */

public class FlowerActivity extends BaseDaggerActivity {

    @Inject
    Pot pot;
    private TextView flowerTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_flower_dragger);
      /*  FlowerComponent component = DaggerFlowerComponent.builder().build();
        component.inject(this);*/

        flowerTv = (TextView) findViewById(R.id.flowerTv);
        flowerTv.setText(pot.showFlower());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flower_frameLayout,new FlowerFragment())
                .commit();
    }
}
