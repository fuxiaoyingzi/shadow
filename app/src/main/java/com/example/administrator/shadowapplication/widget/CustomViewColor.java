package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.shadowapplication.R;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/29
 *     desc   :颜色矩阵
 * </pre>
 */
public class CustomViewColor extends View {
    private Paint paint;
    private int mWidth, mHeight;
    private Context mContext;
    private Bitmap mBitmap;
    private boolean isClick;

    public CustomViewColor(Context context) {
        this(context, null);
    }

    public CustomViewColor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomViewColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context) {
        this.mContext = context;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.collection);
      /*  ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.393f, 0.769f, 0.189f, 0, 0,
                0.349f, 0.686f, 0.168f, 0, 0,
                0.272f, 0.534f, 0.131f, 0, 0,
                0, 0, 0, 1f, 0,
        });*/
        paint = new Paint();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClick) {
                    isClick = true;
                    paint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x00FFFF00)); //变成黄色
                    invalidate(); //重绘
                } else {
                    isClick = false;
                    paint.setColorFilter(null); //灰色原来的状态
                    invalidate();
                }

            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, paint);
    }
}
