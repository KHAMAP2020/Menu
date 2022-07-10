package models.interfaces;

import views.CenterPaneType;

/**
 * Hier sind alle GUI Konsanten des Programmns
 * hinterlegt
 *
 * @author A.Hoffmann 5137817
 */
public interface GUIConstants
{
    public final int START_V_BOX_SPACING = 10;
//---------------------------------------------------------
//Menu Konstanten

    //-----------------------------------------------------
    //Schema Konstanten
    public final String SCHEME_NAME = "Schema";


    public final String SCHEME_DARK_NAME = "Dunkel";

    public final String SCHEME_BRIGHT_NAME = "Hell";

    public final String BRIGHT_THEME_FILE = "BrightTheme.css";
    public final String DARK_THEME_FILE ="DarkTheme.css";


    //public final String BRIGHT_THEME_PATH = StartMenu.class.getResource(GUIConstants.BRIGHT_THEME_FILE).toExternalForm();
    public final String BRIGHT_THEME_PATH = "file:target/classes/views/BrightTheme.css";
    public final String DARK_THEME_PATH = "file:target/classes/views/DarkTheme.css";

    public final boolean START_RETURN_TO_START_ITEM = false;

    public final boolean CHAT_RETURN_TO_START_ITEM = true;

    public final boolean SCHEME_BRIGHT_INITIAL_STATUS = true;

    public final boolean SCHEME_DARK_INITIAL_STATUS = false;

    public final boolean RETURN_TO_START_INITIAL_STATUS = false;
//---------------------------------------------------------
//StartPane Konstanten

    public final String JOIN_BUTTON_NAME = "Chat beitreten";

    public final String HOST_BUTTON_NAME = "Chat erstellen";

    public String WELCOME_LABEL_STRING = "Herzlich willkommen!";

//---------------------------------------------------------
// Chatmenü Konstanten

    public final String RETURN_TO_START_BUTTEN_STRING = "Zurück zum Menü";

    public final String END_STAGE_BUTTON_STRING = "Programm beenden";
    public final String END_CHAT_MENU = "Verlassen";
    
}
