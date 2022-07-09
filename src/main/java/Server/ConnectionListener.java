package Server;

import views.ChatPane;

import java.io.IOException;

public class ConnectionListener extends Thread{

  public ConnectionListener(){

  }

  @Override
  public void run()
  {
    System.out.println("ConnectionsListener gestartet");
    while(Server.serverSocket != null && !Server.serverSocket.isClosed())
    {
      try {
        Server.socket = Server.serverSocket.accept();
        System.out.println("Verbindung akzeptiert");
        /*ChatPane client = new ChatPane();
        synchronized (Server.clients){
          Server.clients.add(client);
        }*/
      } catch (IOException e) {
        System.out.println("Server konnte Client nicht akzeptieren");
        e.printStackTrace();
      }
      while(Server.socket != null && !Server.socket.isConnected())
      {
        System.out.println("waiting for client...");
      }
    }
  }
}
