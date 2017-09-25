package com.example.administrator.shadowapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import static com.example.administrator.shadowapplication.R.array.number;

public class WindowServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    final static int NOTIFY = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_service);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: //网络服务
                connectivifyService();
                break;
            case R.id.btn2: //电话管理服务
                telephoneManager();
                break;
            case R.id.btn3: //短信管理服务
                smsManager();
                break;
            case R.id.btn4: //音频服务
                break;
            case R.id.btn5: //振动器服务
                break;
            case R.id.btn6: //手机闹铃服务
                break;
            case R.id.btn7: //通知服务
                notificationManager();
                break;
        }

    }


    /**
     * 网络服务
     */
    public void connectivifyService() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        toastString("当前网络类型：" + networkInfo.getTypeName() + "---" + networkInfo.getType());


    }

    /**
     * 电话管理服务
     */
    public void telephoneManager() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        PhoneStateListener listener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                      /*  OutputStream os = null;
                        try {
                            os = openFileOutput("phoneList", MODE_APPEND);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        PrintStream ps = new PrintStream(os);
                        // 将来电号码记录到文件中
                        ps.println(new Date() + " 来电：" + number);
                        ps.close();*/
                        Log.d("hh",new Date() + " 来电：" + number);
                        break;

                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };
        //监听通话状态的改变
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    /**
     * 短信服务
     */
    public void smsManager(){
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent pi = PendingIntent.getActivity(this,
                0, new Intent(), 0);
        smsManager.sendTextMessage("18238636138",
                null, "hello shadow", pi, null);
     toastString("消息发送成功");

    }

    public void toastString(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void notificationManager(){
        //用这个变量来唯一的标定一个Notification对象
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this , TestRecycleViewActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notify =new Notification.Builder(this)
                //设置打开通知后， 该标题栏通知自动消失
                .setAutoCancel(true)
                //设置显示在状态栏中的通知提示信息
                .setTicker("有新消息")
                //设置通知的图标，注意图片不能够太大，否则不能够正常的显示
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知内容的标题
                .setContentTitle("一条新消息")
                //设置通知内容
                .setContentText("hello shadow!!!")
                //设置使用系统默认的声音、默认LED灯
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS)
                //设置通知的自定义声音
                //.setSound(Uri.parse("android.resource://com.penglee.notification/"+R.raw.msg))
                //设置消息中显示的发送时间
                .setWhen(System.currentTimeMillis())
                //.setShowWhen(true)   //设置是否setWhe指定的显示时间
                //设置点击通知将要启动程序的Intent
                .setContentIntent(pi)
                //返回Notification对象
                .build();

        //发送通知
        notificationManager.notify(NOTIFY , notify);
    }
}
