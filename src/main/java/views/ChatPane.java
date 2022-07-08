package views;

import Server.Server;
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

import models.LoginData;
import models.Message;
import models.interfaces.GUIConstantss.ChatConstants;

import java.io.*;
import java.net.Socket;

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
    = new Button(ChatConstants.SEND_BUTTON_NAME);
    private BufferedReader input ;
    private static BufferedWriter output;






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
            ChatConstants.MESSAGE_PROMT_TEXT
        );

        sendBar.getChildren().addAll(textArea,sendButton);
        sendBar.setAlignment(Pos.CENTER_LEFT);

        listView.setItems(messages);

        vBox.getChildren().addAll(listView,sendBar);

        messageViewCellSettings();
        buttenEventsSettings();
    }


    /**
     * Stellt Schaltflächenereignisse ein
     */
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
                        String messageText
                        = textArea.getText();

                        Message message
                        = new Message
                        (
                            messageText,
                            ChatConstants.MASSAGE_GOES_OUT,
                            listView.widthProperty()
                        );

                        messages.add(message);

                        listView.scrollTo(message);
                        textArea.clear();
                        try {

                            sendMessage();
                            System.out.println("Naricht wird gesendet");
                            System.out.println("ChatPane: " + messageText);
                            output.write(messageText);
                            output.newLine();
                            output.flush();
                            System.out.println("Naricht wurde gesendet");

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
                public ListCell<Message> call
                (
                    ListView<Message> listView
                )
                {
                    return new MessageListCell();
                }
            }
        );
    }

    /**
     * Prüft ob im Textfeld eine abzuschickende Nachricht
     * beinhaltet
     * @return ob der Text im Textfeld Valide ist
     */
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

    //-----------------------------------------------------
    //Getter

    /**
     * Gibt das Chatlayout zurück
     * @return das Chatlayout
     */
    public VBox getPane()
    {
        return vBox;
    }


    public static void sendMessage() throws IOException {
        try {
            output = new BufferedWriter(new OutputStreamWriter(StartPane.socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void readMessage() throws IOException {
        input = new BufferedReader((new InputStreamReader(StartPane.socket.getInputStream())));
    }
}
