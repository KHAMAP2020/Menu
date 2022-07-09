package Server;
import java.io.*;

public class MessageController extends Thread {
  public static BufferedWriter out;
  public static BufferedReader in;

  public MessageController() throws IOException {

    System.out.println("MessageController gestartet");

  }

  @Override
  public void run() {
    try {
    BufferedWriter  out = new BufferedWriter(new OutputStreamWriter(Server.client.getOutputStream()));
    BufferedReader  in = new BufferedReader(new InputStreamReader(Server.client.getInputStream()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    while(true){
      System.out.println("Message wird gelesen");
      try{

          System.out.println("MessageController erhalten: " + in.readLine());

      }catch(IOException e){
        e.printStackTrace();
        System.out.println("fehler");
      }

    }



  }



}
