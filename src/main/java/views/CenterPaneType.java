package views;

import javafx.scene.layout.VBox;

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
  
  private VBox pane = null;
  private MenuSettings menuSettings = null;

//-------------------------------------------------------------
//Methoden
  
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
