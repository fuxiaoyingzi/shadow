package com.example.administrator.shadowapplication.dagger;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import javax.inject.Inject;


/**
 * Created by Administrator on 2017/11/8.
 */

public class FlowerFragment extends DaggerFragment {
    private TextView textView;
    @Inject
    public Pot pot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower_dragger,container,false);
        textView = (TextView) view.findViewById(R.id.fragment_flowerTv);
        textView.setText("fragment = "+pot.showFlower());
        return view;
    }
}
