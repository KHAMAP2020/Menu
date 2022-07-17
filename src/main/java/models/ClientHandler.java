package models;

import models.interfaces.GUIConstants.NetworkConstants;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/**
@author Philipp Gohlke 5157842
 */
public class ClientHandler implements Runnable
{
  private static ArrayList<ClientHandler> clientHandlers
                                            = new ArrayList<>();
  private Socket socket;
  private   BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;
  private final String clientUsername;
  private static boolean running = NetworkConstants.LOOP_START;

  public static boolean userCount = false;
  
  public ClientHandler(Socket socket)
  {
    /*
    Initialisierung der Streams
     */
    try
    {
      ClientHandler.this.socket = socket;

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
         closeEverything();
         ErrorAlertType.SEND_MESSAGE_FAILED.
                 getAlert().showAndWait();

       }
       catch (NullPointerException e)
       {
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
        if
        (!(clientHandler == this))
        {
          clientHandler.bufferedWriter.write(messageToSend);
          clientHandler.bufferedWriter.newLine();
          clientHandler.bufferedWriter.flush();
          System.out.println(clientHandler.clientUsername +
                  " bekommt von "+ messageToSend);
        }
      } catch (IOException e)
      {
        System.out.println("clienthandler 111");
        ErrorAlertType.SEND_MESSAGE_FAILED.
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
  public void closeEverything()
  {
    removeClientHandler();
    try
    {
      String firstName;

      running = false;
      if (this.socket != null)
      {
        this.socket.close();
      }
    
      if (this.bufferedReader != null)
      {
        this.bufferedReader.close();
      }
    
      if (this.bufferedWriter != null)
      {
        this.bufferedWriter.close();
      }

    } catch (IOException e)
    {
      e.printStackTrace();
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }
  }
}
