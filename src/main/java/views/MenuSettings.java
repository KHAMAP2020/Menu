package views;

import models.interfaces.GUIConstants.MenuConstants;

/**
 * Aufzählung von Menüeinstellungen
 *
 * @author A.Hoffmann 5137817
 */
public enum MenuSettings
{
//-------------------------------------------------------------
//Aufzählungskonstanten
  
  /**
   * Menüeinstellungen für die Startumgebung
   */
  Start(MenuConstants.START_RETURN_TO_START_ITEM),
  
  /**
   * Menüeinstellungen für die Chatumgebung
   */
  Chat(MenuConstants.CHAT_RETURN_TO_START_ITEM);

//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Ob die Menüoption "returnToStartItem"
   * angezeigt werden soll oder nicht
   */
  private Boolean returnToStartItem;

//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Aufzählung
   *
   * @param returnToStartItem ob die Menüoption
   * "returnToStartItem" angezeigt werden soll
   */
  MenuSettings(Boolean returnToStartItem)
  {
    this.returnToStartItem = returnToStartItem;
  }
  
  /**
   * Gibt zurück, ob die Menüoption "returnToStartItem"
   * angezeigt werden soll
   *
   * @return ob die Menüoption "returnToStartItem"
   * angezeigt werden soll
   */
  public boolean getReturnToStartItem()
  {
    return this.returnToStartItem;
  }
}
