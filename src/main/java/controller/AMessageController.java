package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Message;
import models.interfaces.GUIConstantss.ChatConstants;

public class AMessageController
{
  /**
   * NachrichtenListe
   */
  private static ObservableList<Message> messages
    = FXCollections.observableArrayList();
  
  private static ReadOnlyDoubleProperty maxWidth = null;
  
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
    Message message = new Message(messageToSend, ChatConstants.MASSAGE_GOES_OUT,maxWidth);
    Platform.runLater(new Runnable()
    {
      @Override
      public void run()
      {
        messages.add(message);
        ClientController.getAClient().sendMessage(messageToSend);
      }
    });

  }
  
  public static void incommingMessage(String incomingMessage)
  {
    Platform.runLater(new Runnable()
    {
      @Override
      public void run()
      {
        Message message = new Message(incomingMessage, ChatConstants.MASSAGE_COMES_IN,maxWidth);
        messages.add(message);

      }
    });

  }
}
