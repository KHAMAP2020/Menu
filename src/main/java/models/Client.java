package models;

public class Client
{
    private String name = null;
    private int port = 0;
    private String serverName = null;
    public Client(String name, String serverName, int port)
    {
       this.name = name;
       this.serverName = serverName;
       this.port = port;
    }

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
