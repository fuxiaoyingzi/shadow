package com.example.administrator.shadowapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

/**
 * 测试内容提供者 读取联系人信息
 */
public class TestContentProvideActivity extends AppCompatActivity {

    TextView contactsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_provide);
        Log.d("hh", "onCreate");
        contactsTv = (TextView) findViewById(R.id.contactsTv);
        contactsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestContentProvideActivity.this, TestWebActivity.class);
                intent.putExtra("web_url", "http://www.baidu.com");
                startActivity(intent);
            }
        });
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String displayNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsTv.setText(displayName + ":" + displayNum);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hh", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("hh", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hh", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("hh", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("hh", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("hh", "onDestroy");
    }

}
