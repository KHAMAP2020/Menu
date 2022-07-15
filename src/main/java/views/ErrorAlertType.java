package views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import models.interfaces.GUIConstants.ErrorAlertTypeConstants;

/**
 * Aufzählung aller Alarmierungen von Fehlern
 *
 * @author A.Hoffmann 5137817,P. Gohlke 5157842
 */
public enum ErrorAlertType
{
//-------------------------------------------------------------
//Aufzählungskonstanten
  
  /**
   * Fehler der gezeigt werden soll, wenn die vom Anwender
   * angegebene Portnummer nicht in dem Vorgesehenen
   * Zahlenbereich liegt
   */
  PORT_RANGE
  (
    ErrorAlertTypeConstants.PORT_RANGE_TITLE,
    ErrorAlertTypeConstants.PORT_RANGE_TEXT
  ),
  /**
   * Fehler der gezeigt werden soll, wenn nicht alle
   * Textfelder ausgefüllt wurden
   */
  EMPTY_TEXTFIELD
  (
    ErrorAlertTypeConstants.EMPTY_TEXT_FIELD_TITLE,
    ErrorAlertTypeConstants.EMPTY_TEXT_FIELD_TEXT
  ),
  PORT_ALREADY_IN_USE
  (
    ErrorAlertTypeConstants.PORT_ALREADY_IN_USE_TEXT,
    ErrorAlertTypeConstants.PORT_ALREADY_IN_USE_TITLE
  ),
  SEND_MESSAGE_FAILED
  (
    ErrorAlertTypeConstants.SEND_MESSAGE_FAILED_TITLE,
    ErrorAlertTypeConstants.SEND_MESSAGE_FAILED_TEXT

  ),
  REICIVE_MESSAGE_FAILED
  (
    ErrorAlertTypeConstants.REICIVE_MESSAGE_FAILED_TITLE,
    ErrorAlertTypeConstants.REICIVE_MESSAGE_FAILED_TEXT
  ),

  CLOSING_FAILED
  (
    ErrorAlertTypeConstants.CLOSING_FAILED_TITLE,
    ErrorAlertTypeConstants.CLOSING_FAILED_TEXT
  ),
  INITIALIZATION_FAILED
  (
    ErrorAlertTypeConstants.INITIALIZATION_FAILED_TITLE,
    ErrorAlertTypeConstants.INITIALIZATION_FAILED_TEXT
  ),
  HOSTING_FAILED
  (
    ErrorAlertTypeConstants.HOST_SERVER_FAILED_TITLE,
    ErrorAlertTypeConstants.HOST_SERVER_FAILED_TEXT
  ),
  SERVER_CONNECT_FAILED
  (
    ErrorAlertTypeConstants.SERVER_CONNECT_FAILED_TITLE,
    ErrorAlertTypeConstants.SERVER_CONNECT_FAILED_TEXT
  ),
  SERVER_REACH_FAILED
          (
            ErrorAlertTypeConstants.CANNOT_FIND_SEVER_TITLE,
            ErrorAlertTypeConstants.CANNOT_FIND_SERVER_TEXT
          )
  ;

//-------------------------------------------------------------
//Datenfelder
  
  /**
   * Alarmierung, die die Aufzählung zurückgeben soll
   */
  private final Alert alert = new Alert(AlertType.ERROR);

  //-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Aufzählungskonstanten
   *
   * @param title       Titel der Alarmierung
   * @param contentText Text der Alarmierung
   */
  ErrorAlertType(String title, String contentText)
  {
    /**
     * Titel der Alarmierungen
     */
    /**
     * Text der Alarmierungen
     */

    this.alert.setTitle(title);
    this.alert.setContentText(contentText);
  }
  
  /**
   * Gibt die Alarmierung der Konstanten zurück
   *
   * @return die Alarmierung der Konstanten
   */
  public Alert getAlert()
  {
    return this.alert;
  }
}
