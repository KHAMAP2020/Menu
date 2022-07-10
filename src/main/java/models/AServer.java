package models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
@author Philipp Gohlke 5157842
 */

public class AServer extends Thread
{
  private final ServerSocket serverSocket;
  
  public AServer(int port)
  {
    try
    {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e)
    {
      System.out.println("Fehler beim Serverkonstruktor");
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void run()
  {
    System.out.println("Server gestartet");
    try
    {
      while (!this.serverSocket.isClosed())
      {
        /*
        Solange der Serversocket nicht geschlossen ist,
        wird jeder eingehende Verbindungsversuch angenommen.
        Daraufhin wird ein neuer Client erzeugt und in der
        Client ArrayList hinzugefügt
         */
        Socket socket;

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
    /*
    zur sicheren Schließung des Serversockets bei
    möglichen Fehlermledungen.
     */
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


