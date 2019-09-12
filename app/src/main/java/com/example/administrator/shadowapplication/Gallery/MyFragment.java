package com.example.administrator.shadowapplication.Gallery;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

/**
 * 作者： SamanLan on 2016/9/6.
 */
public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        ((TextView)view.findViewById(R.id.text)).setText(getArguments().getString("text"));
        return view;
    }
}
