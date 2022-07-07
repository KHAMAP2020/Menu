package Server;



import models.LoginData;

import java.io.IOException;
import java.net.*;

public class Server extends Thread
{
   public static ServerSocket server;


    public static Socket client;
    static int port;
    public Server(int port) throws IOException
    {
        this.port = port;



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
                if(!client.isConnected()){
                    System.out.println("waiting for client");
                }else{
                    System.out.println("connected");
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

    public static void main(String[] args) throws IOException {
     server = new ServerSocket(Server.port);
     Server server= new Server(Server.port);
     server.start();
    }
}
