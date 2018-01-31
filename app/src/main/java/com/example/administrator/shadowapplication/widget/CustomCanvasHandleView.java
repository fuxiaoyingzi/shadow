package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.shadowapplication.R;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/30
 *     desc   :
 * </pre>
 */


public class CustomCanvasHandleView extends View {
    private Paint paint;
    private Bitmap mBitmap;

    public CustomCanvasHandleView(Context context) {
        this(context, null);
    }

    public CustomCanvasHandleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCanvasHandleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCanvasHandleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.clip);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       /* canvas.save(); //保存现场
        paint.setColor(Color.CYAN);
        for (int i = 0; i < 10; i++) {
            canvas.drawRect(10, 10, 50, 100, paint);
            canvas.translate(10, 10);
        }
        canvas.restore(); //恢复现场

        canvas.save();
        paint.setColor(Color.BLUE);
        canvas.translate(0, 200);
        for (int i = 0; i < 10; i++) {
            canvas.drawRect(10, 10, 100, 100, paint);
            canvas.scale(0.9f, 0.9f);
        }
        canvas.restore();

        canvas.save();
        canvas.translate(300, 120);
        paint.setColor(Color.RED);
        canvas.drawCircle(0, 0, 100, paint);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(0, 80, 0, 100, paint);
            canvas.rotate(360 / 12);
        }
        canvas.restore();

        canvas.save();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.translate(0, 400);
        Path path = new Path();
        path.moveTo(10, 10);
        path.quadTo(45, 60, 300, 200);
        canvas.drawTextOnPath("hello shadow!!", path, 10, 10, paint);
        canvas.drawPath(path, paint);
        canvas.restore();*/

     /*   canvas.save();
        canvas.drawBitmap(mBitmap, 10, 10, paint);
        canvas.restore();
*/
        canvas.save();
        //canvas.translate(0, 200);
        Rect rect = new Rect(200, 200, 400, 400);
        canvas.clipRect(rect);
        Path path = new Path();
        path.addCircle(400, 400, 100, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.UNION);
        canvas.drawBitmap(mBitmap, 10, 10, paint);

        canvas.restore();


    }
}
