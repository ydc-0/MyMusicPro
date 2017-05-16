package cn.edu.xidian.www.mymusicpro;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xidian.www.mymusicpro.music.MusicUtils;
import cn.edu.xidian.www.mymusicpro.music.MyAdapter;
import cn.edu.xidian.www.mymusicpro.music.SongInfo;

public class MusicListActivity extends AppCompatActivity {
    private String TAG = "MusicListActivity";
    private ListView mListView;
    private List<SongInfo> list;
    private MyAdapter adapter;

    private MediaPlayer mediaPlayer;//播放音频的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        setTitle(R.string.local_music_title);
        initView();

        mediaPlayer = new MediaPlayer();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.main_listview);
        list = new ArrayList<>();
        //把扫描到的音乐赋值给list
        list = MusicUtils.getMusicData();
        adapter = new MyAdapter(this, list);
        mListView.setAdapter(adapter);
        TextView up_text = (TextView) findViewById(R.id.muisic_list_text);
        up_text.setText(String.format(getString(R.string.local_music_up_text), mListView.getCount()));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //创建一个播放音频的方法，把点击到的地址传过去
                //list.get(i).path这个就是歌曲的地址
                play(list.get(i).path);
            }
        });
    }

    /**
     * 播放音频的方法
     */
    private void play(String path) {
        //播放之前要先把音频文件重置
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            //调用方法传进去要播放的音频路径
            mediaPlayer.setDataSource(path);
            //异步准备音频资源
            mediaPlayer.prepareAsync();
            //调用mediaPlayer的监听方法，音频准备完毕会响应此方法
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();//开始音频
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity
        mediaPlayer.stop();
    }
}
