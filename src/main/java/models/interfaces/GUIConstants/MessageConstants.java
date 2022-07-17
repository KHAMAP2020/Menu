package models.interfaces.GUIConstants;

/**
 * Konstanten für Nachrichten
 *
 * @author A.Hoffmann 5137817
 */
public interface MessageConstants
{
  /**
   * Wie breit eine Nachricht im verhältnis zur Chatbreite
   * sein soll
   */
  public final double MESSAGE_WIDTH_SCALE = 0.8;
  
  /**
   * Status ob eine Nachricht eingehend ist
   */
  public final boolean MASSAGE_COMES_IN = true;
  
  /**
   * Status ob eine Nachricht ausgehend
   */
  public final boolean MASSAGE_GOES_OUT = !MASSAGE_COMES_IN;
}
