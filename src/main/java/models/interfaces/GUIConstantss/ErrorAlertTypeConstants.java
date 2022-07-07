package models.interfaces.GUIConstantss;

/**
 * Konstanten für die Fehler-Alarmierungsaufzählung
 * "ErrorAlertType"
 *
 * @author A.Hoffmann 5137817
 */
public interface ErrorAlertTypeConstants
{
    /**
     * Titel von der Fehler Alarmierung "PORT_RANGE"
     */
    public final String PORT_RANGE_TITLE
    = "Ungültige Portnummer";

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
}
