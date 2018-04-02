package com.example.administrator.shadowapplication.media;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;
import com.example.administrator.shadowapplication.databinding.ActivityMusicBinding;

import java.io.File;
import java.io.IOException;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMusicBinding mMusicBinding;
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMusicBinding = DataBindingUtil.setContentView(this, R.layout.activity_music);
        mMusicBinding.play.setOnClickListener(this);
        mMusicBinding.pause.setOnClickListener(this);
        mMusicBinding.stop.setOnClickListener(this);
        mMusicBinding.video.setOnClickListener(this);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            initMediaPlayer();
        }
    }

    public void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "xxxx.mp3");
        try {
            mMediaPlayer.setDataSource(file.getPath()); //指定音频文件路径
            mMediaPlayer.prepare(); //进入准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    ToastUtil.showMsg("拒絕權限將無法使用應用播放音樂");
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (mMediaPlayer != null) {
                    mMediaPlayer.start(); //开始播放
                }
                break;
            case R.id.pause:
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause(); //暂停播放
                }
                break;
            case R.id.stop:
                if (mMediaPlayer != null) {
                    mMediaPlayer.reset(); //停止播放
                    initMediaPlayer();
                }
                break;
            case R.id.video:
                startActivity(new Intent(MusicActivity.this, VideoActivity.class));
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }
}
