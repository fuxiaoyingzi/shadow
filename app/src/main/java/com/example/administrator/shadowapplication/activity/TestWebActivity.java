package com.example.administrator.shadowapplication.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

public class TestWebActivity extends AppCompatActivity {

    private WebView webView;
    private TextView title;
    private String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web);
        Log.d("hh","TestWebActivity onCreate");
        webView = (WebView) findViewById(R.id.webView);
        title = (TextView) findViewById(R.id.title);

        webUrl = getIntent().getStringExtra("web_url");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestWebActivity.this, TestWebActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("hh","TestWebActivity onResume");
        if (TextUtils.isEmpty(webUrl)) {
            webView.loadUrl("http://www.baidu.com");
        } else {
            webView.loadUrl(webUrl);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hh","TestWebActivity onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hh","TestWebActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("hh","TestWebActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("hh","TestWebActivity onDestroy");
    }
}
