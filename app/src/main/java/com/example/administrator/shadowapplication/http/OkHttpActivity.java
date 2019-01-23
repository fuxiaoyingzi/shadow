package com.example.administrator.shadowapplication.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {
    private Button sendRequest,testRequest;
    private TextView httpContent;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        sendRequest = findViewById(R.id.sendRequest);
        testRequest = findViewById(R.id.testRequest);
        httpContent =  findViewById(R.id.httpContent);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String networkType;
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    networkType = "WiFi";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    networkType = "移动数据";
                    break;
                default:
                    networkType = "不知道的类型";
                    break;

            }
            Toast.makeText(this, "type  =" + networkType, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
        }

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest("https://www.baidu.com/");
            }
        });

        testRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testOkHttpUtil();
            }
        });
    }

    public void sendRequest(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String JSON = "application/json; charset=utf-8";
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .header("content-type", JSON)
                        .url(url)
                        .get()
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkHttpActivity.this, "hello shadow fail", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (response.body() != null && !TextUtils.isEmpty(response.toString())) {
                            final String text = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (TextUtils.isEmpty(text)) {
                                        httpContent.setText("hello shadow");
                                    } else {
                                        httpContent.setText("OKHttp enqueue回调："+text);
                                    }
                                }
                            });
                        } else {
                            httpContent.setText("hello shadow 2");
                        }
                    }
                });

            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        httpContent.setText( "httpUtil: "+bundle.getString("responseStr"));
                    }else {
                        httpContent.setText("bundle == null");
                    }
                    break;

            }
        }
    };
    public void testOkHttpUtil(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = HttpUtil.testHttp();
                try {
                    if (call != null){
                        Response response = call.execute();
                        if (response != null){

                            Message message  = Message.obtain();
                            message.what = 0;
                            Bundle bundle = new Bundle();
                            if (response.body() != null){
                                bundle.putString("responseStr",response.body().string());
                            }else {
                                bundle.putString("responseStr","response.body() == null");
                            }
                            message.setData(bundle);
                            handler.sendMessage(message);
                        }else {
                            Log.d("hh","response == null");
                        }
                    }else {
                        Log.d("hh","call == null");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
