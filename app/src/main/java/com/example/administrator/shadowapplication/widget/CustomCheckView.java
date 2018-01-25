package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.shadowapplication.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/25
 *     desc   :自定义选中动画，利用canvasDrawBitmap
 * </pre>
 */

public class CustomCheckView extends View {
    private int mWidth, mHeight;
    private Bitmap bitmap;
    private Context context;
    private Paint paint;

    private static final int ANIM_STATE_NULL = 0;
    private static final int ANIM_STATE_CHECK = 1;
    private static final int ANIM_STATE_UNCHECK = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ANIM_STATE_CHECK, ANIM_STATE_NULL, ANIM_STATE_UNCHECK})
    private @interface AnimState {
    }

    private int animMaxPage = 13;
    private int animCurrentPage = -1;
    @AnimState
    private int animState = ANIM_STATE_NULL;
    private int animDuration = 500;//动画时间500s

    private boolean isCheck;
    private Handler handler;


    public CustomCheckView(Context context) {
        this(context, null);
    }

    public CustomCheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.check);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.d("hh", "handleMessage animCurrentPage = " + animCurrentPage);
                Log.d("hh", "handleMessage animMaxPage = " + animMaxPage);
                Log.d("hh", "handleMessage isCheck = " + isCheck);
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_STATE_NULL)
                        return;
                    if (animState == ANIM_STATE_CHECK) {
                        animCurrentPage++;
                    } else {
                        animCurrentPage--;
                    }

                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_STATE_NULL;
                }
                Log.d("hh", "animCurrentPage = " + animCurrentPage);
                Log.d("hh", "animMaxPage = " + animMaxPage);
                Log.d("hh", "isCheck = " + isCheck);

            }
        };
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
        canvas.translate(mWidth / 2, mHeight / 2);//移动到中心
        canvas.drawCircle(0, 0, 200, paint);
        int bitHeight = bitmap.getHeight();
        Rect rect = new Rect(bitHeight * animCurrentPage, 0, bitHeight * (animCurrentPage + 1), bitHeight);
        RectF rectF = new RectF(-150, -150, 150, 150);
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        Log.d("hh", "CustomCheckView onDraw bitHeight = " + bitHeight + ",bitWidth = " + bitmap.getWidth());
        Log.d("hh", "CustomCheckView onDraw bitHeight * animCurrentPage = " + bitHeight * animCurrentPage);
        Log.d("hh", "CustomCheckView onDraw bitHeight * (animCurrentPage + 1) = " + bitHeight * (animCurrentPage + 1));


    }

    public void check() {
        if (animState != ANIM_STATE_NULL || isCheck) {
            return;
        }
        animState = ANIM_STATE_CHECK;
        animCurrentPage = 0;
        isCheck = true;
        Log.d("hh", "check " + animDuration / animMaxPage);
        handler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
    }

    public void unCheck() {
        if (animState != ANIM_STATE_NULL || !isCheck) {
            return;
        }
        animState = ANIM_STATE_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        isCheck = false;
        Log.d("hh", "unCheck " + animDuration / animMaxPage);
        handler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
    }

    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.animDuration = animDuration;
    }


    public void setCirclrBackgroundColor(int color) {
        paint.setColor(color);
    }
}
