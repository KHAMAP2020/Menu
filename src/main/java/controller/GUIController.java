package controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import models.interfaces.GUIConstants;
import views.*;

public class GUIController
{
    private static Stage stage = null;
    private static Scene startScene = null;
    private static Scene chatScene = null;

    private static Scene currentScene = null;
    private static String currentStylePath = null;

    public static void createGUI(Stage mainStage)
    {
        stage =mainStage;
        stage.setTitle(GUIConstants.STAGE_NAME);

        startScene = StartSceneCreator.createStartScene();
        chatScene = ChatSceneCreator.createChatScene();
        setInitialScene();
       // setScene(chatScene);
        //stage.setScene(startScene);
        stage.show();
    }


    private static void setInitialScene()
    {
        currentScene = startScene;
        stage.setScene(currentScene);
    }

    public static void setScene (Scene scene)
    {
        scene.getStylesheets().remove(GUIConstants.BRIGHT_THEME_PATH);
        scene.getStylesheets().remove(GUIConstants.BRIGHT_THEME_PATH);
        scene.getStylesheets().add(currentStylePath);
        currentScene = scene;
        stage.setScene(currentScene);
    }

    public static void setStyle(String path)
    {
        currentScene.getStylesheets().remove(GUIConstants.BRIGHT_THEME_PATH);
        currentScene.getStylesheets().remove(GUIConstants.BRIGHT_THEME_PATH);
        currentStylePath = path;
        currentScene.getStylesheets().add(path);
    }

    public static Scene getStartScene()
    {
        return startScene;
    }
}
