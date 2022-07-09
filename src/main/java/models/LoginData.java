package models;

public class LoginData
{
//-------------------------------------------------------------
//Datenfeld
  
  private String name = null;
  public static int port = 0;
  private String serverName = null;
 
//-------------------------------------------------------------
//Methoden
  
  public LoginData(String name, String serverName, int port)
  {
    this.name = name;
    this.serverName = serverName;
    this.port = port;
  }
  
  //-----------------------------------------------------------
  //Getter
  
  public String getName()
  {
    return this.name;
  }
  
  public String getServerName()
  {
    return this.serverName;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  //-----------------------------------------------------------
  //Setter
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setServerName(String serverName)
  {
    this.serverName = serverName;
  }
  
  public void setPort(int port)
  {
    this.port = port;
  }
}
