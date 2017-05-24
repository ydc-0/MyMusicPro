package cn.edu.xidian.www.mymusicpro.internet;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by chenyudong on 2017/5/16.
 */

public class ConnectServer {
    private String TAG = "ConnectServer";

    private Socket soc = new Socket();
    private String server_ip = new String();
    private int server_port;

    void InitSoc(String ip, int port) {
        server_ip = ip;
        server_port = port;
        Log.i(TAG, "InitSoc: ip:" + server_ip + "port:" + server_port);
    }
    void Connect() {
        try {
            soc.connect(new InetSocketAddress(server_ip,server_port),3000);

            //根据输入输出流和服务端连接
            OutputStream outputStream=soc.getOutputStream();//获取一个输出流，向服务端发送信息
            PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
            printWriter.print("服务端你好，我是Balla_兔子");
            printWriter.flush();
            soc.shutdownOutput();//关闭输出流

            InputStream inputStream=soc.getInputStream();//获取一个输入流，接收服务端的信息
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
            String info="";
            String temp=null;//临时变量
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                System.out.println("客户端接收服务端发送信息："+info);
            }

            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
