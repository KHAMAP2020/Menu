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
  private final Socket socket;
  private final BufferedReader bufferedReader;
  private final BufferedWriter bufferedWriter;
  private final String clientUsername;
  
  public ClientHandler(Socket socket)
  {
    /*
    Initialisierung der Streams
     */
    try
    {
      this.socket = socket;

      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
      
      this.bufferedWriter
        = new BufferedWriter(outputStreamWriter);

      InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
      
      this.bufferedReader
        = new BufferedReader(inputStreamReader);
      this.clientUsername = this.bufferedReader.readLine();
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
         receivingMessage = this.bufferedReader.readLine();
         broadcastMessage(receivingMessage);
         
       } catch (IOException e)
       {
         closeEverything();
         throw new RuntimeException(e);
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
        if(!clientHandler.clientUsername.equals(this.clientUsername))
        {

          clientHandler.bufferedWriter.write(messageToSend);
          clientHandler.bufferedWriter.newLine();
          clientHandler.bufferedWriter.flush();
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
    clientHandlers.remove(this);
    broadcastMessage(this.clientUsername +
                     " hat den Chat verlassen");
  }
  public void closeEverything()
  {
    removeClientHandler();
    try
    {
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
    }
  }
}
