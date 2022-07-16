package models.interfaces.GUIConstants;

import models.LoginData;

/**
 * Konstanten für die Fehler-Alarmierungsaufzählung
 * "ErrorAlertType"
 *
 * @author A.Hoffmann 5137817, P. Gohlke 5157842
 */
public interface ErrorAlertTypeConstants
{
  /**
   * Titel von der Fehler Alarmierung "PORT_RANGE"
   */
  final String PORT_RANGE_TITLE = "Ungültige Portnummer";
  
  /**
   * Text von der Fehler Alarmierung "PORT_RANGE"
   */
  final String PORT_RANGE_TEXT
    = "Sie müssen einen Wert zwischen "
    + DialogConstants.portMinValue + " und "
    + DialogConstants.portMaxValue + " angeben.";
  
  /**
   * Titel von der Fehler Alarmierung "EMPTY_TEXTFIELD"
   */
  final String EMPTY_TEXT_FIELD_TITLE
    = "Leeres Text Feld";
  
  /**
   * Text von der Fehler Alarmierung "EMPTY_TEXTFIELD"
   */
  final String EMPTY_TEXT_FIELD_TEXT
    = "Sie müssen alle Felder Ausfüllen.";

  //Fehlermeldung für den Server--------------------------------

  final String SERVER_CONNECT_FAILED_TEXT =
          "Verbindung zum Server konnte nicht aufgebaut werden!";

  final String SERVER_CONNECT_FAILED_TITLE =
          "Server Error!";

  final String SEND_MESSAGE_FAILED_TEXT =
          "Naricht konnte nicht gesendet werden";

  final String SEND_MESSAGE_FAILED_TITLE =
          "Message Error!";
  final String HOST_SERVER_FAILED_TEXT =
          "Server konnte nicht erstellt werden";

  final String HOST_SERVER_FAILED_TITLE =
          "Host Error";

  final String PORT_ALREADY_IN_USE_TEXT =
         "Port:" + LoginData.port + " ist bereits vergeben";
  final String PORT_ALREADY_IN_USE_TITLE =
          "Port nicht erlaubt";
  final String REICIVE_MESSAGE_FAILED_TITLE =
          "Message Error";
  final String REICIVE_MESSAGE_FAILED_TEXT =
          "Fehler beim einlesen der Naricht";

  final String CLOSING_FAILED_TEXT =
          "Fehler beim Schließen";
  final String CLOSING_FAILED_TITLE =
          "Closing Error";

  final String INITIALIZATION_FAILED_TEXT =
          "Initialisierung fehlgeschlagen";
  final String INITIALIZATION_FAILED_TITLE =
          "Initialisierung Error";
  final String CANNOT_FIND_SERVER_TEXT =
          "Server konnte nicht gefunden werden";
  final String CANNOT_FIND_SEVER_TITLE =
          "Connection Error!";
  final String HOST_ADRESS_WRONG_TEXT =
          "angegebene Host-Adresse ist Fehlerhaft";
  final String HOST_ADRESS_WRONG_TITLE =
          "Host-Adress Error";
  final String CONNECTION_LOST_TEXT =
          "Verbindung zum Server verloren";
  final String CONNECTION_LOST_TITLE =
          "Connection Error";
  final String SERVER_CLOSING_TITLE =
          "Server Warnung";
  final String SERVER_CLOSING_TEXT =
          "Server wird geschlossen";
}
