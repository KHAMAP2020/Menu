package models;

import models.interfaces.GUIConstants.DialogConstants;
import models.interfaces.GeneralConstants;

/**
 * Anmeldedaten die vom Host dialog zurückgegeben werden
 * und alle Informationen enthalten um einen Chat zu hosten
 */
public class HostData
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
  public static String HostAddress
    = GeneralConstants.HOST_ADRESS;

//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Login-Daten
   *
   * @param name Name des Anwenders
   * @param port Portnummer
   */
  public HostData(String name, int port)
  {
    this.name = name;
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
    return this.HostAddress;
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
