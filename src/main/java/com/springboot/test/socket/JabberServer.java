package com.springboot.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * Created with IntelliJ IDEA.
 * Description: 服务端程序
 * User: silence
 * Date: 2019-03-14
 * Time: 下午3:37
 */
public class JabberServer {

    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Started: " + serverSocket);
        try{
            Socket socket = serverSocket.accept();
            try{
                System.out.println("Connection accepted:" + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                while(true){
                    String str = in.readLine();
                    if("END".equals(str)){
                        break;
                    }
                    System.out.println("Echoing: " + str);
                    out.println(str);
                }
            }finally {
                System.out.println("closing......");
                socket.close();
            }
        }finally{
            serverSocket.close();
        }
    }


}
