package Server;

import models.Message;

import java.io.*;

public class MessageListener extends Thread
{
    public MessageListener()
    {

    }

    @Override
    public void run()
    {

        try
        {
            BufferedWriter output;
            new BufferedWriter(new OutputStreamWriter(Server.client.getOutputStream()));
            BufferedReader input;
            new BufferedReader(new InputStreamReader(Server.client.getInputStream()));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }



    }

    public static void main(String[] args)
    {

    }
}
