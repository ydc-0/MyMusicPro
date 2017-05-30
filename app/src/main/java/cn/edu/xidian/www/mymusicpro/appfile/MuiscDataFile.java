package cn.edu.xidian.www.mymusicpro.appfile;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xidian.www.mymusicpro.MainActivity;
import cn.edu.xidian.www.mymusicpro.music.SongInfo;

/**
 * Created by chenyudong on 2017/5/24.
 */

public class MuiscDataFile {
    private String TAG = "MuiscDataFile";

    private String LogFile = "NetMuisc.log";
    private List<SongInfo> music_list = new ArrayList<>();

    private File mfile;
    private Context mcontext;

    private String encoding="GBK";



    public MuiscDataFile(Context context) {
        mcontext = context;
        mfile = context.getFilesDir();
        Log.i(TAG, "MuiscDataFile: dir:" + mfile.toString());
    }


    public void SaveUrlList(List<SongInfo> list) {
        Log.i(TAG, "SaveUrlMusic:");
        if(list.size() == 0) {
            return;
        }

        FileOutputStream outputStream;
        try {
            outputStream  = mcontext.openFileOutput(LogFile, Context.MODE_PRIVATE);
            SongInfo song;
            String sb = "";
            for(int i=0; i < list.size(); i++) {
                song = list.get(i);
                sb += song.song + "$" + song.path + "\n";
            }
            //Log.i(TAG, "SaveUrlList: sb:\n" + sb);
            outputStream.write(sb.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<SongInfo> ReadUrlList() {
        Log.i(TAG, "ReadUrlList: ");

        music_list.clear();
        try {
            FileInputStream input = mcontext.openFileInput(LogFile);
            String tmp = "";
            byte[] read_byte = new byte[1024];
            int len = 0;
            while ((len = input.read(read_byte)) > 0) {
                tmp += new String(read_byte, 0, len);
            }

            String[] StrArray = tmp.split("\n");
            for (int i = 0; i < StrArray.length; i++) {
                String[] Array2 = StrArray[i].split("\\$");
                if(Array2.length != 2)
                    continue;
                SongInfo songinfo = new SongInfo();
                songinfo.song = Array2[0];
                songinfo.path = Array2[1];
                music_list.add(songinfo);
                //Log.i(TAG, "ReadUrlList: line:" + Array2[0] + " " + Array2[1] + " " + Array2[2]);
            }
        } catch (IOException e) {
            Log.i(TAG, "ReadUrlList: Fail!");
            e.printStackTrace();
        }

        return music_list;


    }



    //File file = new File(context.getFilesDir(), filename);
}
