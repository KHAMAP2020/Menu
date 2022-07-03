package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import models.interfaces.GUIConstants;
import models.interfaces.GUIConstantss.ChatPaneConstants;

/**
 * stellt das Chatlayout dar
 *
 * @author A.Hoffmann 5137817
 */
public class ChatPane
{
//---------------------------------------------------------
//Datenfeld

    /**
     * Layout des Chats
     */
    private static VBox vBox = new VBox();

    /**
     * NachrichtenListe
     */
    private static ObservableList<Message> messages
    = FXCollections.observableArrayList();

    /**
     * Listenansicht
     */
    private static ListView<Message> listView
    = new ListView<Message>();

    /**
     * Layout des Nachrichtensendebereichs
     */
    private static HBox sendBar = new HBox();

    /**
     * Textfeld in dem Nachrichten reingeschrieben werden
     */
    public static TextArea textArea = new TextArea();

    /**
     * Sendeschaltfläche
     */
    private static Button sendButton
    = new Button(ChatPaneConstants.SEND_BUTTON_NAME);

//---------------------------------------------------------
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
        textArea.setPromptText
        (
            ChatPaneConstants.MESSAGE_PROMT_TEXT
        );

        sendBar.getChildren().addAll(textArea,sendButton);
        sendBar.setAlignment(Pos.CENTER_LEFT);

        listView.setItems(messages);

        vBox.getChildren().addAll(listView,sendBar);

        messageViewCellSettings();
        buttenEventsSettings();
    }

    private static void buttenEventsSettings()
    {
        sendButton.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    if (validEntries()==true)
                    {
                        String massageText = textArea.getText();

                        Message message = new Message(massageText,GUIConstants.MASSAGE_GOES_OUT,listView.widthProperty());

                        messages.add(message);

                        listView.scrollTo(message);
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

    private static void messageViewCellSettings()
    {
        listView.setCellFactory
        (
            new Callback<ListView<Message>, ListCell<Message>>()
            {
                @Override
                public ListCell<Message> call(ListView<Message> listView)
                {
                    return new MessageListCell();
                }
            }
        );
    }

    private static boolean validEntries ()
    {
        if(textArea.getText().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public VBox getPane()
    {
        return vBox;
    }
}
