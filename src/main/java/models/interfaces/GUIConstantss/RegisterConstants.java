package models.interfaces.GUIConstantss;

/**
 * Enthält konstanten für den Registrierungsdialog
 *
 * @author A.Hoffmann 5137817
 */
public interface RegisterConstants
{
  /**
   * Dialog Titel
   */
  public final String CLIENT_DIALOG_TITLE = "Chat Beitreten";

//-------------------------------------------------------------
//Butten-Konstanten
  
  /**
   * Schriftzug von der Fortsetzungschaltfläche
   */
  public final String CONTINUE_BUTTON_STRING = "Weiter";
  
  /**
   * Schriftzug von der Abbrechenschaltfläche
   */
  public final String CANCEL_BUTTON_STRING = "Zurück";

//-------------------------------------------------------------
//Label-Konstanten
  
  /**
   * Schriftzug neben dem Namen-Textfeld
   */
  public final String NAME_LABEL_STRING = "Dein Name: ";
  
  /**
   * Schriftzug neben dem Portnummer-Textfeld
   */
  public final String PORT_LABEL_STRING = "Port Nummer: ";
  
  /**
   * Schriftzug neben dem Host-Adressen-Textfeld
   */
  public final String HOST_ADDRESS_LABEL_STRING
    = "Host-Adresse: ";


//---------------------------------------------------------
//Textfeld-Konstanten
  
  /**
   * Promttext für das Namen-Textfeld
   */
  public final String NAME_PROMT_TEXT = "Gib deinen Namen ein.";
  
  /**
   * Promttext für das Portnummer-Textfeld
   */
  public final String PORT_PROMT_TEXT
    = "Gib die Portnummer ein.";
  
  /**
   * Promttext für das Host-Adressen-Textfeld
   */
  public final String HOST_ADDRESS_PROMT_TEXT
    = "Gib die Adresse des Hosts ein.";

//---------------------------------------------------------
//Filter-Konstanten
  
  /**
   * Zahlendefinition für das Portnummer-Textfeld
   */
  public final String NUMBER_DEFINITION = "0123456789";
  
  /**
   * Kleinste mögliche Portnummer
   */
  public final int portMinValue = 0;
  
  /**
   * Größte mögliche Portnummer
   */
  public final int portMaxValue = Integer.MAX_VALUE;
}
