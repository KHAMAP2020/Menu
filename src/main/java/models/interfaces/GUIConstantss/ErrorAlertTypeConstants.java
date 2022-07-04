package models.interfaces.GUIConstantss;

public interface ErrorAlertTypeConstants
{
    /**
     *
     */
    public final String PORT_RANGE_ERROR_TITLE = "Ungültige Portnummer";

    public final String PORT_RANGE_ERROR_TEXT = "Sie müssen einen Wert zwischen " + RegisterConstants.portMinValue + " und " + RegisterConstants.portMaxValue + " angeben.";

    public final String EMPTY_TEXT_FIELD_ERROR_TITLE = "Leeres TextFeld";

    public final String EMPTY_TEXT_FIELD_ERROR_TEXT = "Sie müssen alle Felder Ausfüllen.";
}
