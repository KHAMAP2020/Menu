package models.interfaces.GUIConstantss;

/**
 * Enthält Chatkonstanten
 *
 * @author A.Hoffmann 5137817
 */
public interface ChatConstants
{
  /**
   * Schriftzug auf der Sendeschaltfläche
   */
  public final String SEND_BUTTON_NAME = "senden";
  
  /**
   * Promttext für das Textfeld in dem eine Nachricht
   * geschrieben wird
   */
  public final String MESSAGE_PROMT_TEXT
    = "Gib eine Nachricht ein. ";
  
  /**
   * Status ob eine Nachricht eingehend ist
   */
  public final boolean MASSAGE_COMES_IN = true;
  
  /**
   * Status ob eine Nachricht ausgehend
   */
  public final boolean MASSAGE_GOES_OUT = !MASSAGE_COMES_IN;
}
