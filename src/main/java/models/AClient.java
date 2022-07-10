package models;

import controller.AMessageController;
import controller.GUIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import views.ChatPane;

import java.io.*;
import java.net.Socket;
/*
@author Philipp Gohlke 5157842
 */
public class AClient
{
  private Socket socket = null;
  private BufferedReader bufferedReader = null;
  private BufferedWriter bufferedWriter = null;
  
  private OutputStreamWriter outputStreamWriter = null;
  
  private InputStreamReader inputStreamReader = null;
  private String userName = null;
  
  public AClient(String serverName, int port, String userName)
  {
    try
    {
      // Die Initialisierung des Sockets und Streams
      this.socket = new Socket(serverName,port);
      
      this.outputStreamWriter
        = new OutputStreamWriter(socket.getOutputStream());
      this.bufferedWriter
        = new BufferedWriter(this.outputStreamWriter);
  
      this.inputStreamReader
        = new InputStreamReader(socket.getInputStream());
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
      closeEverthing();
      throw new RuntimeException(e);
    }
  }
  
  public void sendMessage(String messageToSend)
  {
    try
    {
      bufferedWriter.write(userName);
      bufferedWriter.newLine();
      bufferedWriter.write(messageToSend);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e)
    {
      closeEverthing();
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
        String incommingMessage = null;
        
        while (socket.isConnected())
        {
          try
          {
            incommingMessage = bufferedReader.readLine();
            AMessageController.incommingMessage(incommingMessage);
            System.out.println(incommingMessage);
          } catch (IOException e)
          {
            closeEverthing();
            throw new RuntimeException(e);
          }
        }
      }
    }).start();
  }
  
  private void closeEverthing()
  {
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
