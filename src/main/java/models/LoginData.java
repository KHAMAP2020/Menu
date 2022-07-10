package models;

/**
 * @author A.Hoffmann 5137817
 */
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
    LoginData.name = name;
    LoginData.HostAdress = HostAdress;
    LoginData.port = port;
  }
  
  //-----------------------------------------------------------
  //Getter
  
  public String getName()
  {
    return name;
  }
  
  public String getHostAdress()
  {
    return HostAdress;
  }
  
  public int getPort()
  {
    return port;
  }
  
  //-----------------------------------------------------------
  //Setter

}
