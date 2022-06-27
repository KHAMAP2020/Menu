package models.interfaces;

import views.MenuCreator;
import views.StartMenuCreator;

import java.net.URL;

/**
 * Hier sind alle GUI Konsanten des Programmns
 * hinterlegt
 *
 * @author A.Hoffmann 5137817
 */
public interface GUIConstants
{
    /**
     * Name der Stage
     */
    public final String STAGE_NAME = "Name der Stage";

    public final int SCENE_WIDTH = 600;

    public final int SCENE_HEIGHT = 400;

    public final int V_BOX_SPACING = 10;
//---------------------------------------------------------
//Menu Konstanten

    //-----------------------------------------------------
    //Schema Konstanten
    public final String SCHEME_NAME = "Schema";


    public final String SCHEME_DARK_NAME = "Dunkel";

    public final String SCHEME_BRIGHT_NAME = "Hell";

    public final String BRIGHT_THEME_FILE = "BrightTheme.css";
    public final String DARK_THEME_FILE ="DarkTheme.css";
    public final String BRIGHT_THEME_PATH = StartMenuCreator.class.getResource(GUIConstants.BRIGHT_THEME_FILE).toExternalForm();
    public final String DARK_THEME_PATH = StartMenuCreator.class.getResource(GUIConstants.DARK_THEME_FILE).toExternalForm();;

    public final Boolean SET_BRIGHT = true;

    public final Boolean SET_DARK = false;

//---------------------------------------------------------
//StartPane Konstanten

    public final String JOIN_BUTTON_NAME = "Chat beitreten";

    public final String HOST_BUTTON_NAME = "Chat erstellen";

    public String WELCOME_LABEL_STRING = "Herzlich willkommen!";

//---------------------------------------------------------
//ChatPane Konstanten

    public final String SEND_BUTTON_NAME = "senden";

    public final String MASSAGE_PROMT_TEXT = "Gib eine Nachricht ein. ";

    public final Boolean MASSAGE_COMES_IN = true;

    public final Boolean MASSAGE_GOES_OUT = !MASSAGE_COMES_IN;

//---------------------------------------------------------
//Dialog Konstanten

    public final String NUMBER_DEFINITION = "0123456789";
    public final String CLIENT_DIALOG_TITLE = "Chat Beitreten";

    public final String NAME_LABEL_STRING = "Dein Name: ";

    public final String PORT_LABEL_STRING = "Port Nummer: ";

    public final String SERVER_LABEL_STRING = "Name des Servers: ";

    public final String NAME_PROMT_TEXT = "Gib deinen Namen ein.";

    public final String PORT_PROMT_TEXT = "Gib die Portnummer ein.";

    public final String SERVER_PROMT_TEXT = "Gib den Servernamen ein.";

    public final String CONTINUE_BUTTON_STRING = "Weiter";

    public final String CANCEL_BUTTON_STRING = "Zurück";

    public final int portMinValue =0;

    public final int portMaxValue = Integer.MAX_VALUE;
    public final String PORT_RANGE_ERROR_TITLE = "Ungültige Portnummer";

    public final String PORT_RANGE_ERROR_TEXT = "Sie müssen einen Wert zwischen " + portMinValue + " und " + portMaxValue + " angeben.";


//---------------------------------------------------------
// ListCell Konstanten

    public final double MASSAGE_MAX_WIDTH = 200;

//---------------------------------------------------------
// Chatmenü Konstanten

    public final String RETURN_BUTTEN_STRING = "Zurück zum Menü";

    public final String END_CHAT_MENU = "Chat beenden";
}
