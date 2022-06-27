package views;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import models.interfaces.GUIConstants;

public class ChatMenu extends StartMenu
{
    private MenuItem returnToStartItem = new MenuItem(GUIConstants.RETURN_BUTTEN_STRING);
    private Menu returnMenu = new Menu(GUIConstants.END_CHAT_MENU);

    public ChatMenu(Scene scene)
    {
        super(scene);
        setMenuItemEvents();
        returnMenu.getItems().add(returnToStartItem);
        super.getMenu().getMenus().addAll(returnMenu);

    }

    private void setMenuItemEvents()
    {
        returnToStartItem.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    //GUIController.setScene(GUIController.getStartScene());
                }
            }
        );
    }


}
