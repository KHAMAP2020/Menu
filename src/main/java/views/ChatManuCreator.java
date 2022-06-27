package views;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import models.interfaces.GUIConstants;

public class ChatManuCreator extends MenuCreator
{
    private static MenuItem returnButton = new MenuItem(GUIConstants.RETURN_BUTTEN_STRING);

    private static Menu returnMenu = new Menu(GUIConstants.END_CHAT_MENU);

    public ChatManuCreator(Scene scene)
    {
        super(scene);
    }

    public MenuBar createChatMenu ()
    {
        super.createMenu();
        returnButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //GUIController.setcenterPane(GUIController.getStartPane());
            }
        });

        returnMenu.getItems().addAll(returnButton);

        super.getMenuBar().getMenus().add(returnMenu);
        return super.getMenuBar();
    }

}
