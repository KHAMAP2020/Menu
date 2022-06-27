package controller;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.interfaces.GUIConstants;
import views.GUICreator;
import views.MenuCreator;
import views.StartMenuCreator;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
       GUIController.createGUI(stage);



        //GUICreator guiCreator = new GUICreator();
        //guiCreator.createGUI(stage);

    }
}
