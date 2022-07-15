package models;

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
      closeEverything();
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void run()
  {
    String receivingMessage;
     while (this.socket.isConnected())
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
    if(!AServer.serverSocket.isClosed())
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
      if(AServer.serverSocket != null){
        AServer.serverSocket.close();
      }
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
