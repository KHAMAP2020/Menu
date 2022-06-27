package controller;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import models.interfaces.GUIConstants;
import views.*;
import views.CenterPaneType;
import views.MenuSettings;
import views.Style;

public class GUIController
{
    private static Stage stage = null;

    //

    private static BorderPane borderPane = new BorderPane();

    private static Scene scene = new Scene(borderPane,GUIConstants.SCENE_WIDTH,GUIConstants.SCENE_HEIGHT);

    private static CenterPaneType currentCenterPane = GUIConstants.INITIAL_CENTER_PANE;

    private static Style currentStyle = Style.BRIGHT;
    private static MenuBar menuBar = StartMenu.createMenu();
    public static void createGUI(Stage mainStage)
    {
        stage = mainStage;
        stage.setScene(scene);
        stage.setResizable(GUIConstants.RESIZABLE);
        stage.setTitle(GUIConstants.STAGE_NAME);
        borderPane.setTop(menuBar);
        borderPane.setCenter(currentCenterPane.getPane());
        setCenterPane(currentCenterPane);
        setCenterPane(CenterPaneType.CHAT);
        stage.show();
    }

    public static void setCenterPane(CenterPaneType centerPane)
    {
        borderPane.setCenter(centerPane.getPane());
        currentCenterPane = centerPane;
        StartMenu.updateSettings();
    }

    public static void setStyle (Style style)
    {
        scene.getStylesheets().remove(currentStyle.getPath());
        scene.getStylesheets().add(style.getPath());
        currentStyle = style;
    }

    public static MenuSettings getCurrentMenuSettings()
    {
        return currentCenterPane.getMenuSettings();
    }
}
