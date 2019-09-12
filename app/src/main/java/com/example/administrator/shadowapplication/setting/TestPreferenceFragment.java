package com.example.administrator.shadowapplication.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import androidx.annotation.Nullable;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;

/**
 * @author Administrator
 * @date 2017/10/20
 */

public class TestPreferenceFragment extends PreferenceFragment {
    private SharedPreferences.OnSharedPreferenceChangeListener mChangeListener;
    private Context mActivity;
    private PreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_fragment1_xml);
        preferenceManager = getPreferenceManager();
        mActivity = getActivity();

        SwitchPreference wifiCheckBoxPreference = (SwitchPreference) preferenceManager.findPreference("setting_wifi");
        boolean n = wifiCheckBoxPreference.isChecked();
        Toast.makeText(mActivity, "当前状态为：" + n, Toast.LENGTH_SHORT).show();

        mChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if ("setting_wifi".equals(key) || "setting_bluetouh".equals(key) || "charge_lock_screen".equals(key) || "never_sleep".equals(key)) {
                    Toast.makeText(mActivity, key + " : change to " + sharedPreferences.getBoolean(key, true), Toast.LENGTH_SHORT).show();
                } else if ("setting_timezone".equals(key)) {
                    findPreference("setting_timezone").setSummary(sharedPreferences.getString(key, "GMY - 02:00"));
                }
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(mChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mChangeListener);
    }
}
