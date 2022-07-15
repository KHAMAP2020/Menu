package controller;

import models.Client;

/**
 * @author Philipp Gohlke 5157842
 */
public class ClientController
{
  private static Client client = null;
  
  public static void setAClient(Client client)
  {
    ClientController.client = client;
  }
  public static Client getAClient()
  {
    return client;
  }
}
