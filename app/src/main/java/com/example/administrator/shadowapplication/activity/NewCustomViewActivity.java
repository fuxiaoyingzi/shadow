package com.example.administrator.shadowapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.CustomCheckView;
import com.example.administrator.shadowapplication.widget.PieChartView;
import com.example.administrator.shadowapplication.widget.bean.PieData;

import java.util.ArrayList;
import java.util.List;

public class NewCustomViewActivity extends AppCompatActivity {

    private List<PieData> pieDataList;
    private PieChartView pieChartView;
    private CustomCheckView customCheckView;
    private Button checkBtn, unCheckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_custom_view);
        customCheckView = (CustomCheckView) findViewById(R.id.checkView);
        pieChartView = (PieChartView) findViewById(R.id.pieChart);
        checkBtn = (Button) findViewById(R.id.checkBnt);
        unCheckBtn = (Button) findViewById(R.id.unCheckBtn);
        pieDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieData pieData = new PieData();
            pieData.setName("name = " + i);
            pieData.setValue((float) (Math.random() * 10));
            pieDataList.add(pieData);
        }

        customCheckView.setAnimDuration(1000);
        customCheckView.setCirclrBackgroundColor(Color.GREEN);
        pieChartView.setPieDataList(pieDataList);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCheckView.check();
            }
        });

        unCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCheckView.unCheck();
            }
        });
    }
}
