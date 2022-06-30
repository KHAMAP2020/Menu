package Server;

import controller.GUIController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server extends Thread
{
   private ServerSocket server;
   public static boolean schleife = true;
    public Server(int port) throws IOException
    {

         server = new ServerSocket(3333);
     //    server.setSoTimeout(10000);
    }


    @Override
    public void run()
    {
        while(schleife){
            try
            {
                String line;
                System.out.println("waiting for client");
                Socket client = server.accept();
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
