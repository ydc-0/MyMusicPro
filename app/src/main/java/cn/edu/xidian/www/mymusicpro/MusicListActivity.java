package cn.edu.xidian.www.mymusicpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        setTitle(R.string.local_music_title);
        initView();
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
    }
}
