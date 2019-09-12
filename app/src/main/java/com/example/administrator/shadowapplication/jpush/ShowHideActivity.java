package com.example.administrator.shadowapplication.jpush;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;

import com.example.administrator.shadowapplication.R;


public class ShowHideActivity extends FragmentActivity implements View.OnClickListener {
    private EventFragment eventFragment;
    private PageFragment pageFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
         fragmentManager = getSupportFragmentManager();
        showFragment(0);
        findViewById(R.id.btn_test_event).setOnClickListener(this);
        findViewById(R.id.btn_test_page).setOnClickListener(this);

    }

    private void showFragment(int fgType){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        if(fgType==0){
            if(eventFragment==null){
                eventFragment = new EventFragment();
                eventFragment.setApiCallType(1);
                transaction.add(R.id.content,eventFragment);
            }else{
                transaction.show(eventFragment);
            }

        }else{
            if(pageFragment==null){
                pageFragment = new PageFragment();
                pageFragment.setApiCallType(1);
                transaction.add(R.id.content,pageFragment);
            }else{
                transaction.show(pageFragment);
            }
        }
        transaction.commit();
    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (eventFragment != null) {
            transaction.hide(eventFragment);
        }
        if ( pageFragment!= null) {
            transaction.hide(pageFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test_event:
                showFragment(0);
                break;
            case R.id.btn_test_page:
                showFragment(1);
                break;
        }
    }
}
