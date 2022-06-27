package controller;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.interfaces.GUIConstants;
import views.*;

public class GUIController
{
    private static BorderPane borderPane = new BorderPane();
    private static BorderPane chatPane = null;
    private static BorderPane startPane = null;
    private static Scene scene = null;


    private static MenuBar chatMenuBar = null;

    private static MenuBar menuBar = null;

    private static Pane centerPane = null;

    public static void createGUI(Stage stage)
    {
        stage.setTitle(GUIConstants.STAGE_NAME);

        //chatPane = ChatPane.createChatPane();

        scene = StartPane.createStartPane();
        setStartPane();

        stage.setScene(scene);
        stage.show();
    }

    public static Scene getScene()
    {
        return scene;
    }

    public static void setStartPane ()
    {
        borderPane.setCenter(startPane);
    }


    public static void setBorderPane (BorderPane pane)
    {
        borderPane.setCenter(pane);
    }

}
