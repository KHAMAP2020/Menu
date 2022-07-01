package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Message;
import models.interfaces.GUIConstants;

public class ChatPane
{
    private static VBox vBox = new VBox();

    private static ObservableList<Message> messages = FXCollections.observableArrayList();

    private static ListView<Message> listView = new ListView<Message>();

    private static HBox sendBar = new HBox();

    public static TextArea textArea = new TextArea();

    private static Button sendButton = new Button(GUIConstants.SEND_BUTTON_NAME);


    public ChatPane()
    {
        createChatBox();
    }
    public static void createChatBox()
    {
        textArea.setWrapText(true);
        textArea.setPromptText(GUIConstants.MESSAGE_PROMT_TEXT);
        listView.setItems(messages);
        setMassageViewCells();
        setButtenEvents();

        sendBar.getChildren().addAll(textArea,sendButton);
        sendBar.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(listView,sendBar);
    }

    private static void setButtenEvents()
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

                        Message message = new Message(massageText,GUIConstants.MASSAGE_GOES_OUT);

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

    private static void setMassageViewCells()
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
