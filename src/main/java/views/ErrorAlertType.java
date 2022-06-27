package views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.interfaces.GUIConstants;

public enum ErrorAlertType
{
    PORT_RANGE
    (
        GUIConstants.PORT_RANGE_ERROR_TITLE,
        GUIConstants.PORT_RANGE_ERROR_TEXT
    ),
    EMPTY_TEXTFIELD
    (
        GUIConstants.EMPTY_TEXT_FIELD_ERROR_TITLE,
        GUIConstants.EMPTY_TEXT_FIELD_ERROR_TEXT
    );



    private Alert alert = new Alert(AlertType.ERROR);
    private String title = null;
    private String contentText = null;
    ErrorAlertType(String title, String contentText)
    {
        this.title = title;
        this.contentText = contentText;

        this.alert.setTitle(title);
        this.alert.setContentText(contentText);
    }

    public Alert getAlert ()
    {
        return this.alert;
    }
}
