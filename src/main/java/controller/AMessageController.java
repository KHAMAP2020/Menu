package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Message;
import models.interfaces.GUIConstants.ChatConstants;

/**
@author Philipp Gohlke 5157842
 */
public class AMessageController
{
  /**
   * NachrichtenListe
   */
  private static ObservableList<Message> messages
    = FXCollections.observableArrayList();
  
  private static ReadOnlyDoubleProperty maxWidth = null;
  private static boolean running = true;
  public static void setMaxWidth(ReadOnlyDoubleProperty width)
  {
    maxWidth = width;
  }
  public static ObservableList<Message> getMessages()
  {
    return messages;
  }

  public static void sendMessage(String messageToSend)
  {
    /*
    Hier werden ausgehende Narichten an den Chat übergeben
    und auf der rechten Seite hinzugefügt
     */
    Message message = new Message(messageToSend,
            ChatConstants.MASSAGE_GOES_OUT,maxWidth);
    /*
    Platform.runLater(new Runnable()
    {
      
      @Override
      public void run()
      {
        //In einer While schleife würde er unendlich lange versuchen was zu schocken
        while(running)
        {
          messages.add(message);
          ClientController.getAClient().sendMessage(messageToSend);
        }
      }
    });
    
       */
    messages.add(message);
          ClientController.getClient().sendMessage(messageToSend);
  }
  
  public static void incomingMessage(String incomingMessage)
  {
    Platform.runLater(new Runnable()
    {
      @Override
      public void run()
      {
        Message message = new Message(incomingMessage,
                ChatConstants.MASSAGE_COMES_IN,maxWidth);
        messages.add(message);
        /*
        Hier werden eingehende Narichten an den Chat übergeben
        und auf der linken Seite angezeigt
         */
      }
    });

  }
  public static void stopMessageController(){
    running = false;
  }
  public static void resetMessages()
  {
    messages.clear();
  }
}
