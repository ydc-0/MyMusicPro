package cn.edu.xidian.www.mymusicpro.internet;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.edu.xidian.www.mymusicpro.music.MusicUtils;
import cn.edu.xidian.www.mymusicpro.music.SongInfo;
import cn.edu.xidian.www.mymusicpro.settings.SettingDat;
import cn.edu.xidian.www.mymusicpro.appfile.MuiscDataFile;

/**
 * Created by chenyudong on 2017/5/16.
 */

public class ConnectServer extends Thread{
    private String TAG = "ConnectServer";

    private Socket soc;
    private String server_ip;
    private int server_port;

    private MuiscDataFile music_file;
    private List<SongInfo> local_list = new ArrayList<>();
    private List<SongInfo> net_list = new ArrayList<>();
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader bufferedReader;


    public ConnectServer(Context context) {
        Log.i(TAG, "ConnectServer: ");
        music_file = new MuiscDataFile(context);
        local_list = MusicUtils.getMusicData();
        net_list = music_file.ReadUrlList();
        MusicUtils.InitUrlMusic(net_list);
    }

    @Override
    public void run() {
        super.run();
        try {
            InitSoc(SettingDat.server_ip,SettingDat.server_port);
            SendData();
            RecvData();

            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            outputStream.close();
            soc.close();
        } catch (IOException e) {
            Log.i(TAG, "run: Soc ERROR");
            e.printStackTrace();
        }


    }



    private void InitSoc(String ip, int port) throws IOException {
        server_ip = ip;
        server_port = port;
        soc = new Socket();
        Log.i(TAG, "InitSoc: ip:" + server_ip + "port:" + server_port);
        soc.connect(new InetSocketAddress(server_ip,server_port),3000);
    }

    private void SendData() throws IOException {
        //根据输入输出流和服务端连接
        outputStream=soc.getOutputStream();//获取一个输出流，向服务端发送信息
        String send_dat = "";
        send_dat += SettingDat.usr_name + "$" + SettingDat.usr_email + "$" + SettingDat.usr_addr + "$" + SettingDat.personal_label;

        for(int i = 0;i<local_list.size();i++) {
            SongInfo song = local_list.get(i);
            String[] path_s = song.path.split("\\/");
            String file_n = path_s[path_s.length - 1];
            //Log.i(TAG, "SendData: path:" + song.path + "\nfile:" + file_n);
            send_dat += "\n" + file_n + "$" + song.song + "$" + song.singer;
        }

        send_dat += "\nend";
        //Log.i(TAG, "SendData: " + send_dat);

        byte[] send_byte = send_dat.getBytes();
        Log.i(TAG, "SendData: " +" len:" + send_byte.length);
        outputStream.write(send_byte,0,send_byte.length);
        soc.shutdownOutput();//关闭输出流

    }

    private void RecvData() throws IOException {
        Log.i(TAG, "RecvData: ");

        inputStream=soc.getInputStream();//获取一个输入流，接收服务端的信息
        bufferedReader=new BufferedReader(new InputStreamReader(inputStream));//缓冲区
        String recv_dat="";
        String temp = "";//临时变量
        while((temp=bufferedReader.readLine())!=null){
            recv_dat += temp + "\n";
        }
        Log.i(TAG, "RecvData: recv" + recv_dat);

        String[] StrArray = recv_dat.split("\n");

        String[] info_arr = StrArray[0].split("\\$");
        if(info_arr.length!=2)
        {
            Log.e(TAG, "RecvData: fist line error!" + StrArray[0]);
        }
        else {
            int song_num = Integer.parseInt(info_arr[0]);
            String label = info_arr[1];
        }
        net_list.clear();
        for (int i = 1; i < StrArray.length; i++) {
            String[] Array2 = StrArray[i].split("\\$");
            if(Array2.length != 2)
                continue;
            SongInfo songinfo = new SongInfo();
            songinfo.song = Array2[0];
            songinfo.path = Array2[1];
            net_list.add(songinfo);
            Log.i(TAG, "RecvData: song" +songinfo.song + songinfo.path);

        }
        MusicUtils.InitUrlMusic(net_list);
        music_file.SaveUrlList(net_list);



    }

}
