package com.example.administrator.shadowapplication.setting;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.administrator.shadowapplication.R;

/**
 * Created by Administrator on 2017/10/23.
 */

public class TestPreferenceFragment2 extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_fragment2_xml);
    }
}
