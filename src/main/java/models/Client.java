package models;

import controller.AMessageController;
import controller.GUIController;
import javafx.application.Platform;
import models.interfaces.GUIConstants.NetworkConstants;
import views.types.CenterPaneType;
import views.types.ErrorAlertType;

import java.io.*;
import java.net.*;

import static controller.GUIController.close;

/**
@author Philipp Gohlke 5157842
 */
public class Client
{
  public static Socket socket;
  private static BufferedReader bufferedReader;
  private static BufferedWriter bufferedWriter;
  private String userName = null;
  /*
  startChat wurde erzeugt um zu verhindern, das mögliche
  Fehler geschehen, die aber erst passieren dürfen, sofern der
  Chat bereits aktiv ist.
   */
  public static boolean startChat;
  /*
  Der boolean running ist notwendig um der While Schleife zu
  signalisieren ob sie weiterlaufen oder abbrechen soll.
   */
  public static boolean running = NetworkConstants.LOOP_START;
  /*
  An dieser Variable werden die Narichten übergeben, welche
  durch ein Readline() eingelesen werden.
   */
  private String receivingMessage;


  public Client(String hostAdress, int port, String userName)
          throws IOException
  {
    try
    {
      /*
       Die Initialisierung des Sockets und der Streams
       running wird auf true gesetzt, damit die Schleifen
       starten können.
       */

      running = NetworkConstants.LOOP_START;
      /*
      Dem Socket werden Hostadresse und port übergeben
      und dementsprechend eine Verbindung aufgebaut,sofern
      möglich.
       */
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
      //Ausgelöst durch fehlerhafte Host Adresse
      ErrorAlertType.HOST_ADRESS_WRONG.
              getAlert().showAndWait();
    }catch(NoRouteToHostException e){
      //Ausgelöst wenn der Server nicht gefunden wird
      ErrorAlertType.SERVER_REACH_FAILED.
              getAlert().showAndWait();
      startChat = false;
    }catch(ConnectException e){
      /*
      Ausgelöst wenn Keine besteht und der Chat
      noch nicht gestartet wurde um zwischen "Verbindung
      verloren" und "Verbindung fehlgeschlagen, unterschieden
      werden kann
       */
      if(!close && !Client.startChat)
      {
        ErrorAlertType.SERVER_REACH_FAILED.
                getAlert().showAndWait();
      }
      /*
      Ausgelöst wenn es nicht der Host ist, da der Host
      mit dem Schließen des Servers automatisch sein Chatfenster
      schließt und damit nicht darüber informiert werden muss,
      das die Verbindung verloren gegangen ist.
      */
        if(ClientHandler.userCount && !close){
          System.out.println("nein");
          ErrorAlertType.CONNECTION_LOST.
                  getAlert().showAndWait();
        }
      closeEverything();
      GUIController.setCenterPane(CenterPaneType.START);
      startChat = false;
      }
    catch(IOException e){
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
      //Ausgelöst durch einen Verbindungsverlust
      ErrorAlertType.CONNECTION_LOST.getAlert().showAndWait();

    }catch (IOException e)
    {
      /*
      Ausgelöst wenn der Bufferedwriter keine Verbindung hat
      oder Methoden nicht einwandfrei laufen
      */

      ErrorAlertType.SEND_MESSAGE_FAILED.
              getAlert().showAndWait();


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
            wartet auf eingehende Narichten
             */
              receivingMessage = bufferedReader.readLine();

            if(receivingMessage != null)
            {
              AMessageController.incomingMessage
                (receivingMessage);

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

                if(running){
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

    } catch (IOException e)
    {
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }
  }
}
