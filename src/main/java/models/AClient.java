package models;

import controller.AMessageController;

import java.io.*;
import java.net.Socket;
/**
@author Philipp Gohlke 5157842
 */
public class AClient
{
  public static Socket socket;
  private final BufferedReader bufferedReader;
  private final BufferedWriter bufferedWriter;
  private final String userName;
  
  public AClient(String serverName, int port, String userName)
  {
    try
    {
      // Die Initialisierung des Sockets und der Streams
      this.socket = new Socket(serverName,port);

      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(socket.getOutputStream());
      this.bufferedWriter
        = new BufferedWriter(outputStreamWriter);

      InputStreamReader inputStreamReader =
              new InputStreamReader(socket.getInputStream());
      this.bufferedReader
        = new BufferedReader(inputStreamReader);
      
      this.userName = userName;
      /*
      Name der Person, die beitritt wird an den Server
      übergeben um den anderen Teilnehmern zu zeigen
      wer beigetreten ist.
       */
      bufferedWriter.write(userName);
      bufferedWriter.newLine();
      bufferedWriter.flush();
      listenForMessage();
    } catch (IOException e)
    {
      closeEverything();
      throw new RuntimeException(e);
    }
  }
  
  public void sendMessage(String messageToSend)
  {
    try
    {
      if(socket.isConnected()){
         /*
      Führt das senden von Narichten, vom Client aus, aus
       */
        bufferedWriter.write
                (userName + ": " + messageToSend);
        bufferedWriter.newLine();
        bufferedWriter.flush();
      }

    } catch (IOException e)
    {
      closeEverything();
      throw new RuntimeException(e);
    }
  }
  
  public void listenForMessage()
  {
    new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        String receivingMessage;
        
        while (socket.isConnected())
        {
          try
          {
            /*
            wartet auf eingehende Narichten, solange der
            Socket eine Verbindung zum Server hat
             */
            receivingMessage = bufferedReader.readLine();
            AMessageController.incommingMessage
                                             (receivingMessage);
            //System.out.println(receivingMessage);

          } catch (IOException e)
          {
            closeEverything();
          }
        }
      }
    }).start();
  }
  
  public void closeEverything()
  {
    /*
    für den Fall das ein Catch ausgelöst wurde, soll diese
    Methode für ein sicheres schließen der
    Sockets etc. sorgen
     */
    try
    {
      if (socket != null)
      {
        socket.close();
      }
    
      if (this.bufferedReader != null)
      {
        this.bufferedReader.close();
      }
    
      if (this.bufferedWriter != null)
      {
        this.bufferedWriter.close();
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
