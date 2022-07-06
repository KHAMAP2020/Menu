package models.interfaces.GUIConstantss;

/**
 * Konstanten für die Menüleiste
 *
 * @author A.Hoffmann 5137817
 */
public interface MenuConstants
{
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
//Stil Menüoptionen

//---------------------------------------------------------
//Verlassen Menüoptionen

    /**
     * Beschriftung von dem Menüpunkt mit den verschiedenen
     * verlassen Optionen
     */
    public final String END_CHAT_MENU
    = "Verlassen";

    /**
     * Beschriftung von der Schaltfläche, die zurück zum
     * start führt.
     */
    public final String RETURN_TO_START_BUTTON_STRING
    = "Zurück zum Menü";

    /**
     * Beschriftung von der Schaltfläche, die das Programm
     * beenden soll
     */
    public final String END_STAGE_BUTTON_STRING 
    = "Programm beenden";


}
