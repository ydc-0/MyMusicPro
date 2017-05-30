package cn.edu.xidian.www.mymusicpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.io.StringReader;

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
        edit_text = (EditText) findViewById(R.id.server_port_text);
        edit_text.setText(String.valueOf(SettingDat.server_port));

        StringReader sr = new StringReader(SettingDat.personal_label);
        int offset = 0;
        char ch;
        TableLayout label_table = (TableLayout) findViewById(R.id.label_table);
        for(int i = 0; i < label_table.getChildCount(); i++) {
            TableRow label_row = (TableRow) label_table.getChildAt(i);
            for(int j = 0; j < label_row.getChildCount(); j++) {
                if(offset < SettingDat.personal_label.length()) {
                    ch = SettingDat.personal_label.charAt(offset);
                    offset ++;
                } else{
                    ch = '0';
                }
                if(ch == '1') {
                    CheckBox check = (CheckBox) label_row.getChildAt(j);
                    check.setChecked(true);
                }
            }
        }

        edit_text = (EditText) findViewById(R.id.usr_setting_size);
        edit_text.setText(String.valueOf(SettingDat.file_size));

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

        String tmp = "";
        TableLayout label_table = (TableLayout) findViewById(R.id.label_table);
        for(int i = 0; i < label_table.getChildCount(); i++) {
            TableRow label_row = (TableRow) label_table.getChildAt(i);
            for(int j = 0; j < label_row.getChildCount(); j++) {
                CheckBox check = (CheckBox) label_row.getChildAt(j);
                tmp += check.isChecked() ? "1" : "0";
                Log.i(TAG, "OnButtonSave: tmp:" + tmp);
            }
        }
        SettingDat.personal_label = tmp;

        edit_text = (EditText) findViewById(R.id.usr_setting_size);
        SettingDat.file_size = Integer.parseInt(edit_text.getText().toString());

        SettingDat.SaveDatToPref();

    }

    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity

    }
}
