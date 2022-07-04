package Server;

import controller.GUIController;
import models.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server extends Thread
{
   private ServerSocket server;
   public static boolean loop = true;
    public Server(int port) throws IOException
    {
        Client client = new Client(null,null, port);
         server = new ServerSocket(3333);
        System.out.println(port);
     //    server.setSoTimeout(10000);
    }


    @Override
    public void run()
    {
        while(loop){
            try
            {
                String line;
                System.out.println("waiting for client");
                Socket client = server.accept();
                System.out.println("Server akzeptiert");
                DataInputStream input = new DataInputStream(client.getInputStream());
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                System.out.println("gelesen:" + input.readUTF());
                output.writeUTF("Naricht angekommen");


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
            s.run();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
