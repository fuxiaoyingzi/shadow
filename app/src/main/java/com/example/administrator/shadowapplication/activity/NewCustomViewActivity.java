package com.example.administrator.shadowapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.PieChartView;
import com.example.administrator.shadowapplication.widget.bean.PieData;

import java.util.ArrayList;
import java.util.List;

public class NewCustomViewActivity extends AppCompatActivity {

    private List<PieData> pieDataList;
    private PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_custom_view);
        pieChartView = (PieChartView) findViewById(R.id.pieChart);
        pieDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieData pieData = new PieData();
            pieData.setName("name = " + i);
            pieData.setValue((float) (Math.random() * 10));
            pieDataList.add(pieData);
        }

        pieChartView.setPieDataList(pieDataList);
    }
}
