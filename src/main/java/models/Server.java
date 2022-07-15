package models;

import views.ErrorAlertType;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
/**
@author Philipp Gohlke 5157842
 */

public class Server extends Thread
{
  public static ServerSocket serverSocket;
  static Socket socket;
  public Server(int port)
  {
    try
    {
      serverSocket = new ServerSocket(port);
    } catch (BindException e){
      ErrorAlertType.PORT_ALREADY_IN_USE.
              getAlert().showAndWait();
    }
    catch (IOException e)
    {
      ErrorAlertType.HOSTING_FAILED.getAlert().showAndWait();
      e.printStackTrace();
    }
  }
  
  @Override
  public void run()
  {
    System.out.println("Server gestartet");
    try
    {
      while (!serverSocket.isClosed())
      {
        /*
        Solange der Serversocket nicht geschlossen ist,
        wird jeder eingehende Verbindungsversuch angenommen.
        Daraufhin wird ein neuer Client erzeugt und in der
        Client ArrayList hinzugefügt
         */


        socket = serverSocket.accept();

        System.out.println
                        ("ein neuer Client hat sich verbunden");

        ClientHandler clientHandler = new ClientHandler(socket);

        Thread thread = new Thread(clientHandler);
        thread.start();
      }
    } catch (IOException e)
    {
      ErrorAlertType.SERVER_CONNECT_FAILED.
              getAlert().showAndWait();
      closeServerSocket();

    }
  }
  
  public static void closeServerSocket()
  {
    /*
    zur sicheren Schließung des Serversockets bei
    möglichen Fehlermledungen.
     */
    try
    {
      if(Server.socket != null){
        socket.close();
      }
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


