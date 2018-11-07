package com.example.administrator.shadowapplication.album;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

/**
 * 相册选择
 */
public class AlbumActivity extends AppCompatActivity {

    private ArrayList<AlbumFile> mAlbumFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        findViewById(R.id.btnChooseImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.image(AlbumActivity.this)
                        .multipleChoice()
                        .camera(true)
                        .columnCount(2)
                        .selectCount(6)
                        .checkedList(mAlbumFiles)
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                                mAlbumFiles = result;
                            }
                        })
                        .start();
            }
        });
    }
}
