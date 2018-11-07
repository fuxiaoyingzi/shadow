
package com.example.administrator.shadowapplication.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.widget.CustomSportCircle;

import java.util.Timer;
import java.util.TimerTask;

public class CustomColorMatrixActivity extends AppCompatActivity {

    private CustomSportCircle sportCircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_color_matrix);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        /*paint.setTextSkewX(0.25f);
        paint.setTextSize(30);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setStyle(Paint.Style.FILL);
        paint.setUnderlineText(true);
        paint.setFakeBoldText(true);
        canvas.drawText("hello shadow", 10, 100, paint);*/

       /* paint.setColor(getResources().getColor(R.color.md_pink_400));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        Rect rect = new Rect(10, 120, 310, 320);
        canvas.drawRect(rect, paint);*/

      /*  Path path = new Path();
        path.moveTo(10, 150);
        path.rLineTo(300, 10);
        path.rLineTo(-300, 150);
        path.rLineTo(150, -300);
        path.rLineTo(150, 300);
        path.close();
        canvas.drawPath(path, paint);*/

        /*paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawPoint(0, 150, paint);
        canvas.drawPoint(300, 150, paint);
        canvas.drawPoint(-300, 300, paint);
        canvas.drawPoint(150, -150, paint);
        canvas.drawPoint(150, 450, paint);*/

        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(50, 50);
        path.quadTo(100, 200, 300, 300);
        canvas.drawTextOnPath("hello shadow", path, 20, 10, paint);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
        imageView.setImageBitmap(bitmap); //创建空白bitmap，绘制之后显示在imageView中

        sportCircle = (CustomSportCircle) findViewById(R.id.sportCircle);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sportCircle.postInvalidate();
            }
        }, 20, 10);
    }
}
