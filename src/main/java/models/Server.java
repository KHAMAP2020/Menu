package models;

import javafx.application.Platform;
import models.interfaces.GUIConstants.NetworkConstants;
import views.ErrorAlertType;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import static controller.GUIController.close;

/**
@author Philipp Gohlke 5157842
 */

public class Server extends Thread
{
  public static ServerSocket serverSocket;
  static Socket socket;
  private static boolean running;
  public static boolean startServer;
  
  private static ClientHandler clientHandler;
  public Server(int port)
  {
    try
    {
      running  = NetworkConstants.LOOP_START;
      serverSocket = new ServerSocket(port);
      startServer = true;
    } catch (BindException e){
      ErrorAlertType.PORT_ALREADY_IN_USE.
              getAlert().showAndWait();
      startServer = false;
      System.out.println("port nicht vorhanden");
    }
    catch (IOException e)
    {
      startServer = false;
      ErrorAlertType.PORT_ALREADY_IN_USE.
              getAlert().showAndWait();
      e.printStackTrace();
    }
  }
  
  @Override
  public void run()
  {
    System.out.println("Server gestartet");
    try
    {
      while (running)
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
  
        clientHandler = new ClientHandler(socket);

        Thread thread = new Thread(clientHandler);
        thread.start();
      }
    } catch (ConnectException e)
    {
      Platform.runLater(new Runnable()
      {
        @Override
        public void run()
        {
          if(!close){
            ErrorAlertType.SERVER_CONNECT_FAILED.
                    getAlert().showAndWait();
          }
        }
      });

      closeServerSocket();
    }catch(NullPointerException e){
      /*
      Wird zum Beispiel ausgelöst, wenn ein 2. Server auf den
      gleichen port versucht wird zu erstellen.
       */
    }catch(IOException e){

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
      running = NetworkConstants.LOOP_STOP;
      if(Server.socket != null){
        socket.close();
      }
      if(serverSocket != null)
      {
        serverSocket.close();
      }
      if (clientHandler != null)
      {
        clientHandler.closeEverything();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}


