package cn.edu.xidian.www.mymusicpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.edu.xidian.www.mymusicpro.music.MusicUtils;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_title);
        Toast.makeText(getApplicationContext(), "扫描本地歌曲...", Toast.LENGTH_LONG).show();
        MusicUtils.SearchLocalMusic(this);
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
    }

    public void EnterSettings(View view) {
        Log.i(TAG, "EnterSettings: start.");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
