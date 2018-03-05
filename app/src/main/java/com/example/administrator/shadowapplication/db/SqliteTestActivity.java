package com.example.administrator.shadowapplication.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;

import java.io.ByteArrayOutputStream;

public class SqliteTestActivity extends AppCompatActivity {
    private Button saveImage,getImage;
    private SQLiteDatabase db;
    private ImageView sqlImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);
        saveImage = findViewById(R.id.saveImage);
        getImage = findViewById(R.id.getImage);
        sqlImage = findViewById(R.id.iv_sql);
        db = new MySqlite(this,"shadow.db",null,1).getReadableDatabase();
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_bg_0);
                byte[] bytes = new byte[bitmap.getWidth()*bitmap.getHeight()*4];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                bytes = outputStream.toByteArray();
                ContentValues contentValues = new ContentValues();
                contentValues.put("image",bytes);
                db.insert("shadow",null,contentValues);

            }
        });

        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] colums = new String[]{"id","image"};
                Cursor cursor = db.query("shadow",colums,null,null,null,null,null);
                if (cursor != null){
                    while (cursor.moveToNext()){
                        int imageIndex = cursor.getColumnIndex("image");
                        byte[] bytes = cursor.getBlob(imageIndex);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        sqlImage.setImageBitmap(bitmap);
                    }
                    cursor.close();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
