package controller;

import Server.Server;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{

    public Main() throws IOException
    {
    }

    public static void main(String[] args)
    {
        try
        { Server server = new Server(3333);
            server.start();


            launch(args);
        }catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    @Override
    public void start(Stage stage) throws Exception
    {
        //System.out.println();
       GUIController.initGUI(stage);
    }
}
