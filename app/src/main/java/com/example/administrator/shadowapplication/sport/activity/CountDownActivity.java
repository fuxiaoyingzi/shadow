package com.example.administrator.shadowapplication.sport.activity;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;

public class CountDownActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SportBean sportBean;
    TextView sportTitle;
    TextView sportTime;
    ImageView playAndPauseImage;
    SeekBar progressBar;
    TextView totalTime;
    private int currentState = -1;
    private static final int STATE_PLAY = 1;
    private static final int STATE_PAUSE = 0;
    private int mCurrentProgress = 0;
    Thread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportBean = getIntent().getParcelableExtra("bean");
        if (sportBean == null) {
            finish();
        }
        setContentView(R.layout.activity_count_down);
        initView();
        initData();
        startSport();
    }

    public void initView() {
        playAndPauseImage = (ImageView) findViewById(R.id.playAndPauseImage);
        progressBar = (SeekBar) findViewById(R.id.progressBar);
        sportTitle = (TextView) findViewById(R.id.sportTitle);
        sportTime = (TextView) findViewById(R.id.sportTime);
        totalTime = (TextView) findViewById(R.id.totalTime);

    }

    public void initData() {
        currentState = STATE_PAUSE;
        sportTitle.setText(sportBean.getTitle());
        totalTime.setText("总时间："+sportBean.getTime()+"s");
        sportTime.setText(String.valueOf(0));
        progressBar.setMax(Integer.valueOf(sportBean.getTime()));
        progressBar.setOnSeekBarChangeListener(this);
        playAndPauseImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));
        playAndPauseImage.setOnClickListener(playClick);
    }

    public void switchState(){
        if (currentState == STATE_PLAY){
            currentState = STATE_PAUSE;
            playAndPauseImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));
        }else {
            currentState = STATE_PLAY;
            playAndPauseImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
        }
    }

    View.OnClickListener playClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchState();
            if (currentState == STATE_PLAY){
                startSport();
            }
        }
    };

    public void startSport(){
        myThread = new Thread(new MyThread());
        myThread.start();
    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCurrentProgress++;
                            sportTime.setText(String.valueOf(mCurrentProgress));
                            progressBar.setProgress(mCurrentProgress);
                            if (String.valueOf(mCurrentProgress).equals(sportBean.getTime())){
                                currentState = STATE_PLAY;
                                mCurrentProgress = 0;
                                MediaPlayer mp = new MediaPlayer();
                                try {
                                    mp.setDataSource(CountDownActivity.this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                                    mp.prepare();
                                    mp.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                CountDownActivity.this.finish();
                            }
                        }
                    });


            }
        }
    };

     private class  MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    if (currentState == STATE_PLAY){
                        break;
                    }
                    if (String.valueOf(progressBar.getProgress()+1) .equals( sportBean.getTime())){
                        playAndPauseImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
                        break;
                    }
                    Thread.sleep(1000);     // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mCurrentProgress = progress;
        sportTime.setText(String.valueOf(mCurrentProgress));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    //======================着色区域 setTint setTintList ================================
    public void setImageTint(ImageView src, int color) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            src.getDrawable().setTint(color);
        } else {
            Drawable drawableWrapper = DrawableCompat.wrap(src.getDrawable());
            DrawableCompat.setTint(drawableWrapper, color);
            src.setImageDrawable(drawableWrapper);
        }
    }

    public void setDrawableTintList(ImageView src, ColorStateList colors) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            src.getDrawable().setTintList(colors);
        } else {
            Drawable drawable = DrawableCompat.wrap(src.getDrawable());
            Rect rect = drawable.getBounds();
            drawable = drawable.mutate();
            DrawableCompat.setTintList(drawable, colors);
            drawable.setBounds(rect);
            src.setImageDrawable(drawable);
        }
    }


}
