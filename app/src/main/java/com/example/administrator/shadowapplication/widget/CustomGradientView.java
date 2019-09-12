package com.example.administrator.shadowapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/6
 *     desc   :渐变 Shader
 * </pre>
 */
public class CustomGradientView extends View {
    private Paint mPaint;
    private static final int SIZE = 50;//棋子尺寸
    private static final int OFFSET = 10; //发光点的偏移大小


    public CustomGradientView(Context context) {
        this(context, null);
    }

    public CustomGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    public void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //矩形的线性渐变
       Rect rect = new Rect(30, 30, 200, 100);
        LinearGradient linearGradient = new LinearGradient(rect.left, rect.top, rect.right, rect.bottom, Color.RED, Color.YELLOW, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRect(rect, mPaint);

        canvas.translate(0,rect.height()+50);
        Rect rect1 = new Rect(rect);
        rect1.inset(-20,-20);//放大矩形,20
        linearGradient = new LinearGradient(rect1.left, rect1.top, rect1.right, rect1.bottom, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);
        canvas.drawRect(rect1, mPaint);

        canvas.translate(0,100);
        Rect rect2 = new Rect(rect);
        rect2.inset(20,20);//缩小矩形 20
        linearGradient = new LinearGradient(rect1.left, rect1.top, rect1.right, rect1.bottom, Color.RED, Color.YELLOW, Shader.TileMode.MIRROR);
        mPaint.setShader(linearGradient);
        canvas.drawRect(rect2, mPaint);

        //ViewConfiguration.get(getContext()).getScaledTouchSlop();//获取最小滑动距离，不同的设备，此数据不一样

        canvas.translate(0,100);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight() / 3;
        int rows = height / SIZE; //行数
        int cols = width / SIZE; //列数
        drawChessBoard(canvas, rows, cols);
        drawChess(canvas, 1, 1, ChessType.BLACK);
        drawChess(canvas, 2, 2, ChessType.BLACK);
        drawChess(canvas, 3, 3, ChessType.WHITE);
        drawChess(canvas, 4, 4, ChessType.WHITE);

        //扫描渐变
        canvas.save();
        canvas.translate(0, height + 50);
        SweepGradient sg = new SweepGradient(100, 100,
                new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.GREEN}, null);
        mPaint.setShader(sg);
        canvas.drawOval(new RectF(0, 0, 200, 200), mPaint);
        canvas.restore();


    }

    /**
     * 绘制棋盘
     *
     * @param canvas
     * @param rows   行
     * @param cols   列
     */
    public void drawChessBoard(Canvas canvas, int rows, int cols) {
        mPaint.setColor(Color.GRAY);
        mPaint.setShadowLayer(0, 0, 0, Color.GRAY);
        for (int i = 0; i < rows + 1; i++) {
            canvas.drawLine(0, i * SIZE, cols * SIZE, i * SIZE, mPaint);//画行
        }
        for (int i = 0; i < cols + 1; i++) {
            canvas.drawLine(i * SIZE, 0, i * SIZE, rows * SIZE, mPaint);//画行
        }
    }

    /**
     * 绘制棋子
     *
     * @param canvas
     * @param x      第几行
     * @param y      第几列
     * @param type   棋子类型，黑子 白子
     */
    public void drawChess(Canvas canvas, int x, int y, ChessType type) {
        int colorOuter = type == ChessType.BLACK ? Color.BLACK : Color.GRAY;
        int colorInner = Color.WHITE;
        //径向渐变
        RadialGradient radialGradient = new RadialGradient(x * SIZE + OFFSET, y * SIZE + OFFSET, SIZE / 1.5f, colorInner, colorOuter, Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        this.setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setShadowLayer(6, 6, 4, Color.parseColor("#aacccccc"));
        canvas.drawCircle(x * SIZE, y * SIZE, SIZE / 2, mPaint);
    }

    enum ChessType {
        BLACK, WHITE
    }
}
