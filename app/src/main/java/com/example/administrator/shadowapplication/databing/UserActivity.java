package com.example.administrator.shadowapplication.databing;

import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.databinding.ActivityUserBinding;

import java.util.Locale;

public class UserActivity extends AppCompatActivity {


    private ActivityUserBinding userBinding;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 < Build.VERSION.SDK_INT) {
            configuration.setLocale(Locale.ENGLISH);
        } else {
            configuration.locale = Locale.ENGLISH;
        }
         getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        userBinding = DataBindingUtil.setContentView(this, R.layout.activity_user);


        User user = new User();
        user.setName("shadow");
        user.setSex("女");
        user.setAge(20);
        user.setDesc("hello world");
        userModel = new UserModel(user);
        userBinding.setUserModel(userModel);
        userBinding.setUserHandle(new UserHandle() {
            @Override
            public void changeData() {
                User user = new User();
                user.setName("tom");
                user.setSex("男");
                userModel = new UserModel(user);
                userBinding.setUserModel(userModel);

            }
        });
    }


}
