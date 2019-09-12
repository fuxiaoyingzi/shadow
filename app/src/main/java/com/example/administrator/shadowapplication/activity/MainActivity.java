package com.example.administrator.shadowapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.fragment.FragmentCallBack;
import com.example.administrator.shadowapplication.fragment.TestFragment;

public class MainActivity extends AppCompatActivity implements FragmentCallBack {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    TestFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        frameLayout = (FrameLayout) findViewById(R.id.fl_content);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle); //3.监听变化.
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_history:
                        startActivity(new Intent(MainActivity.this, TestRecycleViewActivity.class));
                        Log.d("hh", "设置");
                        break;
                    case R.id.nav_manage:
                        startActivity(new Intent(MainActivity.this, WindowServiceActivity.class));
                        Log.d("hh", "管理");

                      /*  DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%90%91%E6%97%A5%E8%91%B5&hs=2&pn=1&spn=0&di=107970712521&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=2563838110%2C1002273593&os=125678947%2C101098936&simid=3473851336%2C92055225&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%90%91%E6%97%A5%E8%91%B5&objurl=http%3A%2F%2Fpic17.nipic.com%2F20111009%2F6641300_211957416128_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3F8AzdH3F99AzdH3Fcd988blh0cdanw0w_z%26e3Bip4s&gsm=0"));
                        request.setDestinationInExternalPublicDir("/dowmload/","test");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        request.setVisibleInDownloadsUi(true);

                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);*/
                        break;
                }
                return true;
            }
        });


        fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (fragment == null) {
            fragment = TestFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fl_content, fragment);
            fragmentTransaction.commit();
        }

        ImageView userAvatar = (ImageView) findViewById(R.id.userAvatar);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.updateFragmentContent("hello shadow,我是activity的数据");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("name", "shadow");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void callBack(String fragmentText) {
        Log.d("hh", fragmentText);

    }
}
