package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.interfaces.GUIConstants;

import java.util.Optional;
import models.Client;

public class StartPaneCreator
{
    private Button joinButton = new Button(GUIConstants.JOIN_BUTTON_NAME);
    private Button hostButton = new Button(GUIConstants.HOST_BUTTON_NAME);
    private Label welcomeLabel = new Label(GUIConstants.WELCOME_LABEL_STRING);
    private VBox startPane = new VBox(GUIConstants.V_BOX_SPACING);

    ClientDialogCreator clientDialogCreator = new ClientDialogCreator();

    public StartPaneCreator()
    {

    }
    public VBox createStartPane()
    {
        joinButton.setOnAction
        (
            new EventHandler<ActionEvent>()
           {
               @Override
               public void handle(ActionEvent actionEvent)
               {
                   Dialog<Client> clientDialog = clientDialogCreator.createDialog();
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
        startPane.setAlignment(Pos.CENTER);
        startPane.getChildren().addAll(welcomeLabel,joinButton,hostButton);

        return this.startPane;
    }
}
