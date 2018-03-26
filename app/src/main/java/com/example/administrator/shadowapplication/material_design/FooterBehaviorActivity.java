package com.example.administrator.shadowapplication.material_design;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.activity.TestRecycleViewActivity;
import com.example.administrator.shadowapplication.adapter.TestRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class FooterBehaviorActivity extends AppCompatActivity {
    private TestRecycleAdapter adapter;
    private String[] stringArray;
    private List itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_behavior);
        Toolbar toolbar = findViewById(R.id.toolbarLayout);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingLayout);
        collapsingToolbarLayout.setTitle("hello shadow");
        RecyclerView recyclerView = findViewById(R.id.recycleViewLayout);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(FooterBehaviorActivity.this,DividerItemDecoration.VERTICAL));
        itemList = new ArrayList();


        stringArray = getResources().getStringArray(R.array.recycle_list_item);
        for (int i = 0; i < stringArray.length; i++) {
            itemList.add(stringArray[i]);
        }


        adapter = new TestRecycleAdapter(itemList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

    }
}
