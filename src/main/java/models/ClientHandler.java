package models;

import models.interfaces.GeneralConstants;
import views.types.ErrorAlertType;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/**
@author Philipp Gohlke 5157842
 */
public class ClientHandler implements Runnable
{
  /*
  Die ArrayList clientHandlers beinhaltet jeden Client
  der aktuell erstellt wurde
   */
  private static final ArrayList<ClientHandler> clientHandlers
                                            = new ArrayList<>();
  private Socket socket;
  private final BufferedReader bufferedReader;
  private final BufferedWriter bufferedWriter;
  private final String clientUsername;
  /*
 Der boolean running ist notwendig um der While Schleife zu
 signalisieren, ob sie weiterlaufen oder abbrechen soll.
  */
  private static boolean running = GeneralConstants.LOOP_START;

  /*
  Sollte nur ein
   */
  public static boolean userCount = false;
  
  public ClientHandler(Socket socket)
  {
    /*
    Initialisierung der Streams
     */
    try
    {
      /*
      überprüft ob es sich um den Host handelt
       */
      if(clientHandlers.size() > 1){
        userCount = true;
      }
      ClientHandler.this.socket = socket;

      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(socket.getOutputStream());
      
      bufferedWriter
        = new BufferedWriter(outputStreamWriter);

      InputStreamReader inputStreamReader =
              new InputStreamReader(socket.getInputStream());
      
      bufferedReader
        = new BufferedReader(inputStreamReader);

      /*
      Name des beitretenden Clients wird an alle anderen
      Clients geschickt
       */
      this.clientUsername = bufferedReader.readLine();
      clientHandlers.add(this);
      broadcastMessage(clientUsername + " " +
                       "hat den Chat betreten");
    }
    catch (IOException e)
    {
      //Ausgelöst, wenn die Initialisierung fehlschlägt
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
        Muss in einem Thread ausgeführt werden, da readline()
        eine blockierende Methode ist, also erst in die nächste
        Zeile geht, sobald readline
         ausgeführt wurde. Und das
        würde das komplette Programm blockieren
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
    außer an den, der die Naricht verschickt hat. Dafür
    wird extra in einer For-Schleife jeder Client
    durchgegangen.
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
        /*
        Ausgelöst wenn der Bufferedwriter einen Fehler
        auslöst
         */

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
    /*
    Die removeClientHandler Methode wird ausgelöst und
    alle Objekte werden geschlossen, sofern Sie nicht null sind
    um NullPointerExceptions zu vermeiden
     */
    removeClientHandler();
    try
    {


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
      //Ausgelöst, wenn das schließen fehlschlägt
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }
  }
}
