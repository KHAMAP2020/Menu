package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.LoginData;
import models.interfaces.GUIConstants;
import models.interfaces.GUIConstantss.RegisterConstants;

/**
 * Dialog in dem der Anwender Daten zum aufbau eines Servers
 * eingibt oder sich mit einem verbindet.
 *
 * Dafür sind Name des Anwenders, Portnummer und der
 * Severname notwendig
 *
 * @author A.Hoffmann 5137817
 */
public class RegisterDialog
{
//---------------------------------------------------------
//Datenfelder

    /**
     * Dialog das Anmeldedaten zurückgibt
     */
    private static Dialog<LoginData> dialog = null;

    /**
     * Layouts des Dialogs
     */
    private static GridPane grid = null;

    //-----------------------------------------------------
    //Textfelder für Anwenderangaben

    /**
     * Textfeld in dem der Name des Anwenders angegeben
     * werden soll
     */
    private static TextField nameTextField
    = new TextField();

    /**
     * Textfeld in dem die Portnummer angegeben werden soll
     */
    private static TextField portTextField
    = new TextField();

    /**
     * Textfeld in dem der Servername angegeben wird
     */
    private static TextField serverTextField
    = new TextField();

    //-----------------------------------------------------
    //Beschriftungen

    /**
     * Schriftzug neben dem Namenstextfeld
     */
    private static Label nameLabel
    = new Label(RegisterConstants.NAME_LABEL_STRING);

    /**
     * Schriftzug neben dem Portnummertextfeld
     */
    private static Label portLabel
    = new Label(RegisterConstants.PORT_LABEL_STRING);

    /**
     * Schriftzug neben dem
     */
    private static Label serverLabel
    = new Label(RegisterConstants.SERVER_LABEL_STRING);

    //-----------------------------------------------------
    //Schaltflächen

    /**
     * Schaltfläche zur bestätigung der Anwenderangaben
     */
    private static ButtonType buttonTypeContinue
    = new ButtonType
    (
        RegisterConstants.CONTINUE_BUTTON_STRING,
        ButtonBar.ButtonData.OK_DONE
    );

    /**
     * Schaltfläche zum Abbrechen des Dialogs
     */
    private static ButtonType buttonTypeCancel
    = new ButtonType
    (
        RegisterConstants.CANCEL_BUTTON_STRING,
        ButtonBar.ButtonData.CANCEL_CLOSE
    );

    //-----------------------------------------------------
    //Für LoginData

    /**
     * Name des Anwenders
     */
    private static String name = null;

    /**
     * Servername
     */
    private static String server = null;

    /**
     * Portnummer
     */
    private static int port = 0;

//---------------------------------------------------------
//Methoden

