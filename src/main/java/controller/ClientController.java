package controller;

import models.Client;

/**
 * @author Philipp Gohlke 5157842
 */
public class ClientController
{
  private static Client client = null;
  /*
  getter-/setter-Methoden
  Client wird erzeugt
   */
  public static void createAClient(String hostAdress, int port, String userName)
  {
    client = new Client(hostAdress,port,userName);
  }
  
  public static void setAClient(Client client)
  {
    ClientController.client = client;
  }
  public static Client getAClient()
  {
    return client;
  }
}
