package com.example.administrator.shadowapplication.sport.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Toast;


import com.example.administrator.shadowapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private SportListAdapter adapter;
    private List<SportBean> sportBeanList;
    private String[] sportTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        initView();

    }

    public void initView() {
        sportBeanList = new ArrayList<>();
        sportTitle = getResources().getStringArray(R.array.sport_categroy);
        for (int i = 0; i < sportTitle.length; i++) {
            SportBean bean = new SportBean(sportTitle[i]);
            sportBeanList.add(bean);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        adapter = new SportListAdapter(sportBeanList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // recyclerView.addItemDecoration(new LinearItemDecoration(getResources().getColor(R.color.grey_333333), dp2px(this, 1)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setItemOnClickListener(new SportListAdapter.ItemOnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(SportActivity.this, CountDownActivity.class);
                intent.putExtra("bean", sportBeanList.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

    }

    public void showMessage(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public static int dp2px(Context context, float dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }
}
