package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.interfaces.GUIConstants;

import models.Client;
public class ClientDialogCreator
{
    private String name = null;

    private String server = null;

    private int port = 0;

    private TextField nameTextField = new TextField();
    private TextField portTextField = new TextField();
    private TextField serverTextField = new TextField();

    public ClientDialogCreator()
    {

    }
    public Dialog createDialog()
    {
        Dialog<Client> dialog = new Dialog();


        dialog.setTitle(GUIConstants.CLIENT_DIALOG_TITLE);

        this.nameTextField.setPromptText(GUIConstants.NAME_PROMT_TEXT);
        this.serverTextField.setPromptText(GUIConstants.SERVER_PROMT_TEXT);
        this.portTextField.setPromptText(GUIConstants.PORT_PROMT_TEXT);


        Label nameLabel = new Label(GUIConstants.NAME_LABEL_STRING);
        Label portLabel = new Label(GUIConstants.PORT_LABEL_STRING);
        Label serverLabel = new Label(GUIConstants.SERVER_LABEL_STRING);


        GridPane grid = new GridPane();
        grid.add(nameLabel, 1, 1);
        grid.add(nameTextField, 2, 1);
        grid.add(portLabel, 1, 2);
        grid.add(portTextField, 2, 2);
        grid.add(serverLabel, 1, 3);
        grid.add(serverTextField, 2, 3);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeContinue = new ButtonType(GUIConstants.CONTINUE_BUTTON_STRING, ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeContinue);

        ButtonType buttonTypeCancel = new ButtonType(GUIConstants.CANCEL_BUTTON_STRING, ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);


        Button continueButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeContinue);
        continueButton.addEventFilter
        (
            ActionEvent.ACTION,
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    if (validEntries()==false)
                    {
                        event.consume();
                    }
                    else
                    {
                        ClientDialogCreator.this.name = nameTextField.getText();
                        ClientDialogCreator.this.server = serverTextField.getText();
                        ClientDialogCreator.this.port = Integer.valueOf(portTextField.getText());
                        nameTextField.clear();
                        serverTextField.clear();
                        portTextField.clear();
                    }
                }
            }
        );

        portTextField.addEventFilter
        (
            KeyEvent.KEY_TYPED,
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event)
                {
                    if (GUIConstants.NUMBER_DEFINITION.contains(event.getCharacter())==false)
                    {
                        event.consume();
                    }
                }
            }
        );

        dialog.setResultConverter
        (
            new Callback<ButtonType, Client>()
            {
                @Override
                public Client call(ButtonType button)
                {
                    if (button == buttonTypeContinue)
                    {
                        return new Client(ClientDialogCreator.this.name,ClientDialogCreator.this.server,ClientDialogCreator.this.port);
                    }
                    return null;
                }
            }
        );

        return dialog;
    }

    private boolean validEntries ()
    {
        boolean nameIsEmpty = nameTextField.getText().isEmpty();
        boolean portIsEmpty = portTextField.getText().isEmpty();
        boolean serverIsEmpty = serverTextField.getText().isEmpty();

        if (portIsEmpty)
        {
            return false;
        }

        String portString = portTextField.getText();

        try
        {
            int test = Integer.parseInt(portString);
            System.out.println(test);
            if (test > GUIConstants.portMaxValue||test < GUIConstants.portMinValue)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(GUIConstants.PORT_RANGE_ERROR_TITLE);
                alert.setContentText(GUIConstants.PORT_RANGE_ERROR_TEXT);
                alert.showAndWait();

                return false;
            }
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(GUIConstants.PORT_RANGE_ERROR_TITLE);
            alert.setContentText(GUIConstants.PORT_RANGE_ERROR_TEXT);
            alert.showAndWait();
            return false;
        }
        if (nameIsEmpty||serverIsEmpty)
        {
            return false;
        }
        return true;
    }
}
