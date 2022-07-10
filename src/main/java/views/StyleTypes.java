package views;

import models.interfaces.GUIConstants;
import models.interfaces.GUIConstantss.StyleTypesConstants;

/**
 * Stilarten, die Eingestellt werden können
 *
 * @author A.Hoffmann 5137817
 */
public enum StyleTypes
{
//-------------------------------------------------------------
//Aufzählungskonstanten
  
  /**
   * Heller Stil
   */
  BRIGHT(StyleTypesConstants.BRIGHT_THEME_PATH),
  
  /**
   * Dunkler Stil
   */
  DARK(StyleTypesConstants.DARK_THEME_PATH);
 
//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Pfad des Stils
   */
  private String path;
  
//---------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Stile
   *
   * @param path Pfad des Stils
   */
  StyleTypes(String path)
  {
    this.path = path;
  }
  
  //-------------------------------------------------------
  //Getter
  
  /**
   * Gibt den Pfad des Stils zurück
   *
   * @return den Pfadn des Stils
   */
  public String getPath()
  {
    return this.path;
  }
}
