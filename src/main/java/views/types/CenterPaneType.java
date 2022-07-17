package views.types;

import javafx.scene.layout.VBox;
import views.ChatPane;
import views.StartPane;

/**
 * Hier sind alle GUI Konsanten des Programmns
 * hinterlegt
 *
 * @author A.Hoffmann 5137817
 */
public enum CenterPaneType
{
//-------------------------------------------------------------
//Aufzählungskonstanten
  
  /**
   * Startzentruminhalte
   */
  START(new StartPane().getPane(), MenuSettings.Start),
  /**
   * Chatzentruminhalt
   */
  CHAT(new ChatPane().getPane(), MenuSettings.Chat);

//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Layout der Zentruminhalte
   */
  private final VBox pane;
  
  /**
   * Menüeinstellungen der Zentruminhalte
   */
  private final MenuSettings menuSettings;

//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Aufzählungskonstanten
   * @param pane das Layout der Aufzählungskonstanten
   * @param menuSettings Menüeinstellungen der
   *                     Aufzählungskonstanten
   */
  CenterPaneType(VBox pane, MenuSettings menuSettings)
  {
    this.pane = pane;
    this.menuSettings = menuSettings;
  }
  
  //-----------------------------------------------------------
  //Getter
  
  /**
   * Gibt das Zentrumlayout zurück
   *
   * @return das Zentrumlayout
   */
  public VBox getPane()
  {
    return this.pane;
  }
  
  /**
   * Gibt die Menüinstellungen zurück
   *
   * @return die Menüeinstellungen
   */
  public MenuSettings getMenuSettings()
  {
    return this.menuSettings;
  }
}
