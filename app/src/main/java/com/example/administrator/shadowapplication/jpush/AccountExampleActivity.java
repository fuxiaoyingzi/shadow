package com.example.administrator.shadowapplication.jpush;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;

import cn.jiguang.analytics.android.api.Account;
import cn.jiguang.analytics.android.api.AccountCallback;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;

/**
 * Created by efan on 2017/11/7.
 */

public class AccountExampleActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "AccountExampleActivity";

    private EditText etAccountId;
    private EditText etCreationTime;
    private EditText etSex;
    private EditText etBirthdate;
    private EditText etPaid;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etName;
    private EditText etKey1;
    private EditText etValue1;
    private EditText etKey2;
    private EditText etValue2;
    private Button btnIdentify;
    private Button btnDetach;
    private CheckBox cbAccountId;
    private CheckBox cbCreationTime;
    private CheckBox cbSex;
    private CheckBox cbBithdate;
    private CheckBox cbPaid;
    private CheckBox cbPhone;
    private CheckBox cbEmail;
    private CheckBox cbName;
    private CheckBox cbExtra1;
    private CheckBox cbExtra2;

    private EditText etPeriod;
    private Button btnSetPeriod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_example);
        initView();
    }

    private void initView() {
        etAccountId = (EditText) findViewById(R.id.et_account_id);
        etCreationTime = (EditText) findViewById(R.id.et_creation_time);
        etSex = (EditText) findViewById(R.id.et_sex);
        etBirthdate = (EditText) findViewById(R.id.et_birthdate);
        etPaid = (EditText) findViewById(R.id.et_paid);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etEmail = (EditText) findViewById(R.id.et_email);
        etName = (EditText) findViewById(R.id.et_name);
        etKey1 = (EditText) findViewById(R.id.et_key1);
        etValue1 = (EditText) findViewById(R.id.et_value1);
        etKey2 = (EditText) findViewById(R.id.et_key2);
        etValue2 = (EditText) findViewById(R.id.et_value2);
        btnIdentify = (Button) findViewById(R.id.btn_identify);
        btnDetach = (Button) findViewById(R.id.btn_detach);

        cbAccountId = (CheckBox) findViewById(R.id.cb_account_id);
        cbCreationTime = (CheckBox) findViewById(R.id.cb_creation_time);
        cbSex = (CheckBox) findViewById(R.id.cb_sex);
        cbBithdate = (CheckBox) findViewById(R.id.cb_bithdate);
        cbPaid = (CheckBox) findViewById(R.id.cb_paid);
        cbPhone = (CheckBox) findViewById(R.id.cb_phone);
        cbEmail = (CheckBox) findViewById(R.id.cb_email);
        cbName = (CheckBox) findViewById(R.id.cb_name);
        cbExtra1 = (CheckBox) findViewById(R.id.cb_extra1);
        cbExtra2 = (CheckBox) findViewById(R.id.cb_extra2);

        etPeriod = (EditText) findViewById(R.id.et_period);
        btnSetPeriod = (Button) findViewById(R.id.btn_set_period);

        btnIdentify.setOnClickListener(this);
        btnDetach.setOnClickListener(this);
        btnSetPeriod.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_identify:
                identifyAccount();
                break;
            case R.id.btn_detach:
                detachAccount();
                break;
            case R.id.btn_set_period:
                setPeriod();
                break;
        }
    }

    private void setPeriod() {
        String periodStr = etPeriod.getText().toString();
        int period = 0;
        if (!TextUtils.isEmpty(periodStr)) {
            try {
                period = Integer.parseInt(periodStr);
            } catch (Exception ignore) {
                Toast.makeText(this, "时长应当是整数", Toast.LENGTH_SHORT).show();
                return;
            }
            JAnalyticsInterface.setAnalyticsReportPeriod(getApplicationContext(), period);
        }
    }

    private void identifyAccount() {
        String id = etAccountId.getText().toString().trim();
        Account account = new Account(id);


        if (cbCreationTime.isChecked()) {
            Long creationTime = null;
            String time = etCreationTime.getText().toString().trim();
            if (!TextUtils.isEmpty(time)) {
                try {
                    creationTime = Long.parseLong(time);
                } catch (Exception ignore) {
                    Toast.makeText(this, "creationTime 格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            account.setCreationTime(creationTime);
        }

        if (cbSex.isChecked()) {
            Integer sex = null;
            String sexStr = etSex.getText().toString().trim();
            if (!TextUtils.isEmpty(sexStr)) {
                try {
                    sex = Integer.parseInt(sexStr);
                } catch (Exception ignore) {
                    Toast.makeText(this, "sex 格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            account.setSex(sex);
        }

        if (cbBithdate.isChecked()) {
            String birthdate = etBirthdate.getText().toString().trim();
            if (TextUtils.isEmpty(birthdate)) {
                birthdate = null;
            }
            account.setBirthdate(birthdate);
        }

        if (cbPaid.isChecked()) {
            Integer paid = null;
            String paidStr = etPaid.getText().toString().trim();
            if (!TextUtils.isEmpty(paidStr)) {
                try {
                    paid = Integer.parseInt(paidStr);
                } catch (Exception ignore) {
                    Toast.makeText(this, "paid 格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            account.setPaid(paid);
        }

        if (cbPhone.isChecked()) {
            String phone = etPhone.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                phone = null;
            }
            account.setPhone(phone);
        }

        if (cbEmail.isChecked()) {
            String email = etEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                email = null;
            }
            account.setEmail(email);
        }

        if (cbName.isChecked()) {
            String name = etName.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                name = null;
            }
            account.setName(name);
        }

        String key1 = etKey1.getText().toString().trim();
        String value1 = etValue1.getText().toString().trim();
        if (!TextUtils.isEmpty(key1)) {
            if (cbExtra1.isChecked()) {
                account.setExtraAttr(key1, null);
            } else {
                account.setExtraAttr(key1, value1);
            }
        }


        String key2 = etKey2.getText().toString().trim();
        String value2 = etValue2.getText().toString().trim();
        if (!TextUtils.isEmpty(key2)) {
            if (cbExtra2.isChecked()) {
                account.setExtraAttr(key2, null);
            } else {
                account.setExtraAttr(key2, value2);
            }
        }
        JAnalyticsInterface.identifyAccount(getApplicationContext(), account, new AccountCallback() {
            @Override
            public void callback(int code, String msg) {
                Log.e(TAG, "code = " + code + " msg =" + msg);
            }
        });
    }

    private void detachAccount() {
        JAnalyticsInterface.detachAccount(getApplicationContext(), new AccountCallback() {
            @Override
            public void callback(int code, String msg) {
                Log.e(TAG, "code = " + code + " msg =" + msg);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        JAnalyticsInterface.onPageStart(getApplicationContext(), this.getClass().getCanonicalName());
    }


    @Override
    protected void onPause() {
        super.onPause();
        JAnalyticsInterface.onPageEnd(getApplicationContext(), this.getClass().getCanonicalName());
    }

}
