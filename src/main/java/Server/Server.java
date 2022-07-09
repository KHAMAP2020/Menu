package Server;

import views.ChatPane;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
  static BufferedWriter output;
  static BufferedReader input;
  static List<ChatPane> clients = new ArrayList<>();
  static int port;
  static ServerSocket serverSocket;
  static Socket socket;
  MessageListener messageListener = new MessageListener();
  ConnectionListener connectionListener = new ConnectionListener();

  public Server(int port)
  {
    this.port = port;


  }


  public void start() throws IOException {
    serverSocket = new ServerSocket(port);
    socket = new Socket("localhost",port);
    output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    output.write("Hallo vom Server");
    output.newLine();
    output.flush();
    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    if(messageListener != null && !messageListener.isAlive())
    {
      messageListener.start();
    }
    else
    {
      System.out.println("MessageListener kann nicht gestartet werden");
      closeEverything(socket,output,input);
    }
    if(connectionListener != null && !connectionListener.isAlive())
    {
      connectionListener.start();
    } else
    {
      System.out.println("connectionListener konnte nicht gestartet werden.");
      closeEverything(socket,output,input);
    }
  }

  public static void closeEverything(Socket socket,BufferedWriter out,BufferedReader in) throws IOException {
    if(socket != null)
    {
      socket.close();
    }
    if(out != null)
    {
      out.close();
    }
    if(in != null)
    {
      in.close();
    }
  }

}
