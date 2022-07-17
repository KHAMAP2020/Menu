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

    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception
  {
    GUIController.initGUI(stage);
  }
}
