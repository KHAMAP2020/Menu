package controller;

import models.AClient;

import java.util.ArrayList;

/**
 * @author Philipp Gohlke 5157842
 */
public class ClientController
{
  private static AClient aClient = null;
  /*
  getter-/setter-Methoden
   */
  public static void createAClient(String serverName, int port, String userName)
  {
    aClient = new AClient(serverName,port,userName);
  }
  
  public static void setAClient(AClient client)
  {
    aClient = client;
  }
  public static AClient getAClient()
  {
    return aClient;
  }
}
