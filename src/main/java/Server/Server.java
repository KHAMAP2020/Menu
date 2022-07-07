package Server;



import models.LoginData;

import java.io.IOException;
import java.net.*;

public class Server extends Thread
{

    public static ServerSocket server;

    static int port;
    public static Socket client;


    public Server(int port) throws IOException
    {
        this.port = port;
        client = new Socket("localhost",port);
        server = new ServerSocket(port);


        System.out.println(port);
     //    server.setSoTimeout(10000);
    }


    @Override
    public void run()
    {
      System.out.println("server run");
        while(true){

            try
            {
                ConnectionListener connectionListener;
                connectionListener = new ConnectionListener();
                connectionListener.start();
                if(!client.isConnected()){
                    System.out.println("waiting for client");
                }





            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }

    }


}
