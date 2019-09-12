package com.example.administrator.shadowapplication.broadcast_receive;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.base.MyApp;

import java.io.File;

public class BroadcastReceiveActivity extends AppCompatActivity {
    private static final String BROAD_ACTION_ONE = "broad_action_one";
    private Button sendBroad;
    private IntentFilter intentFilter;

    BroadcastReceiver myReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(BROAD_ACTION_ONE)) {
                sendBroad.setText(intent.getStringExtra("hh"));
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receive);
        //注册广播
         intentFilter = new IntentFilter();
        intentFilter.addAction(BROAD_ACTION_ONE);
//        registerReceiver(myReceive, intentFilter);
        sendBroad = findViewById(R.id.sendBroad);
        sendBroad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //开启一个新线程，发布广播
                        Intent intent = new Intent();
                        intent.setAction(BROAD_ACTION_ONE);
                        intent.putExtra("hh", "hello shadow");
                        sendBroadcast(intent);
                        //只在本应用内传递广播
                        //LocalBroadcastManager.getInstance(BroadcastReceiveActivity.this).sendBroadcast(intent);
//
//                        Intent intent1 = new Intent();
//                        intent1.setAction("");
//                        intent1.setDataAndType(Uri.parse(""),"");
//                        intent.addCategory("");
//                        startActivity(intent);



                    }
                }).start();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceive,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceive);
    }

    /**
     * 只有在activity在意外注销的情况下才能被调用，也只是保存一些临时数据，不能保存持久数据
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    /**
     * 如果被调用，bundle中肯定有内容，而onCreate中的bundle不一定有内容。。。
     * 比如由其他activity启动时候就没有，只有意外销毁之后，重启才有内容
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }


    public void fileTest(String fileName){
       File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName);


       //测试外部存储的权限
       if (Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState())){

       }

        //apply比comment更加速度，apply先存内存，再存磁盘，comment直接存入磁盘，返回结果。。。频繁操作内容用apply，需要获取执行结果的用comment
        PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit().putString("hh","shadow").apply();

    }

    /**
     * 事务插入数据
     * @param db
     */
    public void inserUserData(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","shadow");
        contentValues.put("age",18);
        contentValues.put("weight",100);
        db.beginTransaction();
        try{

            db.insert("shadow",null,contentValues);
            db.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            db.endTransaction();
        }

    }

    public Bitmap decodeSampleBitmap(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_bg_0,options);
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_bg_0,options);
        return bitmap;
    }


}
