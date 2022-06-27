package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.Client;
import models.interfaces.GUIConstants;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;

public class ServerDialog
{
    private static String name = null;

    private static String server = null;

    private static int port = 0;

    private static TextField nameTextField = new TextField();
    private static TextField portTextField = new TextField();
    private static TextField serverTextField = new TextField();

    public ServerDialog()
    {

    }
    public static Dialog createDialog()
    {
        Dialog<Client> dialog = new Dialog();

        dialog.setTitle(GUIConstants.CLIENT_DIALOG_TITLE);

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
                                    ServerDialog.name = nameTextField.getText();
                                    ServerDialog.server = serverTextField.getText();
                                    ServerDialog.port = Integer.valueOf(portTextField.getText());
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
                                if ("0123456789".contains(event.getCharacter())==false)
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
                                    return new Client(ServerDialog.name, ServerDialog.server, ServerDialog.port);
                                }
                                return null;
                            }
                        }
                );

        return dialog;
    }

    private static boolean validEntries()
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(GUIConstants.PORT_RANGE_ERROR_TITLE);
                alert.setContentText(GUIConstants.PORT_RANGE_ERROR_TEXT);
                alert.showAndWait();

                return false;
            }
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
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
