package cn.edu.xidian.www.mymusicpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xidian.www.mymusicpro.appfile.MuiscDataFile;
import cn.edu.xidian.www.mymusicpro.internet.ConnectServer;
import cn.edu.xidian.www.mymusicpro.music.MusicUtils;
import cn.edu.xidian.www.mymusicpro.music.SongInfo;
import cn.edu.xidian.www.mymusicpro.settings.SettingDat;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_title);
        SettingDat.InitSettingDat(this);

        // search local music
        Toast.makeText(getApplicationContext(), R.string.local_music_search, Toast.LENGTH_LONG).show();
        MusicUtils.SearchLocalMusic(this);

        //socket thread
        Thread soc_th = new ConnectServer(this);
        soc_th.start();

    }

    public void StartLocalMusic(View view) {
        Log.i(TAG, "StartLocalMusic: start.");
        Intent intent = new Intent(this, MusicListActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);    }

    public void MusicRecommendation(View view) {
        Log.i(TAG, "MusicRecommendation: start.");
        Intent intent = new Intent(this, NetMusicActivity.class);
        startActivity(intent);
    }

    public void EnterSettings(View view) {
        Log.i(TAG, "EnterSettings: start.");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity

    }
}
