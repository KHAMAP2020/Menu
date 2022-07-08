package Server;

import java.io.IOException;

public class ConnectionListener extends Thread
{


    public ConnectionListener() throws IOException
    {

    }

    @Override
    public void run()
    {
        try
        {

            while(true)
            {
                Server.client = Server.server.accept();
                System.out.println("Server akzeptiert");
            }

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args)
    {

    }
}
