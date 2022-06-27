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
import models.Massage;
import models.interfaces.GUIConstants;

public class ChatPane
{
    private static VBox chatBox = new VBox();

    private static ObservableList<Massage> massages = FXCollections.observableArrayList();

    private static ListView<Massage> massageView = new ListView<Massage>();

    private static HBox sendBar = new HBox();

    private static TextArea massageTextfield = new TextArea();

    private static Button sendButton = new Button(GUIConstants.SEND_BUTTON_NAME);


    public ChatPane()
    {
        createChatBox();
    }
    public static void createChatBox()
    {
        massageTextfield.setWrapText(true);
        massageTextfield.setPromptText(GUIConstants.MASSAGE_PROMT_TEXT);
        massageView.setItems(massages);

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

                        Massage massage = new Massage(massageText,GUIConstants.MASSAGE_GOES_OUT);

                        massages.add(massage);

                        massageView.scrollTo(massage);
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
            new Callback<ListView<Massage>, ListCell<Massage>>()
            {
                @Override
                public ListCell<Massage> call(ListView<Massage> listView)
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
