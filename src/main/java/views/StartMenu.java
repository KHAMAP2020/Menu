package views;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import models.interfaces.GUIConstants;

public class StartMenu
{
    private static RadioMenuItem schemeBright = new RadioMenuItem(GUIConstants.SCHEME_BRIGHT_NAME);
    private static RadioMenuItem schemeDark = new RadioMenuItem(GUIConstants.SCHEME_DARK_NAME);

    private static MenuBar menuBar = new MenuBar();

    private static Menu schemeMenu =new Menu(GUIConstants.SCHEME_NAME);
    private static MenuItem returnToStartItem = new MenuItem(GUIConstants.RETURN_BUTTEN_STRING);
    private static Menu returnMenu = new Menu(GUIConstants.END_CHAT_MENU);
    private static ToggleGroup schemeToggleGroup = new ToggleGroup();
    public static MenuBar createMenu()
    {
        schemeBright.setToggleGroup(schemeToggleGroup);
        schemeDark.setToggleGroup(schemeToggleGroup);

        schemeMenu.getItems().setAll(schemeBright,schemeDark);
        returnMenu.getItems().add(returnToStartItem);
        setItemEvents();

        //Initiale RadioEinstellung
        schemeBright.setSelected(GUIConstants.SCHEME_BRIGHT_INITIAL_STATUS);
        schemeDark.setSelected(GUIConstants.SCHEME_DARK_INITIAL_STATUS);
        returnToStartItem.setVisible(GUIController.getCurrentMenuSettings().getReturnToStartItem());
        returnMenu.setVisible(GUIController.getCurrentMenuSettings().getReturnMenu());
        menuBar.getMenus().setAll(schemeMenu,returnMenu);

        return menuBar;
    }
    private static void setItemEvents()
    {
        setSchemeBrightEvent();
        setSchemeDarkEvents();
        setReturnToStartEvent();
    }

    private static void setSchemeBrightEvent()
    {
        schemeBright.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    GUIController.setStyle(Style.BRIGHT);
                }
            }
        );

    }

    private static void setSchemeDarkEvents()
    {
        schemeDark.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    GUIController.setStyle(Style.DARK);
                }
            }
        );
    }

    private static void setReturnToStartEvent()
    {
        returnToStartItem.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    GUIController.setCenterPane(CenterPaneType.START);
                }
            }
        );
    }

    public static void updateSettings ()
    {
        returnToStartItem.setVisible(GUIController.getCurrentMenuSettings().getReturnToStartItem());
        returnMenu.setVisible(GUIController.getCurrentMenuSettings().getReturnMenu());
    }
}
