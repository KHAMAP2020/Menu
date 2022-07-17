package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Message;
import models.interfaces.GUIConstants.MessageConstants;

/**
@author Philipp Gohlke 5157842
 */
public class MessageController
{
  /*
   * In dieser ObservableList werden die Narichten gespeichert
   * welche im Chat zu sehen sind. Dadurch das es eine
   * ObservableList ist, können Listener diese List
   * beobachten bzw. einlesen
   */
  private static ObservableList<Message> messages
    = FXCollections.observableArrayList();

  //Definiert die Breite der ListView
  private static ReadOnlyDoubleProperty maxWidth = null;
  private static boolean running = true;
  public static void setMaxWidth(ReadOnlyDoubleProperty width)
  {//setter-Methode
    maxWidth = width;
  }
  public static ObservableList<Message> getMessages()
  {//getter-Methode
    return messages;
  }

  public static void sendMessage(String messageToSend)
  {
    /*
    hier werden Narichten im ChatFenster bzw. in der List
        einsortiert und ankommende Narichten werden rechts
        angezeigt.
     */
    Message message = new Message(messageToSend,
            MessageConstants.MASSAGE_GOES_OUT,maxWidth);

    messages.add(message);
          ClientController.getClient().sendMessage
                  (messageToSend);
  }
  
  public static void incomingMessage(String incomingMessage)
  {
    Platform.runLater(new Runnable()
    {
      @Override
      public void run()
      {
        /*
        hier werden Narichten im ChatFenster bzw. in der List
        einsortiert und ankommende Narichten werden links
        angezeigt.
         */
        Message message = new Message(incomingMessage,
                MessageConstants.MASSAGE_COMES_IN,maxWidth);
        messages.add(message);
        /*
        Hier werden eingehende Narichten an den Chat übergeben
        und auf der linken Seite angezeigt
         */
      }
    });

  }
  public static void stopMessageController(){
    /*
    wird aufgerufen um den MessageController zu stoppen, indem
    die While Schleife beendet wird
     */
    running = false;
  }
  public static void resetMessages()
  {
    messages.clear();
  }
}
