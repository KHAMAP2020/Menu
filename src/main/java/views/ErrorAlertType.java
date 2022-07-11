package views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import models.interfaces.GUIConstants.ErrorAlertTypeConstants;

/**
 * Aufzählung aller Alarmierungen von Fehlern
 *
 * @author A.Hoffmann 5137817
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
  );

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
