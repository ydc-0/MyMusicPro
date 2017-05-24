package cn.edu.xidian.www.mymusicpro.settings;

import android.content.Context;
import android.util.Log;

import cn.edu.xidian.www.mymusicpro.R;

/**
 * Created by chenyudong on 2017/5/24.
 */

public class SettingDat {
    private static String TAG = "SteeingDat";
    private static String usr_name;
    private static String usr_email;

    public static void InitSettingDat(Context context){

        usr_name = context.getResources().getString(R.string.setting_usr_name_text);
        //Log.i(TAG, "InitSettingDat: usr_name:" + usr_name);
    }

}
