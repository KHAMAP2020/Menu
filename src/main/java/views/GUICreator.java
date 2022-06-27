package views;

import controller.GUIController;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.interfaces.GUIConstants;

public class GUICreator
{
    private BorderPane borderPane = new BorderPane();
    private Scene scene = new Scene(borderPane,GUIConstants.SCENE_WIDTH,GUIConstants.SCENE_HEIGHT);
    private VBox startPane = null;
    private Pane centerPane = null;

    public void GUICreator ()
    {

    }
    public void createGUI(Stage stage)
    {
        stage.setTitle(GUIConstants.STAGE_NAME);



        MenuCreator menuCreator = new MenuCreator(this.scene);
        MenuBar menuBar = menuCreator.createMenu();


        ChatManuCreator chatManuCreator = new ChatManuCreator(this.scene);
        MenuBar chatMenuBar = chatManuCreator.createChatMenu();

        StartPaneCreator startPaneCreator = new StartPaneCreator();
        startPane = startPaneCreator.createStartPane();

        ChatPaneCreator chatPaneCreator = new ChatPaneCreator();
        VBox chatPane = chatPaneCreator.createChatPane();





        setcenterPane(chatPane);

        this.borderPane.setTop(chatMenuBar);
        //borderPane.setLeft(new Label("This will be at the left"));
        //borderPane.setRight(new Label("This will be at the Right"));

        this.borderPane.setCenter(centerPane);

        //borderPane.setBottom(new Label("This will be at the bottom"));
        scene = new Scene(borderPane,GUIConstants.SCENE_WIDTH,GUIConstants.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene()
    {
        return this.scene;
    }

    public void setcenterPane (Pane pane)
    {
        this.centerPane = pane;
    }

    public VBox getStartPane()
    {
        return this.startPane;
    }
}

