package models.interfaces.GUIConstants;

/**
 * Konstanten für die Menüleiste
 *
 * @author A.Hoffmann 5137817
 */
public interface MenuConstants
{
  //-----------------------------------------------------------
  //Stil Menü Konstanten
  
  /**
   * Stil-Menü Schriftzug
   */
  public final String SCHEME_NAME = "Schema";
  
  /**
   * Schriftzug von der Menüoption dunkler Stil
   */
  public final String SCHEME_DARK_NAME = "Dunkel";
  
  /**
   * Schriftzug von der Menüoption heller Stil
   */
  public final String SCHEME_BRIGHT_NAME = "Hell";
  
  //-----------------------------------------------------------
  //Verlassen Menü Konstanten
  
  /**
   * Beschriftung von dem Menüpunkt mit den verschiedenen
   * verlassen Optionen
   */
  public final String END_CHAT_MENU = "Verlassen";
  
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
  
  //-----------------------------------------------------------
  // Konstanten für die Menüeinstellungen
  
  /**
   * Ob die Menüoption zurück zum Start im Startlayout sichtbar
   * sein soll
   */
  public final boolean START_RETURN_TO_START_ITEM = false;
  
  /**
   * Ob die Menüoption zurück zum Start im Chatlayout sichtbar
   * sein soll
   */
  public final boolean CHAT_RETURN_TO_START_ITEM = true;
  
  /**
   * Ob am Anfang der helle Stil eingestellt sein soll
   */
  public final boolean SCHEME_BRIGHT_INITIAL_STATUS = true;
  
  /**
   * Ob am Anfang der dunkle Stil eingestellt sein soll
   */
  public final boolean SCHEME_DARK_INITIAL_STATUS = false;
}
