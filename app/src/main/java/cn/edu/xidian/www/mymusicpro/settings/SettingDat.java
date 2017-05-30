package cn.edu.xidian.www.mymusicpro.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import cn.edu.xidian.www.mymusicpro.R;



/**
 * Created by chenyudong on 2017/5/24.
 */

public class SettingDat {
    private static String TAG = "SteeingDat";
    private static SharedPreferences sharedPref;
    private static String key_check_usr_set;

    private static String key_usr_name;
    private static String key_usr_email;
    private static String key_usr_addr;
    private static String key_server_ip;
    private static String key_server_port;
    private static String key_personal_label;
    private static String key_file_size;

    public static String usr_name;
    public static String usr_email;
    public static String usr_addr;
    public static String server_ip;
    public static int server_port;
    public static String personal_label;
    public static int file_size;


    public static void InitSettingDat(Context context){

        sharedPref = context.getSharedPreferences(context.getString(R.string.pre_set), Context.MODE_PRIVATE);
        key_usr_name = context.getString(R.string.key_usr_name);
        key_usr_email = context.getString(R.string.key_usr_email);
        key_usr_addr = context.getString(R.string.key_usr_addr);
        key_server_ip = context.getString(R.string.key_server_ip);
        key_server_port = context.getString(R.string.key_server_port);
        key_personal_label = context.getString(R.string.key_personal_label);
        key_file_size = context.getString(R.string.key_file_size);

        key_check_usr_set = context.getString(R.string.pre_set_check);
        boolean has_usr_set = sharedPref.getBoolean(key_check_usr_set, false);
        if(has_usr_set){
            Log.i(TAG, "InitSettingDat: preference exist,read.");
            GetDatFromPref();
        }else{
            Log.i(TAG, "InitSettingDat: preference doesn't exist.");
            SetDatDefault(context);
            SaveDatToPref();
        }
    }

    public static void SaveDatToPref() {
        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key_usr_name, usr_name);
        editor.putString(key_usr_email, usr_email);
        editor.putString(key_usr_addr, usr_addr);
        editor.putString(key_server_ip, server_ip);
        editor.putInt(key_server_port, server_port);
        editor.putString(key_personal_label, personal_label);
        editor.putInt(key_file_size, file_size);
        editor.putBoolean(key_check_usr_set, true);
        editor.commit();
    }

    public static void SetDatDefault(Context context) {
        usr_name = context.getString(R.string.setting_usr_name_text);
        usr_email = context.getString(R.string.setting_usr_email_text);
        usr_addr = context.getString(R.string.setting_usr_addr_text);
        server_ip = context.getString(R.string.setting_net_server_addr_text);
        server_port = Integer.parseInt(context.getString(R.string.setting_net_server_port_text));
        personal_label = "0000000000";
        file_size = Integer.parseInt(context.getString(R.string.setting_min_size));
        Log.i(TAG, "SetDatDefault: server ip:" + server_ip + "server_port:" + server_port);
        personal_label = "";
    }

    private static void GetDatFromPref() {
        usr_name = sharedPref.getString(key_usr_name, "");
        usr_email = sharedPref.getString(key_usr_email, "");
        usr_addr = sharedPref.getString(key_usr_addr, "");
        server_ip = sharedPref.getString(key_server_ip, "");
        server_port = sharedPref.getInt(key_server_port, 0);
        personal_label = sharedPref.getString(key_personal_label, "");
        file_size = sharedPref.getInt(key_file_size, 800);

        Log.i(TAG, "GetDatFromPref: server ip:" + server_ip + "server_port:" + server_port);
    }

}
