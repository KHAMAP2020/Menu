package Server;



import models.LoginData;

import java.io.IOException;
import java.net.*;

public class Server extends Thread
{
   public static ServerSocket server;
   public static boolean loop = true;

    public static Socket client;
    public Server(int port) throws IOException
    {
        String name = "";
        String servername = "";
        int port2 = 0;
        LoginData data = new LoginData(null,null,0);

         server = new ServerSocket(data.getPort());
        System.out.println(port);
     //    server.setSoTimeout(10000);
    }


    @Override
    public void run()
    {
        while(loop){
            try
            {
                if(!client.isConnected()){
                    System.out.println("waiting for client");
                }



                ConnectionListener connectionListener;
                connectionListener = new ConnectionListener();
                connectionListener.start();



            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }

    }

    public static void main(String[] args)

    {
        try
        {
            Server s = new Server(3333);
            s.start();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
