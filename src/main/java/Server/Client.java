package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client
{


    public Client() throws IOException
    {
    }

    public static void main(String[] args)
    {
        try
        {
        Socket client = new Socket("localhost",3333);
        System.out.println("Client startet");
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        DataInputStream in = new DataInputStream(client.getInputStream());
        System.out.println(in.readUTF());
        System.out.println("Naricht gelesen");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
