package models;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable
{
  private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
  private Socket socket = null;
  private BufferedReader bufferedReader = null;
  private BufferedWriter bufferedWriter = null;
  private OutputStreamWriter outputStreamWriter = null;
  private InputStreamReader inputStreamReader = null;
  private String clientUsername;
  
  public ClientHandler(Socket socket)
  {
    try
    {
      this.socket = socket;
      
      this.outputStreamWriter
        = new OutputStreamWriter(socket.getOutputStream());
      
      this.bufferedWriter
        = new BufferedWriter(this.outputStreamWriter);
      
      this.inputStreamReader
        = new InputStreamReader(socket.getInputStream());
      
      this.bufferedReader
        = new BufferedReader(inputStreamReader);
      this.clientUsername = this.bufferedReader.readLine();
      clientHandlers.add(this);
      broadcastMessage(clientUsername + " hat den Chat betreten");
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
    String incommingMessage = "";
     while (this.socket.isConnected())
     {

       try
       {
         incommingMessage = this.bufferedReader.readLine();
         broadcastMessage(incommingMessage);
         
       } catch (IOException e)
       {
         closeEverything();
         throw new RuntimeException(e);
       }
     }
  }
  
  private void broadcastMessage(String messageToSend)
  {
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
    clientHandlers.remove(this);
    broadcastMessage(this.clientUsername + " hat den Chat verlassen");
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
