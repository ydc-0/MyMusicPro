package cn.edu.xidian.www.mymusicpro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(R.string.settings_title);
        initView();
    }


    protected void initView() {

    }

    public void OnButtonDefault(View view) {

    }

    public void OnButtonSave(View view) {
    }
}
