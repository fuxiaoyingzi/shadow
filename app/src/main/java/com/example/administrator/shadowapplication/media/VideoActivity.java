package com.example.administrator.shadowapplication.media;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;
import com.example.administrator.shadowapplication.databinding.ActivityVideoBinding;

import java.io.File;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityVideoBinding mVideoBinding;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoBinding = DataBindingUtil.setContentView(this, R.layout.activity_video);
        mVideoBinding.play.setOnClickListener(this);
        mVideoBinding.pause.setOnClickListener(this);
        mVideoBinding.stop.setOnClickListener(this);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "xxxx.mp4");
        mVideoBinding.videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMediaPlayer();
            } else {
                ToastUtil.showMsg("拒绝读取sd卡权限，不能播放视频");

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (mVideoBinding.videoView != null) {
                    mVideoBinding.videoView.start(); //开始播放
                }
                break;
            case R.id.pause:
                if (mVideoBinding.videoView != null) {
                    mVideoBinding.videoView.pause(); //暂停播放
                }
                break;
            case R.id.stop:
                if (mVideoBinding.videoView != null) {
                    mVideoBinding.videoView.resume(); //停止播放
                    initMediaPlayer();
                }
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoBinding.videoView != null) {
            mVideoBinding.videoView.suspend();
        }
    }
}
