package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Massage;
import models.interfaces.GUIConstants;
import javafx.util.Callback;

public class ChatPaneCreator
{

    private VBox chatPane = new VBox();

    private ObservableList<Massage> massages = FXCollections.observableArrayList();

    private ListView <Massage> massageView = new ListView<Massage>();


    private HBox sendBar = new HBox();

    private TextArea massageTextfield = new TextArea();

    private Button sendButton = new Button(GUIConstants.SEND_BUTTON_NAME);

    private Boolean isReceivedMassage = null;

    public VBox createChatPane()
    {
        this.massageTextfield.setWrapText(true);
        this.massageTextfield.setPromptText(GUIConstants.MASSAGE_PROMT_TEXT);
        this.massageView.setItems(massages);
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
        this.sendButton.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    if (validEntries()==true)
                    {


                        String massageText = ChatPaneCreator.this.massageTextfield.getText();
                        /*
                        Label massageLabel = new Label(massageText);
                        massageLabel.setWrapText(true);
                        HBox massage = new HBox();
                        */
                        Massage massage = new Massage(massageText,GUIConstants.MASSAGE_GOES_OUT);
                        //massage.getChildren().add(massageLabel);
                        //massage.setAlignment(Pos.CENTER_RIGHT);
                        ChatPaneCreator.this.massages.add(massage);

                        ChatPaneCreator.this.massageView.scrollTo(massage);
                        massageTextfield.clear();

                    }
                }
            }
        );

        this.sendBar.getChildren().addAll(massageTextfield,sendButton);
        this.sendBar.setAlignment(Pos.CENTER_LEFT);
        this.chatPane.getChildren().addAll(massageView,sendBar);
        return this.chatPane;
    }
    private boolean validEntries ()
    {
        if(this.massageTextfield.getText().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
