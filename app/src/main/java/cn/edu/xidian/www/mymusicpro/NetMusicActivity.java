package cn.edu.xidian.www.mymusicpro;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xidian.www.mymusicpro.internet.Player;
import cn.edu.xidian.www.mymusicpro.music.MusicUtils;
import cn.edu.xidian.www.mymusicpro.music.SongInfo;

public class NetMusicActivity extends AppCompatActivity {

    private String TAG = "NetMusicActivity";


    private List<SongInfo> list = new ArrayList<>();
    private int order_song = 0;

    private String song_name;
    private String song_singer;
    private String song_url;

    private SeekBar musicProgress;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_music);
        setTitle(R.string.music_recommendation_title);
        InitMuiicList();
        InitView();
        musicProgress = (SeekBar) findViewById(R.id.music_progress);
        player = new Player(musicProgress);
        musicProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
    }

    private void InitMuiicList() {
        list.clear();
        list = MusicUtils.getMusicUrlList();
    }

    private void InitView() {
        int total_song = list.size();
        Log.i(TAG, "InitView: total_song:" + total_song);
        if (total_song == 0) {

            TextView msg_text = (TextView) findViewById(R.id.net_song_msg);
            msg_text.setText(R.string.net_music_null);
            Toast.makeText(getApplicationContext(), R.string.net_music_null, Toast.LENGTH_LONG).show();
            return;
        }
        if (order_song < 0 || order_song >= total_song)  {
            order_song = 0;
        }
        Log.i(TAG, "InitView: order:" + order_song);
        TextView msg_text = (TextView) findViewById(R.id.net_song_msg);
        msg_text.setText(String.format(getString(R.string.net_message), order_song + 1));
        TextView song_text = (TextView) findViewById(R.id.net_music_song_text);
        song_text.setText(list.get(order_song).song);
        TextView url_text = (TextView) findViewById(R.id.net_music_song_url);
        url_text.setText(list.get(order_song).path);
        Log.i(TAG, "InitView: song:" + song_name + " singer:" + song_singer + " path:" + song_url);


    }

    public void OnButtonPlayOnline(View view) {
        TextView pathText = (TextView) findViewById(R.id.net_music_song_url);
        final String url = pathText.getText().toString();
        Log.i(TAG, "OnButtonPlayOnline: url:" + url);
        musicProgress.setProgress(0);

        player.PlayNet(url);
    }

    public void OnButtonStop(View view) {
        musicProgress.setProgress(0);
        player.stop();
    }

    public void OnButtonChangeSong(View view) {
        musicProgress.setProgress(0);
        player.stop();
        order_song ++;
        InitView();
    }

    // 进度改变
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity
        player.stop();
    }
}
