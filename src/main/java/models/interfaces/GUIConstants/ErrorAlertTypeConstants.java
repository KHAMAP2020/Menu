package models.interfaces.GUIConstants;

import models.LoginData;

/**
 * Konstanten für die Fehler-Alarmierungsaufzählung
 * "ErrorAlertType"
 *
 * @author A.Hoffmann 5137817, Philipp Gohlke 5157842
 */
public interface ErrorAlertTypeConstants
{
  /**
   * Titel von der Fehler Alarmierung "PORT_RANGE"
   */
  public final String PORT_RANGE_TITLE = "Ungültige Portnummer";
  
  /**
   * Text von der Fehler Alarmierung "PORT_RANGE"
   */
  public final String PORT_RANGE_TEXT
    = "Sie müssen einen Wert zwischen "
    + RegisterConstants.portMinValue + " und "
    + RegisterConstants.portMaxValue + " angeben.";
  
  /**
   * Titel von der Fehler Alarmierung "EMPTY_TEXTFIELD"
   */
  public final String EMPTY_TEXT_FIELD_TITLE
    = "Leeres TextFeld";
  
  /**
   * Text von der Fehler Alarmierung "EMPTY_TEXTFIELD"
   */
  public final String EMPTY_TEXT_FIELD_TEXT
    = "Sie müssen alle Felder Ausfüllen.";

  //Fehlermeldung für den Server--------------------------------

  public final String SERVER_CONNECT_FAILED =
          "Verbindung zum Server konnte nicht aufgebaut werden!";

  public final String CONNECTION_LOST =
          "Verbindung zum Server wurde getrennt";

  public final String SEND_MESSAGE_FAILED =
          "Naricht konnte nicht gesendet werden";

  public final String HOST_SERVER_FAILED =
          "Server konnte nicht erstellt werden";

  public final String IP_IS_WRONG =
          "IP-Adresse konnte nicht zugeordnet werden";

  public final String PORT_IS_WRONG =
          "Port konnte nicht zugeordnet werden.";

  public final String NAME_ALREADY_IN_USE =
        "Name: " + LoginData.name + " ist bereits vergeben";

  public final String HOST_ADRESS_ALREADY_IN_USE =
         "Host-Adresse: " + LoginData.HostAdress + " ist bereits vergeben";

  public final String PORT_ALREADY_IN_USE =
         "Port:" + LoginData.port + " ist bereits vergeben";

}
