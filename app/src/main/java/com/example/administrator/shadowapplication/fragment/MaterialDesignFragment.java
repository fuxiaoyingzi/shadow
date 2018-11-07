package com.example.administrator.shadowapplication.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.adapter.TestRecycleAdapter;


/**
 * Created by Administrator on 2017/6/7.
 */

public class MaterialDesignFragment extends Fragment{
    static MaterialDesignFragment materialDesignFragment;
    private String[] stringArray;
    private TestRecycleAdapter adapter;

    private AppBarLayout appBarLayout;
    //private TextView appbarTitle;
    private RecyclerView recyclerView;
    public static MaterialDesignFragment newInstance(){
        Bundle bundle = new Bundle();
        bundle.putString("name","付小影子");
        materialDesignFragment = new MaterialDesignFragment();
        materialDesignFragment.setArguments(bundle);
        return materialDesignFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_material_design_layout,container,false);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appBarView);
        //appbarTitle = (TextView) view.findViewById(R.id.textView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        stringArray = getActivity().getResources().getStringArray(R.array.subject);
        adapter = new TestRecycleAdapter(stringArray,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
             /*   int height = appbarTitle.getHeight();
                int minHeight = appbarTitle.getMinHeight();
                float fraction = verticalOffset / (float) (minHeight - height);
                appbarTitle.setAlpha(1 - fraction);*/
            }
        });
    }

    @Override
    public void onDestroyView() {//和onViewCreate配套的
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