    /**
     * Erstellt Dialog der Login-Informationen zurückgibt
     * @return die Login-Informationen
     */
    public static Dialog<LoginData> createDialog()
    {
        dialog = new Dialog();
        grid = new GridPane();
        dialog.setTitle
        (
            RegisterConstants.CLIENT_DIALOG_TITLE
        );

        nameTextField.setPromptText
        (
            RegisterConstants.NAME_PROMT_TEXT
        );
        serverTextField.setPromptText
        (
            RegisterConstants.SERVER_PROMT_TEXT
        );
        portTextField.setPromptText
        (
            RegisterConstants.PORT_PROMT_TEXT
        );

        grid.add(nameLabel,         1, 1);
        grid.add(nameTextField,     2, 1);
        grid.add(portLabel,         1, 2);
        grid.add(portTextField,     2, 2);
        grid.add(serverLabel,       1, 3);
        grid.add(serverTextField,   2, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll
        (
            buttonTypeContinue,
            buttonTypeCancel
        );
        eventSettings();
        dialogResultSettings();

        return dialog;
    }

    /**
     * Stellt alle Ereignisse ein
     */
    private static void eventSettings()
    {
        buttonEventSettings();
        textFieldEventSettings();
    }
    /**
     * Stellt alle Schaltflächen-Ereignisse ein
     */
    private static void buttonEventSettings()
    {
        continueButtenEventSettings();
        cancelButtenEventSettings();
    }

    /**
     * Stellt das Schaltflächen-Ereignis für die
     * Schaltfläche zum Fortsetzen ein
     */
    private static void continueButtenEventSettings()
    {
        Button continueButton
        = (Button)dialog.getDialogPane().lookupButton
        (
            buttonTypeContinue
        );

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
                        port
                        = Integer.valueOf
                        (
                            portTextField.getText()
                        );
                        clearAllFields();
                    }
                }
            }
        );
    }

    /**
     * Stellt das Schaltflächen-Ereignis für die
     * Schaltfläche zum Abbrechen ein
     */
    private static void cancelButtenEventSettings()
    {
        Button cancelButton
        = (Button)dialog.getDialogPane().lookupButton
        (
            buttonTypeCancel
        );

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

    /**
     * stellt alle Textflächen-Ereignisse ein
     */
    private static void textFieldEventSettings()
    {
        portTextfieldEventSettings();
    }

    /**
     * Stellt Ereignisse für das Portnummer-Textfeld ein
     */
    private static void portTextfieldEventSettings()
    {
        portTextField.addEventFilter
        (
            KeyEvent.KEY_TYPED,
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event)
                {
                    if
                    (
                        RegisterConstants.NUMBER_DEFINITION
                        .contains
                        (
                            event.getCharacter()
                        )
                        == false
                    )
                    {
                        event.consume();
                    }
                }
            }
        );
    }

    /**
     * Stellt das Ergebnis bei einem Erfolgreichen durchlauf
     * des Dialogs ein
     */
    private static void dialogResultSettings()
    {
        dialog.setResultConverter
        (
            new Callback<ButtonType, LoginData>()
            {
                @Override
                public LoginData call(ButtonType button)
                {
                    if (button == buttonTypeContinue)
                    {
                        return new LoginData
                        (
                            name,
                            server,
                            port
                        );
                    }
                    return null;
                }
            }
        );
    }

    /**
     * Prüft, ob die Einträge in den Textfeldern den
     * Absendebedingungen entsprechen
     * @return Ob Einträge absendbar sind
     */
    private static boolean validEntries ()
    {
        /**
         * gibt an, ob ein Name im Namenstextfeld
         * eingetragen wurde
         */
        boolean nameIsEmpty
        = nameTextField.getText().isEmpty();

        /**
         * gibt an, ob eine Portnummer im
         * Portnummerntextfeld eingetragen wurde
         */
        boolean portIsEmpty
        = portTextField.getText().isEmpty();

        /**
         * gibt an, ob ein servername im
         * serverNamenstextfeld eingetragen wurde
         */
        boolean serverIsEmpty
        = serverTextField.getText().isEmpty();

        if (portIsEmpty==false)
        {
            String portString = portTextField.getText();

            try
            {
                int portNumber = Integer.parseInt(portString);
                if
                (
                    portNumber
                    > RegisterConstants.portMaxValue
                    ||
                    portNumber
                    < RegisterConstants.portMinValue
                )
                {
                    ErrorAlertType.PORT_RANGE.getAlert()
                    .showAndWait();

                    return false;
                }
            }
            catch (NumberFormatException exception)
            {
                ErrorAlertType.PORT_RANGE.getAlert()
                .showAndWait();
                return false;
            }
        }
        else
        {
            ErrorAlertType.EMPTY_TEXTFIELD.getAlert()
            .showAndWait();
            return false;
        }
        if (nameIsEmpty||serverIsEmpty)
        {
            ErrorAlertType.EMPTY_TEXTFIELD.getAlert()
            .showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Leert alle Textfelder von eventuellen Eingaben
     */
    private static void clearAllFields()
    {
        nameTextField.clear();
        serverTextField.clear();
        portTextField.clear();
    }
}