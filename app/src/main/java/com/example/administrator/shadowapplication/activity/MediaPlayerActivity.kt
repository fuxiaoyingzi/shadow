package com.example.administrator.shadowapplication.activity

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import kotlinx.android.synthetic.main.activity_media_player.*


class MediaPlayerActivity : AppCompatActivity() {
    /**
     *
    int getDuration()：获取流媒体的总播放时长，单位是毫秒。
    int getCurrentPosition()：获取当前流媒体的播放的位置，单位是毫秒。
    void seekTo(int msec)：设置当前MediaPlayer的播放位置，单位是毫秒。
    void setLooping(boolean looping)：设置是否循环播放。
    boolean isLooping()：判断是否循环播放。
    boolean  isPlaying()：判断是否正在播放。
    void prepare()：同步的方式装载流媒体文件。
    void prepareAsync()：异步的方式装载流媒体文件。
    void release ()：回收流媒体资源。
    void setAudioStreamType(int streamtype)：设置播放流媒体类型。
    void setWakeMode(Context context, int mode)：设置CPU唤醒的状态。
    setNextMediaPlayer(MediaPlayer next)：设置当前流媒体播放完毕，下一个播放的MediaPlayer。


    setOnCompletionListener(MediaPlayer.OnCompletionListener listener)：当流媒体播放完毕的时候回调。
    setOnErrorListener(MediaPlayer.OnErrorListener listener)：当播放中发生错误的时候回调。
    setOnPreparedListener(MediaPlayer.OnPreparedListener listener)：当装载流媒体完毕的时候回调。
    setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener listener)：当使用seekTo()设置播放位置的时候回调。
     */

    val mp3Path: String = "http://data.flash127.com/mp3_flash127_com/mp3/201807/20180725_1237_262808.mp3"

    var mediaPlayer: MediaPlayer? = null;

    var handler = Handler()
    var updateThread: Runnable = object : Runnable {
        override fun run() {
            //获得歌曲现在播放位置并设置成播放进度条的值
            musicProgress.setProgress(mediaPlayer?.getCurrentPosition() ?: 50)
            //每次延迟100毫秒再启动线程
            handler.postDelayed(this, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(mp3Path)
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)

        /**
         * 第一个是滑块滑动时调用的，第二个是滑动开始滑动时调用的，第三个是滑动停止时调用的。
         */
        musicProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                // fromUser判断是用户改变的滑块的值
                if (fromUser) {
                    mediaPlayer?.seekTo(progress);
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //mediaPlayer?.seekTo(p0?.progress ?: 0)
            }

        })
        tvPlay.setOnClickListener {
            mediaPlayer?.prepareAsync()
            mediaPlayer?.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(p0: MediaPlayer?) {
                    musicProgress.max = mediaPlayer?.duration ?: 100
                    mediaPlayer?.start()
                    ToastUtil.showMsg("开始播放")
                    //启动
                    handler.post(updateThread);
                }

            })

            mediaPlayer?.setOnCompletionListener {
                ToastUtil.showMsg("播放完成")
            }

        }
        tvPause.setOnClickListener {
            mediaPlayer?.pause()
        }

        tvStop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null;
            //取消线程
            handler.removeCallbacks(updateThread);

        }

        tvRetry.setOnClickListener {
            mediaPlayer?.reset()
        }
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}
