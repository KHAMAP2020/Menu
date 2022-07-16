package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.LoginData;

import models.interfaces.GUIConstants.DialogConstants;

/**
 * Dialog in dem der Anwender Daten zum Beitreten eines Chats
 * eingibt
 *
 * Dafür sind Name des Anwenders, Portnummer und der
 * Host-Adresse notwendig
 *
 * @author A.Hoffmann 5137817
 */
public class JoinDialog
{
//-------------------------------------------------------------
//Datenfelder
  
  /**
   * Dialog das Anmeldedaten zurückgibt
   */
  private static Dialog<LoginData> dialog = null;

  //-----------------------------------------------------------
  //Textfelder für Anwenderangaben
  
  /**
   * Textfeld in dem der Name des Anwenders angegeben
   * werden soll
   */
  private static final TextField nameTextField
    = new TextField();
  
  /**
   * Textfeld in dem die Portnummer angegeben werden soll
   */
  private static final TextField portTextField
    = new TextField();
  
  /**
   * Textfeld in dem der Host-Adresse angegeben wird
   */
  private static final TextField HostAddressTextField
    = new TextField();
  
  //-----------------------------------------------------------
  //Beschriftungen
  
  /**
   * Schriftzug neben dem Namenstextfeld
   */
  private static final Label nameLabel
    = new Label(DialogConstants.NAME_LABEL_STRING);
  
  /**
   * Schriftzug neben dem Portnummertextfeld
   */
  private static final Label portLabel
    = new Label(DialogConstants.PORT_LABEL_STRING);
  
  /**
   * Schriftzug neben dem Hostaddressentextfeld
   */
  private static final Label HostAddressLabel
    = new Label(DialogConstants.HOST_ADDRESS_LABEL_STRING);
  
  //-----------------------------------------------------------
  //Schaltflächen
  
  /**
   * Schaltfläche zur bestätigung der Anwenderangaben
   */
  private static final ButtonType buttonTypeContinue
    = new ButtonType
      (
        DialogConstants.CONTINUE_BUTTON_STRING,
        ButtonBar.ButtonData.OK_DONE
      );
  
  /**
   * Schaltfläche zum Abbrechen des Dialogs
   */
  private static final ButtonType buttonTypeCancel
    = new ButtonType
      (
        DialogConstants.CANCEL_BUTTON_STRING,
        ButtonBar.ButtonData.CANCEL_CLOSE
      );
  
  //-----------------------------------------------------------
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

//-------------------------------------------------------------
//Methoden
  
  /**
   * Erstellt Dialog der Login-Informationen zurückgibt
   *
   * @return die Login-Informationen
   */
  public static Dialog<LoginData> createDialog()
  {
    dialog = new Dialog();
    /**
     * Layouts des Dialogs
     */
    GridPane grid = new GridPane();
    dialog.setTitle(DialogConstants.JOIN_DIALOG_TITLE);

    nameTextField.setPromptText
      (DialogConstants.NAME_PROMT_TEXT);
    
    HostAddressTextField.setPromptText
      (DialogConstants.HOST_ADDRESS_PROMT_TEXT);
    
    portTextField.setPromptText
      (DialogConstants.PORT_PROMT_TEXT);
    
    
    grid.add(nameLabel, 1, 1);
    grid.add(nameTextField, 2, 1);
    grid.add(portLabel, 1, 2);
    grid.add(portTextField, 2, 2);
    grid.add(HostAddressLabel, 1, 3);
    grid.add(HostAddressTextField, 2, 3);
    
    
    dialog.getDialogPane().setContent(grid);
    dialog.getDialogPane().getButtonTypes()
      .addAll(buttonTypeContinue, buttonTypeCancel);
    
    
    eventSettings();
    dialogResultSettings();
    
    
    return dialog;
  }
  
  /**
   * Prüft, ob die Einträge in den Textfeldern den
   * Absendebedingungen entsprechen
   *
   * @return Ob Einträge absendbar sind
   */
  private static boolean validEntries()
  {
    /**
     * gibt an, ob ein Name im Namenstextfeld
     * eingetragen wurde
     */
    boolean nameIsEmpty = nameTextField.getText().isEmpty();
    
    /**
     * gibt an, ob eine Portnummer im
     * Portnummerntextfeld eingetragen wurde
     */
    boolean portIsEmpty = portTextField.getText().isEmpty();
    
    /**
     * gibt an, ob ein servername im
     * serverNamenstextfeld eingetragen wurde
     */
    boolean serverIsEmpty
      = HostAddressTextField.getText().isEmpty();
    
    if (!portIsEmpty)
    {
      String portString = portTextField.getText();
      
      try
      {
        int portNumber = Integer.parseInt(portString);
        if
        (
          portNumber > DialogConstants.portMaxValue
            ||
            portNumber < DialogConstants.portMinValue
        )
        {
          ErrorAlertType.PORT_RANGE.getAlert().showAndWait();
          return false;
        }
      } catch (NumberFormatException exception)
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
    if (nameIsEmpty || serverIsEmpty)
    {
      ErrorAlertType.EMPTY_TEXTFIELD.getAlert().showAndWait();
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
    HostAddressTextField.clear();
    portTextField.clear();
  }
  
  //-----------------------------------------------------------
  //Settings
  
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
    continueButtonEventSettings();
    cancelButtonEventSettings();
  }
  
  /**
   * Stellt das Schaltflächen-Ereignis für die
   * Schaltfläche zum Fortsetzen ein
   */
  private static void continueButtonEventSettings()
  {
    Button continueButton = (Button) dialog.getDialogPane()
      .lookupButton(buttonTypeContinue);
    
    continueButton.addEventFilter
    (
      ActionEvent.ACTION,
      new EventHandler<>()
      {
        @Override
        public void handle(ActionEvent event)
        {
          /*
            Wenn die Einträge nicht Valide sind, soll das
            fortsetzende Schaltflächenevent konsumiert werden
           */
          if (!validEntries())
          {
            event.consume();
          } else
          {
            name = nameTextField.getText();
            server = HostAddressTextField.getText();
            port = Integer.parseInt(portTextField.getText());

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
  private static void cancelButtonEventSettings()
  {
    Button cancelButton = (Button) dialog.getDialogPane()
      .lookupButton(buttonTypeCancel);
    
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
    portTextFieldEventSettings();
  }
  
  /**
   * Stellt Ereignisse für das Portnummer-Textfeld ein
   */
  private static void portTextFieldEventSettings()
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
            !DialogConstants.NUMBER_DEFINITION
                    .contains(event.getCharacter())
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
    (new Callback<ButtonType, LoginData>()
      {
        @Override
        public LoginData call(ButtonType button)
        {
          if (button == buttonTypeContinue)
          {
            return new LoginData(name, server, port);
          }
          return null;
        }
      }
    );
  }
}