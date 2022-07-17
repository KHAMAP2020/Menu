package models;

import controller.AMessageController;
import controller.GUIController;
import javafx.application.Platform;
import models.interfaces.GUIConstants.NetworkConstants;
import views.CenterPaneType;
import views.ErrorAlertType;
import java.io.*;
import java.net.*;
import java.util.TimerTask;

import static controller.GUIController.close;

/**
@author Philipp Gohlke 5157842
 */
public class Client
{
  public static Socket socket;
  private static BufferedReader bufferedReader;
  private static BufferedWriter bufferedWriter;
  private static boolean closing = NetworkConstants.LOOP_STOP;
  private String userName = null;

  private static boolean alreadyCaught = false;
  public static boolean startChat;

  public static boolean running = NetworkConstants.LOOP_START;
  private String receivingMessage;


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
    }catch(UnknownHostException e){
      ErrorAlertType.HOST_ADRESS_WRONG.
              getAlert().showAndWait();
    }catch(NoRouteToHostException e){
      ErrorAlertType.SERVER_REACH_FAILED.
              getAlert().showAndWait();
      startChat = false;
    }catch(ConnectException e){
      System.out.println("client 69");
      if(!close && !Client.startChat)
      {
        ErrorAlertType.SERVER_REACH_FAILED.
                getAlert().showAndWait();
      }
        if(ClientHandler.userCount && !close){
          System.out.println("nein");
          ErrorAlertType.CONNECTION_LOST.
                  getAlert().showAndWait();
        }
      closeEverything();
      GUIController.setCenterPane(CenterPaneType.START);
      startChat = false;
      } catch(BindException e){
      ErrorAlertType.PORT_ALREADY_IN_USE.
              getAlert().showAndWait();
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

    } catch(SocketException e){
      if(!alreadyCaught){
        ErrorAlertType.CONNECTION_LOST.getAlert().showAndWait();
      }
    }catch (IOException e)
    {

      ErrorAlertType.SEND_MESSAGE_FAILED.
              getAlert().showAndWait();
      e.printStackTrace();
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
        while (running)
        {
          try
          {
            /*
            wartet auf eingehende Narichten, solange der
            Socket eine Verbindung zum Server hat
             */
            if(bufferedReader != null){
              receivingMessage = bufferedReader.readLine();
              System.out.println("client 155");
            }

            if(receivingMessage != null)
            {
              AMessageController.incomingMessage
                (receivingMessage);
              System.out.println("client 162");
            }
          }
          catch (IOException e)
          {
            Platform.runLater(new Runnable()
            {
              @Override
              public void run()
              {
                closeEverything();
                System.out.println("naricht 143");
                if(running && !closing){
                  ErrorAlertType.REICIVE_MESSAGE_FAILED.
                          getAlert().showAndWait();
                }




              }
            });

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

      closing = true;
    } catch (IOException e)
    {
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }

  }

}
