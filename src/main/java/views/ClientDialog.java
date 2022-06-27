package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.Client;
import models.interfaces.GUIConstants;

public class ClientDialog
{
    private static Dialog<Client> dialog =null;// new Dialog();
    private static GridPane grid = null;//new GridPane();

    private static String name = null;

    private static String server = null;

    private static int port = 0;

    private static TextField nameTextField = new TextField();
    private static TextField portTextField = new TextField();
    private static TextField serverTextField = new TextField();

    private static Label nameLabel = new Label(GUIConstants.NAME_LABEL_STRING);
    private static Label portLabel = new Label(GUIConstants.PORT_LABEL_STRING);
    private static Label serverLabel = new Label(GUIConstants.SERVER_LABEL_STRING);

    private static ButtonType buttonTypeContinue = new ButtonType(GUIConstants.CONTINUE_BUTTON_STRING, ButtonBar.ButtonData.OK_DONE);
    private static ButtonType buttonTypeCancel = new ButtonType(GUIConstants.CANCEL_BUTTON_STRING, ButtonBar.ButtonData.CANCEL_CLOSE);

    public ClientDialog()
    {

    }
    public static Dialog<Client> createDialog()
    {
        dialog = new Dialog();
        grid = new GridPane();
        dialog.setTitle(GUIConstants.CLIENT_DIALOG_TITLE);

        nameTextField.setPromptText(GUIConstants.NAME_PROMT_TEXT);
        serverTextField.setPromptText(GUIConstants.SERVER_PROMT_TEXT);
        portTextField.setPromptText(GUIConstants.PORT_PROMT_TEXT);

        grid.add(nameLabel,         1, 1);
        grid.add(nameTextField,     2, 1);
        grid.add(portLabel,         1, 2);
        grid.add(portTextField,     2, 2);
        grid.add(serverLabel,       1, 3);
        grid.add(serverTextField,   2, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeContinue,buttonTypeCancel);

        setButtonEvents();
        setTextFieldEvents();
        setDialogResults();

        return dialog;
    }

    private static void setButtonEvents ()
    {
        Button continueButton = (Button)dialog.getDialogPane().lookupButton(buttonTypeContinue);
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
                        name = nameTextField.getText();
                        server = serverTextField.getText();
                        port = Integer.valueOf(portTextField.getText());
                        clearAllFields();
                    }
                }
            }
        );


        Button cancelButton = (Button)dialog.getDialogPane().lookupButton(buttonTypeCancel);
        cancelButton.addEventFilter
        (
            ActionEvent.ACTION,
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    clearAllFields();
                }
            }
        );
    }

    private static void setTextFieldEvents ()
    {
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

    }

    private static void setDialogResults()
    {
        dialog.setResultConverter
        (
            new Callback<ButtonType, Client>()
            {
                @Override
                public Client call(ButtonType button)
                {
                    if (button == buttonTypeContinue)
                    {
                        return new Client(name,server,port);
                    }
                    return null;
                }
            }
        );
    }

    private static boolean validEntries ()
    {
        boolean nameIsEmpty = nameTextField.getText().isEmpty();
        boolean portIsEmpty = portTextField.getText().isEmpty();
        boolean serverIsEmpty = serverTextField.getText().isEmpty();

        if (portIsEmpty==false)
        {
            String portString = portTextField.getText();

            try
            {
                int portNumber = Integer.parseInt(portString);
                if (portNumber > GUIConstants.portMaxValue||portNumber < GUIConstants.portMinValue)
                {
                    ErrorAlertType.PORT_RANGE.getAlert().showAndWait();
                    return false;
                }
            }
            catch (NumberFormatException exception)
            {
                ErrorAlertType.PORT_RANGE.getAlert().showAndWait();
                return false;
            }
        }
        else
        {
            ErrorAlertType.EMPTY_TEXTFIELD.getAlert().showAndWait();
            return false;
        }
        if (nameIsEmpty||serverIsEmpty)
        {
            ErrorAlertType.EMPTY_TEXTFIELD.getAlert().showAndWait();
            return false;
        }
        return true;
    }

    private static void clearAllFields()
    {
        nameTextField.clear();
        serverTextField.clear();
        portTextField.clear();
    }
}