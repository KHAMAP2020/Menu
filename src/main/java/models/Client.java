package models;

import controller.AMessageController;
import controller.GUIController;
import models.interfaces.GUIConstants.NetworkConstants;
import views.CenterPaneType;
import views.ErrorAlertType;

import java.io.*;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketException;

/**
@author Philipp Gohlke 5157842
 */
public class Client
{
  public static Socket socket;
  private static BufferedReader bufferedReader;
  private static BufferedWriter bufferedWriter;
  private String userName = null;
  public static boolean startChat;

  private static boolean running = true;

  
  public Client(String hostAdress, int port, String userName)
          throws IOException
  {
    try
    {
      // Die Initialisierung des Sockets und der Streams

      running = NetworkConstants.LOOP_START;
      socket = new Socket(hostAdress,port);
      startChat = true;
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(socket.getOutputStream());
      bufferedWriter
        = new BufferedWriter(outputStreamWriter);

      InputStreamReader inputStreamReader =
              new InputStreamReader(socket.getInputStream());
      bufferedReader
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
    }catch(NoRouteToHostException e){
      ErrorAlertType.SERVER_REACH_FAILED.
              getAlert().showAndWait();
      startChat = false;
    }catch(ConnectException e){
      ErrorAlertType.SERVER_CONNECT_FAILED.
              getAlert().showAndWait();
      closeEverything();
      GUIController.setCenterPane(CenterPaneType.START);

      startChat = false;
    }catch(IOException e){
      startChat = false;
    }
  }
  
  public void sendMessage(String messageToSend)
  {
    try
    {
      if(running){
        System.out.println("senden");
         /*
      Führt das senden von Narichten, vom Client aus, aus
       */
        bufferedWriter.write
                (userName + ": " + messageToSend);
        //Naricht wird an den BufferedWriter übergeben
        bufferedWriter.newLine();
        /*
        Dem BufferedWriter wird signalisiert das kein weiterer
        Input mehr kommt.
         */
        bufferedWriter.flush();
        /*
        Wird bnötigt die Naricht abzuschicken. Sonst würde er
        warten bis der Writer voll ist.
         */
      }

    } catch (IOException e)
    {
      System.out.println("Fehler beim schicken");
      ErrorAlertType.SEND_MESSAGE_FAILED.
              getAlert().showAndWait();
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
        while (running)
        {
          try
          {
            /*
            wartet auf eingehende Narichten, solange der
            Socket eine Verbindung zum Server hat
             */
            receivingMessage = bufferedReader.readLine();
            if(receivingMessage!= null)
            {
              AMessageController.incomingMessage
                (receivingMessage);
            }
          }
          catch (IOException e)
          {
            closeEverything();
            //Wirft Fehler
            //ErrorAlertType.REICIVE_MESSAGE_FAILED.
            //        getAlert().show();
          }
        }
      }
    }).start();
  }
  
  public static void closeEverything()
  {
    /*
    für den Fall das ein Catch ausgelöst wurde, soll diese
    Methode für ein sicheres schließen der
    Sockets etc. sorgen.
     */
    try
    {
      /*
      zum Deaktivieren der while-Schleife in den Threads.
      Dadurch werden die Threads terminiert, sobald die
      run()-Methode durchgelaufen ist.
       */
      running = NetworkConstants.LOOP_STOP;

      /*
      Solange, das jeweilige Objekt nicht null ist, wird es
      geschlossen.
       */
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
    } catch (IOException e)
    {
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }
  }
}
