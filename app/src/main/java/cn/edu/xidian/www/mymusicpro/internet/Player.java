package cn.edu.xidian.www.mymusicpro.internet;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenyudong on 2017/5/26.
 */

public class Player implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener {

    public MediaPlayer mediaPlayer; // 媒体播放器
    private String TAG = "Player";
    private SeekBar seekBar; // 拖动条
    private Timer mTimer = new Timer(); // 计时器

    private boolean isplaying;


    // 初始化播放器
    public Player(SeekBar seekBar) {
        super();
        this.seekBar = seekBar;
        isplaying = false;
        mediaPlayer = new MediaPlayer();

        //调用mediaPlayer的监听方法，音频准备完毕会响应此方法
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);


        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        // 每一秒触发一次
        mTimer.schedule(timerTask, 0, 1000);
    }

    public void PlayLocal(String path)
    {
        if(isplaying)
        {
            mediaPlayer.stop();
        }
        //播放之前要先把音频文件重置
        try {
            mediaPlayer.reset();
            //调用方法传进去要播放的音频路径
            mediaPlayer.setDataSource(path);
            //异步准备音频资源
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PlayNet(String url)
    {
        if(isplaying)
        {
            mediaPlayer.stop();
        }
        try {

            //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepareAsync(); // prepare自动播放 异步
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 计时器
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            if (mediaPlayer == null)
                return;
            if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
                handler.sendEmptyMessage(0); // 发送消息
            }
        }
    };

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                long pos = seekBar.getMax() * position / duration;
                seekBar.setProgress((int) pos);
            }
        };
    };

    public void play() {
        mediaPlayer.start();
    }

    /**
     *
     * @param url
     *            url地址
     */
    public void playUrl(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepare(); // prepare自动播放
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暂停
    public void pause() {
        mediaPlayer.pause();
    }

    // 停止
    public void stop() {
        isplaying = false;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            seekBar.setProgress(0);
        }
    }

    // 播放准备
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Log.i(TAG, "onPrepared: ");
    }

    // 播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("mediaPlayer", "onCompletion");
        isplaying = false;
    }

    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);
        int currentProgress = seekBar.getMax()
                * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
        Log.e(currentProgress + "% play", percent + " buffer");
    }


}
