package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import views.ErrorAlertType;

public class Main extends Application
{
  public static void main(String[] args)
  {

    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception
  {
    ErrorAlertType.REICIVE_MESSAGE_FAILED.
            getAlert().showAndWait();
    GUIController.initGUI(stage);
  }
}
