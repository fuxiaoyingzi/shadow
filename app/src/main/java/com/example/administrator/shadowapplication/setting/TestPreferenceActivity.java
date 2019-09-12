package com.example.administrator.shadowapplication.setting;

import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.shadowapplication.R;

import java.util.List;

/**
 * @author Administrator
 *         原始设置界面 自动保存到sharepreference
 */
public class TestPreferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.setting_activity_xml, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        // ... Finish with your last fragment.
        return PreferenceFragment.class.getName().equals(fragmentName)
                || TestPreferenceFragment.class.getName().equals(fragmentName)
                || TestPreferenceFragment2.class.getName().equals(fragmentName);

    }

}
