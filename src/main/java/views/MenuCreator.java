package views;

import javafx.scene.control.*;
import javafx.event.*;
import models.interfaces.GUIConstants;
import javafx.scene.Scene;

public class MenuCreator
{
    private RadioMenuItem schemeBright = new RadioMenuItem(GUIConstants.SCHEME_BRIGHT_NAME);
    private RadioMenuItem schemeDark = new RadioMenuItem(GUIConstants.SCHEME_DARK_NAME);

    private MenuBar menuBar = new MenuBar();

    private Menu scheme =new Menu(GUIConstants.SCHEME_NAME);


    private ToggleGroup schemeToggleGroup = new ToggleGroup();

    private Scene scene = null;

    public MenuCreator(Scene scene)
    {
        this.scene = scene;
    }
    public MenuBar createMenu()
    {
        initialSettings();
        return this.menuBar;
    }

    private void setScheme (Boolean bright)
    {
        /*
        String brightThemePath = MenuCreator.class.getResource(GUIConstants.BRIGHT_THEME_PATH).toExternalForm();
        String darkThemePath = MenuCreator.class.getResource(GUIConstants.DARK_THEME_PATH).toExternalForm();
        if (bright == true)
        {
            this.scene.getStylesheets().remove(darkThemePath);
            this.scene.getStylesheets().add(brightThemePath);
        }
        else
        {
            this.scene.getStylesheets().remove(brightThemePath);
            this.scene.getStylesheets().add(darkThemePath);
        }


        String brightThemePath = MenuCreator.class.getResource(GUIConstants.BRIGHT_THEME_CSS).toExternalForm();
        String darkThemePath = MenuCreator.class.getResource(GUIConstants.DARK_THEME_CSS).toExternalForm();
        if (bright == true)
        {
            this.GuiCreator.getScene().getStylesheets().remove(darkThemePath);
            this.guiCreator.getScene().getStylesheets().add(brightThemePath);
        }
        else
        {
            this.guiCreator.getScene().getStylesheets().remove(brightThemePath);
            this.guiCreator.getScene().getStylesheets().add(darkThemePath);
        }*/
    }

    private void initialSettings ()
    {
        radioSchemeSettings();
        this.menuBar.getMenus().setAll(this.scheme);
    }

    private void radioSchemeSettings()
    {
        this.schemeBright.setSelected(true);
        setScheme(GUIConstants.SET_BRIGHT);

        this.schemeBright.setToggleGroup(this.schemeToggleGroup);
        this.schemeBright.setOnAction
                (
                    new EventHandler<ActionEvent>()
                    {
                        @Override public void handle(ActionEvent e)
                        {
                            setScheme(GUIConstants.SET_BRIGHT);
                        }
                    }
                );


        this.schemeDark.setToggleGroup(this.schemeToggleGroup);

        this.schemeDark.setOnAction
                (
                    new EventHandler<ActionEvent>()
                    {
                        @Override public void handle(ActionEvent e)
                        {
                            setScheme(GUIConstants.SET_DARK);
                        }
                    }
                );

        this.scheme.getItems().setAll(this.schemeBright,this.schemeDark);
    }

    public MenuBar getMenuBar()
    {
        return this.menuBar;
    }
}
