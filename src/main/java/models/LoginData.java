package models;

public class LoginData
{
//-------------------------------------------------------------
//Datenfeld
  
  public static String name = null;
  public static int port = 0;
  public static String HostAdress = null;
 
//-------------------------------------------------------------
//Methoden
  
  public LoginData(String name, String HostAdress, int port)
  {
    this.name = name;
    this.HostAdress = HostAdress;
    this.port = port;
  }
  
  //-----------------------------------------------------------
  //Getter
  
  public String getName()
  {
    return this.name;
  }
  
  public String getHostAdress()
  {
    return this.HostAdress;
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
  
  public void setHostAdress(String hostAdress)
  {
    this.HostAdress = hostAdress;
  }
  
  public void setPort(int port)
  {
    this.port = port;
  }
}
