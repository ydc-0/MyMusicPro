package cn.edu.xidian.www.mymusicpro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.edu.xidian.www.mymusicpro.settings.SettingDat;

public class SettingsActivity extends AppCompatActivity {
    private String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        initView();
        setTitle(R.string.settings_title);

    }


    protected void initView() {
        EditText edit_text = (EditText) findViewById(R.id.usr_name_text);
        edit_text.setText(SettingDat.usr_name);
        edit_text = (EditText) findViewById(R.id.usr_email_text);
        edit_text.setText(SettingDat.usr_email);
        edit_text = (EditText) findViewById(R.id.usr_addr_text);
        edit_text.setText(SettingDat.usr_addr);
        edit_text = (EditText) findViewById(R.id.server_addr_text);
        edit_text.setText(SettingDat.server_ip);
        //String TestString = "1234";
        edit_text = (EditText) findViewById(R.id.server_port_text);
        edit_text.setText(String.valueOf(SettingDat.server_port));
    }

    public void OnButtonDefault(View view) {
        SettingDat.SetDatDefault(this);
        initView();
    }

    public void OnButtonSave(View view) {
        EditText edit_text = (EditText) findViewById(R.id.usr_name_text);
        SettingDat.usr_name = edit_text.getText().toString();
        edit_text = (EditText) findViewById(R.id.usr_email_text);
        SettingDat.usr_email = edit_text.getText().toString();
        edit_text = (EditText) findViewById(R.id.usr_addr_text);
        SettingDat.usr_addr = edit_text.getText().toString();
        edit_text = (EditText) findViewById(R.id.server_addr_text);
        SettingDat.server_ip = edit_text.getText().toString();
        edit_text = (EditText) findViewById(R.id.server_port_text);
        SettingDat.server_port = Integer.parseInt(edit_text.getText().toString());
        Log.i(TAG, "OnButtonSave: server_ip:" + SettingDat.server_ip + " server_port:" + SettingDat.server_port);
        SettingDat.SaveDatToPref();
    }

    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity

    }
}
