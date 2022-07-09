package controller;

import models.AClient;

import java.util.ArrayList;

public class ClientController
{
  private static AClient aClient = null;
  
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
