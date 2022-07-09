package Server;

import java.io.IOException;

public class MessageListener extends Thread{

  @Override
  public void run() {
    System.out.println("MessageListener gestartet");
    while (true){
      try {
        System.out.println("Message erhalten: " + Server.input.readLine());
        System.out.println("message erhalten!");
      } catch (IOException e) {
        System.out.println("Naricht konnte icht gesendet werden");
        try {
          Server.closeEverything(Server.socket,Server.output,Server.input);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        throw new RuntimeException(e);
      }


    }
  }


}
