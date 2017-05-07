package cn.edu.xidian.www.mymusicpro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuInflater;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(R.string.settings_title);
    }

}
