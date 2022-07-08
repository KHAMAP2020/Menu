package Server;



import models.LoginData;
import models.Message;

import java.io.IOException;
import java.net.*;

public class Server extends Thread
{

    public static ServerSocket server;

    public static Socket client;
    int port;

    public Server(int port) throws IOException
    {
       this.port = port;
        server = new ServerSocket(port);
        client = new Socket("localhost",port);

        System.out.println(port);
     //    server.setSoTimeout(10000);
    }


    @Override
    public void run() {

      try {
        ConnectionListener connectionListener;
        connectionListener = new ConnectionListener();
        connectionListener.start();
        MessageController messageController;
        messageController = new MessageController();
        messageController.start();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }


      while (!server.isClosed()) {

        if (!client.isConnected()) {
          System.out.println("waiting for client");
        }

      }

    }

}
