package com.example.administrator.shadowapplication.databing;


import android.content.Intent;
import android.content.res.Configuration;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.databinding.ActivityUserBinding;

import java.util.Locale;

public class UserActivity extends AppCompatActivity {


    private ActivityUserBinding userBinding;
    private UserModel userModel;
    private ShareActionProvider mShareActionProvider;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu_share_action, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                Intent intent = new Intent();
                setShareIntent(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

}
