package models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AServer
{
  private ServerSocket serverSocket= null;
  
  public AServer(int port)
  {
    try
    {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e)
    {
      System.out.println("Fehler beim Serverkonstrultor");
      throw new RuntimeException(e);
    }
  }
  
  public void startAServer()
  {
    try
    {
      while (!this.serverSocket.isClosed())
      {
        Socket socket = null;
        
        socket = serverSocket.accept();
        
        System.out.println("ein neuer Client hat sich verbunden");
        
        ClientHandler clientHandler = new ClientHandler(socket);
        
        Thread thread = new Thread(clientHandler);
        thread.start();
      }
    } catch (IOException e)
    {
      closeServerSocket();
      throw new RuntimeException(e);
    }
  }
  
  public void closeServerSocket()
  {
    try
    {
      if(serverSocket != null)
      {
        serverSocket.close();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}


