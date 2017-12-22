package com.example.administrator.shadowapplication.http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnectionActivity extends AppCompatActivity {

    private Button sendRequest, okRequest;
    private TextView httpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);
        sendRequest = (Button) findViewById(R.id.sendRequest);
        httpContent = (TextView) findViewById(R.id.httpContent);
        okRequest = (Button) findViewById(R.id.okRequest);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest("https://www.baidu.com/");
            }
        });
        okRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HttpUrlConnectionActivity.this, OkHttpActivity.class));
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });


    }

    public void sendRequest(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
               /* try {
                    InputStream inputStream = new FileInputStream("shadow.txt");
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(bytes)) != -1) { //没读完

                    }
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream); //缓冲区，默认8192 byte
                    bufferedInputStream.read(); //读取8192字节
                    InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);//字节流转换为字符流
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //字符缓冲流
                    bufferedReader.readLine();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


                try {
                    URL url1 = new URL(url);
                    urlConnection = (HttpURLConnection) url1.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    reader = new BufferedReader(streamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    showResponse(stringBuilder.toString());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            }
        }).start();

    }

    public void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(response)) {
                    httpContent.setText("hello shadow");
                } else {
                    httpContent.setText(response);
                }
            }
        });

    }
}
