package views;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import models.interfaces.GUIConstants;

import java.util.Optional;
import models.Client;

public class StartPane
{
    private static VBox startBox = new VBox(GUIConstants.START_V_BOX_SPACING);
    private static Button joinButton = new Button(GUIConstants.JOIN_BUTTON_NAME);
    private static Button hostButton = new Button(GUIConstants.HOST_BUTTON_NAME);
    private static Label welcomeLabel = new Label(GUIConstants.WELCOME_LABEL_STRING);

    StartPane()
    {
        createStartBox();
    }

    public static void createStartBox()
    {
        startBox.setAlignment(Pos.CENTER);
        startBox.getChildren().addAll(welcomeLabel,joinButton,hostButton);

        setButtonEvents();
    }

    private static void setButtonEvents()
    {
        joinButton.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    Dialog<Client> clientDialog = ClientDialog.createDialog();
                    Optional<Client> result = clientDialog.showAndWait();
                    if(result.isPresent())
                    {
                        Client client = result.get();
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Lückenfüller");
                        alert.setHeaderText("Geschafft!");
                        String s ="Jetzt müssen die eben angegebenen Infos noch verarbeitet werden. ";
                        alert.setContentText(s);
                        alert.show();
                    }
                }
            }
        );

        hostButton.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    Dialog<Client> clientDialog = ClientDialog.createDialog();
                    Optional<Client> result = clientDialog.showAndWait();
                    if(result.isPresent())
                    {
                        Client client = result.get();
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Lückenfüller");
                        alert.setHeaderText("Geschafft!");
                        String s ="Jetzt müssen die eben angegebenen Infos noch verarbeitet werden. ";
                        alert.setContentText(s);
                        alert.show();
                    }
                }
            }
        );
    }

    public VBox getPane()
    {
        return startBox;
    }
}

