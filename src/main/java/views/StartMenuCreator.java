package views;

import controller.GUIController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import models.interfaces.GUIConstants;

public class StartMenuCreator
{
    private static RadioMenuItem schemeBright = new RadioMenuItem(GUIConstants.SCHEME_BRIGHT_NAME);
    private static RadioMenuItem schemeDark = new RadioMenuItem(GUIConstants.SCHEME_DARK_NAME);

    private static MenuBar menuBar = new MenuBar();

    private static Menu scheme =new Menu(GUIConstants.SCHEME_NAME);

    private static ToggleGroup schemeToggleGroup = new ToggleGroup();

    public static MenuBar createMenu()
    {
        schemeBright.setToggleGroup(schemeToggleGroup);
        schemeDark.setToggleGroup(schemeToggleGroup);

        scheme.getItems().setAll(schemeBright,schemeDark);

        radioSchemeEvents();


        //Initiale RadioEinstellung
        schemeBright.setSelected(true);
        setScheme(GUIConstants.SET_BRIGHT);

        menuBar.getMenus().setAll(scheme);
        return menuBar;
    }

    private static void radioSchemeEvents()
    {
        schemeBright.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    setScheme(GUIConstants.SET_BRIGHT);
                }
            }
        );

        schemeDark.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    setScheme(GUIConstants.SET_DARK);
                }
            }
        );
    }

    private static void setScheme (Boolean isBright)
    {
        Scene scene = StartPane.getScene();

        String brightThemePath = GUIConstants.BRIGHT_THEME_PATH;
        String darkThemePath = GUIConstants.DARK_THEME_PATH;

        scene.getStylesheets().remove(darkThemePath);
        scene.getStylesheets().remove(brightThemePath);

        if (isBright == true)
        {
            scene.getStylesheets().add(brightThemePath);
        }
        else
        {
            scene.getStylesheets().add(darkThemePath);
        }
    }
}
