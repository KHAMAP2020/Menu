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
    private static VBox chatBox = new VBox();

    private static ObservableList<Message> messages = FXCollections.observableArrayList();

    private static ListView<Message> massageView = new ListView<Message>();

    private static HBox sendBar = new HBox();

    public static TextArea massageTextfield = new TextArea();

    private static Button sendButton = new Button(GUIConstants.SEND_BUTTON_NAME);


    public ChatPane()
    {
        createChatBox();
    }
    public static void createChatBox()
    {
        massageTextfield.setWrapText(true);
        massageTextfield.setPromptText(GUIConstants.MASSAGE_PROMT_TEXT);
        massageView.setItems(messages);

        setMassageViewCells();
        setButtenEvents();

        sendBar.getChildren().addAll(massageTextfield,sendButton);
        sendBar.setAlignment(Pos.CENTER_LEFT);
        chatBox.getChildren().addAll(massageView,sendBar);
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
                        String massageText = massageTextfield.getText();

                        Message message = new Message(massageText,GUIConstants.MASSAGE_GOES_OUT);

                        messages.add(message);

                        massageView.scrollTo(message);
                        massageTextfield.clear();
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
        massageView.setCellFactory
        (
            new Callback<ListView<Message>, ListCell<Message>>()
            {
                @Override
                public ListCell<Message> call(ListView<Message> listView)
                {
                    return new MassageListCell();
                }
            }
        );
    }

    private static boolean validEntries ()
    {
        if(massageTextfield.getText().isEmpty())
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
        return chatBox;
    }
}
