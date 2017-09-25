package com.example.administrator.shadowapplication.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.fragment.MaterialDesignFragment;
import com.example.administrator.shadowapplication.fragment.TestFragment;

public class MaterialDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        MaterialDesignFragment fragment = (MaterialDesignFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if (fragment == null){
            fragment = MaterialDesignFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameLayout,fragment);
            fragmentTransaction.commit();
        }
    }
}
