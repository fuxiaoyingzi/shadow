package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.shadowapplication.R;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/25
 *     desc   :图片文字
 * </pre>
 */

public class CustomPictureView extends View {
    private Picture mPicture;
    private PictureDrawable mPictureDrawable;
    private int mWidth, mHeight;
    private Bitmap bitmap;
    private Paint paint;

    public CustomPictureView(Context context) {
        this(context, null);
    }

    public CustomPictureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPicture(context);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomPictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPicture(context);
    }

    public void initPicture(Context context) {
        mPicture = new Picture();
        Canvas canvas = mPicture.beginRecording(500, 500);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        canvas.drawRect(0, 0, 200, 200, paint);
        mPicture.endRecording();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launchers);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制picture，即绘制picture录制的canvas的操作
        /*mPictureDrawable = new PictureDrawable(mPicture);
        mPictureDrawable.draw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(0, 0, 300, 300);
        canvas.drawPicture(mPicture, rectF);*/
        /*canvas.drawBitmap(bitmap, new Matrix(), paint);
        canvas.translate(mWidth / 2, mHeight / 2);*/
        //canvas.drawBitmap(bitmap, 100, 50, paint);
        Rect rect = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2); //截图图片要显示的部分
        Rect rectDst = new Rect(0, 0, 400, 400);//图片要填充的区域
        canvas.drawBitmap(bitmap, rect, rectDst, paint);
    }
}
