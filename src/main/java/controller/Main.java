package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author P. Gohlke 5157842
 */
public class Main extends Application
{
  public static void main(String[] args)
  {
    /*
    Hier ist unsere Main Methode, welche das ganze Programm
    startet.
     */
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception
  {
    /*
    führt die InitGui Methode im GuiController aus, welche
    die stage erstellt und anzeigt.
     */
    GUIController.initGUI(stage);
  }
}
