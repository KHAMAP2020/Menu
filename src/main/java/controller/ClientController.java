package controller;

import models.Client;

/**
 * @author Philipp Gohlke 5157842
 */
public class ClientController
{
  //Beinhaltet die getter-/setter-Methoden des Clients
  private static Client client = null;
  
  public static void setClient(Client client)
  {
    ClientController.client = client;
  }
  public static Client getClient()
  {
    return client;
  }
}
