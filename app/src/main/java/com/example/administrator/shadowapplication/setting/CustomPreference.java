package com.example.administrator.shadowapplication.setting;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import androidx.annotation.RequiresApi;
import androidx.core.view.LayoutInflaterFactory;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;

/**
 *
 * @author Administrator
 * @date 2017/10/23
 */

public class CustomPreference extends Preference {
    private TextView t1, t2;
    private String title, value;


    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.preference_atts);
        title = typedArray.getString(R.styleable.preference_atts_preference_title);
        value = typedArray.getString(R.styleable.preference_atts_preference_value);
        typedArray.recycle();
    }

    public CustomPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPreference(Context context) {
        this(context, null);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        t1 = (TextView) view.findViewById(R.id.preference_title);
        t2 = (TextView) view.findViewById(R.id.preference_value);
        t1.setText(title);
        t2.setText(value);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        return LayoutInflater.from(getContext()).inflate(R.layout.custom_preference_layout, parent, false);

    }

    @Override
    protected void onClick() {
        super.onClick();
        Toast.makeText(getContext(), "自定义preference", Toast.LENGTH_SHORT).show();
    }
}
