package models;

import javafx.application.Platform;
import models.interfaces.GeneralConstants;
import views.types.ErrorAlertType;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import static controller.GUIController.close;

/**
@author Philipp Gohlke 5157842
 */

public class Server extends Thread
{
  public static ServerSocket serverSocket;
  static Socket socket;
  /*
  Wird verwendet um der While-Schleife zu signialisieren
  ob sie laufen soll oder nicht und eventuell abeschaltet
  werden kann
   */
  private static boolean running;
  /*
  Wird dafür genutzt mit einem boolean zu überprüfen
  ob der Server läuft
   */
  public static boolean startServer;
  private static ClientHandler clientHandler;
  public Server(int port)
  {
    try
    {
      running  = GeneralConstants.LOOP_START;
      serverSocket = new ServerSocket(port);

      startServer = true;
    } catch (BindException e){
      //ausgelöst wenn der Port bereits benutzt wird
      ErrorAlertType.PORT_ALREADY_IN_USE.
              getAlert().showAndWait();
      startServer = false;
    }
    catch (IOException e)
    {
    }
  }
  
  @Override
  public void run()
  {
    try
    {
      while (running)
      {
        if(serverSocket == null){
          Client.running = false;
        }
        /*
        Solange der Serversocket nicht geschlossen ist,
        wird jeder eingehende Verbindungsversuch angenommen.
        Daraufhin wird ein neuer Client erzeugt und in der
        Client ArrayList hinzugefügt.
        Muss in einem Thread ausgeführt werden, da accept()
        eine blockierende Methode ist, also erst in die nächste
        Zeile geht, sobald accept ausgeführt wurde. Und das
        würde das komplette Programm blockieren
         */


        socket = serverSocket.accept();

        System.out.println
                        ("ein neuer Client hat sich verbunden");

        clientHandler = new ClientHandler(socket);

        Thread thread = new Thread(clientHandler);
        thread.start();

      }
    } catch (ConnectException e)
    {
      Platform.runLater(new Runnable()
      {
        @Override
        public void run()
        {
          //Ausgelöst durch fehlgeschlagenen Verbindungsversuch
          if(!close){
            ErrorAlertType.SERVER_CONNECT_FAILED.
                    getAlert().showAndWait();
          }
        }
      });

      closeEverything();
    }catch(NullPointerException e){
      /*
      Wird zum Beispiel ausgelöst, wenn ein 2. Server auf den
      gleichen port versucht wird zu erstellen.
       */
    }catch(IOException e){

    }
  }
  
  public static void closeEverything()
  {
    /*
   Setzt den boolean running auf false, damit die While
   Schleifen stoppen und schließt alle Objekte, sofern sie nicht
   null sind
     */
    try
    {
      running = GeneralConstants.LOOP_STOP;
      if(Server.socket != null){
        socket.close();
      }
      if(serverSocket != null)
      {
        serverSocket.close();
      }
      if (clientHandler != null)
      {
        clientHandler.closeEverything();
      }
    }
    catch (IOException e)
    {
      //Ausgelöst wenn das Schließen fehlschlägt
      ErrorAlertType.CLOSING_FAILED.getAlert().showAndWait();
    }
  }

}


