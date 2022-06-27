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

public class StartMenu
{
    private RadioMenuItem schemeBright = new RadioMenuItem(GUIConstants.SCHEME_BRIGHT_NAME);
    private RadioMenuItem schemeDark = new RadioMenuItem(GUIConstants.SCHEME_DARK_NAME);

    private MenuBar menuBar = new MenuBar();

    private Menu schemeMenu =new Menu(GUIConstants.SCHEME_NAME);

    private ToggleGroup schemeToggleGroup = new ToggleGroup();
    private Scene scene = null;
    public StartMenu(Scene scene)
    {
        this.scene = scene;
        schemeBright.setToggleGroup(schemeToggleGroup);
        schemeDark.setToggleGroup(schemeToggleGroup);

        schemeMenu.getItems().setAll(schemeBright,schemeDark);

        setRadioSchemeEvents();

        //Initiale RadioEinstellung
        schemeBright.setSelected(true);

        setStylesheet(GUIConstants.SET_BRIGHT);

        menuBar.getMenus().setAll(schemeMenu);
    }

    private void setRadioSchemeEvents()
    {
        schemeBright.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    setStylesheet(GUIConstants.SET_BRIGHT);
                }
            }
        );

        schemeDark.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    setStylesheet(GUIConstants.SET_DARK);
                }
            }
        );
    }

    private void setStylesheet(Boolean isBright)
    {
        scene = StartSceneCreator.getScene();

        String brightThemePath = GUIConstants.BRIGHT_THEME_PATH;
        String darkThemePath = GUIConstants.DARK_THEME_PATH;

        scene.getStylesheets().remove(darkThemePath);
        scene.getStylesheets().remove(brightThemePath);

        if (isBright == true)
        {
            //GUIController.setStyle(GUIConstants.BRIGHT_THEME_PATH);
            scene.getStylesheets().add(brightThemePath);
        }
        else
        {
            scene.getStylesheets().add(darkThemePath);
            //GUIController.setStyle(GUIConstants.DARK_THEME_PATH);
        }
    }

    public MenuBar getMenu()
    {
        return menuBar;
    }
}
