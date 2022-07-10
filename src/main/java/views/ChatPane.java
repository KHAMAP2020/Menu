package views;

import controller.AMessageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Message;
import models.interfaces.GUIConstantss.ChatConstants;

/**
 * stellt das Chatlayout dar
 *
 * @author A.Hoffmann 5137817 Philipp Gohlke 5157842
 */
public class ChatPane
{
//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Layout des Chats
   */
  private static final VBox vBox = new VBox();

  /**
   * Listenansicht
   */
  private static final ListView<Message> listView
    = new ListView<>();
  
  /**
   * Layout des Nachrichtensendebereichs
   */
  private static final HBox sendBar = new HBox();
  
  /**
   * Textfeld in dem Nachrichten reingeschrieben werden
   */
  public static TextArea textArea = new TextArea();
  
  /**
   * Sendeschaltfläche
   */
  private static final Button sendButton
    = new Button(ChatConstants.SEND_BUTTON_NAME);


//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor des Chatlayouts
   */
  public ChatPane()
  {

    init();

  }
  
  /**
   * Initiale Einstellungen für das Chatlayout
   */
  public static void init()
  {
    textArea.setWrapText(true);
    textArea.setPromptText(ChatConstants.MESSAGE_PROMT_TEXT);
    
    sendBar.getChildren().addAll(textArea, sendButton);
    sendBar.setAlignment(Pos.CENTER_LEFT);
    
    //listView.setItems(messages);
    AMessageController.setMaxWidth(listView.widthProperty());
    listView.setItems(AMessageController.getMessages());
    
    vBox.getChildren().addAll(listView, sendBar);
    
    messageViewCellSettings();
    buttenEventsSettings();

  }
  
  
  /**
   * Stellt Schaltflächenereignisse ein
   */
  private static void buttenEventsSettings()
  {


    sendButton.setOnAction
    (new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent event)
        {
          if (validEntries())
          {
            String massageText = textArea.getText();
          
            /*
            Message message
              = new Message
                (
                  massageText,
                  ChatConstants.MASSAGE_GOES_OUT,
                  listView.widthProperty()
                );
          
            messages.add(message);
          
            listView.scrollTo(message);
            
             */
            AMessageController.sendMessage(massageText);

            textArea.clear();
          }
          else
          {
            event.consume();
          }
        }
      }
    );
  }
  
  /**
   * Stellt Zelle des Listenanzeigers ein
   */
  private static void messageViewCellSettings()
  {
    listView.setCellFactory
    (
      new Callback<ListView<Message>,
      ListCell<Message>>()
      {
        @Override
        public ListCell<Message>
          call(ListView<Message> listView)
        {
          return new MessageListCell();
        }
      }
    );
  }
  
  /**
   * Prüft ob im Textfeld eine abzuschickende Nachricht
   * beinhaltet
   *
   * @return ob der Text im Textfeld Valide ist
   */
  private static boolean validEntries()
  {
    return !textArea.getText().isEmpty();
  }
  
  //-----------------------------------------------------------
  //Getter
  
  /**
   * Gibt das Chatlayout zurück
   *
   * @return das Chatlayout
   */
  public VBox getPane()
  {
    return vBox;
  }
  



  }
