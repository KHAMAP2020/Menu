package models;

/**
 * Login Daten die der Registerdialog zurück geben soll
 *
 * @author A.Hoffmann 5137817
 */
public class LoginData
{
//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Nutzername, der Später im Chat für
   * alle sichtbar sein soll um zu sehen, wer die Nachricht
   * geschrieben hat.
   */
  public static String name = null;
  
  /**
   * Portnummer des Servers
   */
  public static int port = 0;
  
  /**
   * Adresse des Hosts
   */
  public static String HostAdress = null;

//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Login-Daten
   *
   * @param name Name des Anwenders
   * @param HostAddress Adresse des Hosts
   * @param port Portnummer
   */
  public LoginData(String name, String HostAddress, int port)
  {
    this.name = name;
    this.HostAdress = HostAddress;
    this.port = port;
  }
  
  //-----------------------------------------------------------
  //Getter
  
  /**
   * Gibt den Namen des Anwenders zurück
   * @return den Namen des Anwenders
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * Gibt die Adresse des Hosts zurück
   * @return die Adresse des Hosts
   */
  public String getHostAdress()
  {
    return this.HostAdress;
  }
  
  /**
   * gibt die Portnummer zurück
   * @return die Portnummer
   */
  public int getPort()
  {
    return this.port;
  }
}
