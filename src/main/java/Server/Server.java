package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server
{
   private ServerSocket server;
    public Server(int port) throws IOException
    {
         server = new ServerSocket(3333);
     //    server.setSoTimeout(10000);
    }

    public void run()
    {
        while(true){
            try
            {

                System.out.println("waiting for client");
                Socket client = server.accept();
                DataInputStream input = new DataInputStream(client.getInputStream());
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
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
