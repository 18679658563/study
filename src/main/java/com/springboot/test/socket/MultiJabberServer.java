package com.springboot.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-15
 * Time: 上午11:02
 */
public class MultiJabberServer{

    static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("Server start...");
        try{
            while(true){
                Socket socket = ss.accept();
                try{
                    new ServerOneJabber(socket);
                }catch (IOException e){
                    socket.close();
                }
            }
        }finally {
            ss.close();
        }
    }
}

class ServerOneJabber extends Thread{
    private Socket socket ;
    private BufferedReader in;
    private PrintWriter out;

    public ServerOneJabber(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    public void run(){
        try{
            while(true){
                String str = in.readLine();
                if("END".equals(str)){
                    break;
                }
                System.out.println("ECHOING : " + str);
                out.println(str);
            }
            System.out.println("close....");
        }catch(IOException e){

        }finally {
            try{
                socket.close();
            } catch (IOException e){

            }
        }
    }
}
