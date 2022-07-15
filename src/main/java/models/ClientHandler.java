package models;

import models.interfaces.GUIConstants.NetworkConstants;
import views.ErrorAlertType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/**
@author Philipp Gohlke 5157842
 */
public class ClientHandler implements Runnable
{
  private static final ArrayList<ClientHandler> clientHandlers
                                            = new ArrayList<>();
  private static Socket socket;
  public static  BufferedReader bufferedReader;
  public static BufferedWriter bufferedWriter;
  private final String clientUsername;
  private static boolean running = NetworkConstants.LOOP_START;
  
  public ClientHandler(Socket socket)
  {
    /*
    Initialisierung der Streams
     */
    try
    {
      ClientHandler.socket = socket;

      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(socket.getOutputStream());
      
      bufferedWriter
        = new BufferedWriter(outputStreamWriter);

      InputStreamReader inputStreamReader =
              new InputStreamReader(socket.getInputStream());
      
      bufferedReader
        = new BufferedReader(inputStreamReader);
      this.clientUsername = bufferedReader.readLine();
      clientHandlers.add(this);
      broadcastMessage(clientUsername + " " +
                       "hat den Chat betreten");
    }
    catch (IOException e)
    {
      ErrorAlertType.INITIALIZATION_FAILED.
              getAlert().showAndWait();
      closeEverything();
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void run()
  {
    String receivingMessage;
     while (running)
     {
        /*
        Solange der Socket eine Verbindung zum Server hat,
        werden ankommende Narichten gelesen und
        der BroadcastMessage-Methode übergeben.
         */
       try
       {
         receivingMessage = bufferedReader.readLine();
         broadcastMessage(receivingMessage);
         
       } catch (IOException e)
       {
         ErrorAlertType.SEND_MESSAGE_FAILED.
                 getAlert().showAndWait();
         closeEverything();


       }
     }
  }
  
  private void broadcastMessage(String messageToSend)
  {
    /*
    Hier werden Narichten an alle Chatteilnehmer gesendet,
    außer an den, der die Naricht verschickt hat.
     */
    for (ClientHandler clientHandler:clientHandlers)
    {
      try
      {
        if(!clientHandler.clientUsername.equals
                                          (this.clientUsername))
        {

          bufferedWriter.write(messageToSend);
          bufferedWriter.newLine();
          bufferedWriter.flush();
          System.out.println(messageToSend);
        }
      } catch (IOException e)
      {
        ErrorAlertType.REICIVE_MESSAGE_FAILED.
                getAlert().showAndWait();
        closeEverything();
        throw new RuntimeException(e);
      }
    }
  }
  
  public void removeClientHandler()
  {
    /*
    Wenn ein Client den Chat verlässt, werden alle informiert
    und der Client wird aus der ArrayList gelöscht
     */
    if(Server.serverSocket != null)
    {
      clientHandlers.remove(this);
      broadcastMessage(this.clientUsername +
              " hat den Chat verlassen");
    }

  }
  public static void closeEverything()
  {
    //removeClientHandler();
    try
    {
      running = false;
      if (socket != null)
      {
          socket.close();
      }
    
      if (bufferedReader != null)
      {
        bufferedReader.close();
      }
    
      if (bufferedWriter != null)
      {
        bufferedWriter.close();
      }
      if(Server.serverSocket != null){
        Server.serverSocket.close();
      }
    } catch (IOException e)
    {
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }
  }
}
