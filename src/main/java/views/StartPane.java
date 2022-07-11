package views;


import controller.AMessageController;
import controller.ClientController;
import controller.GUIController;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import models.AClient;
import models.AServer;
import models.interfaces.GUIConstants;


import java.util.Optional;

import models.LoginData;
/*
@author Philipp Gohlke 5157842
 */
public class StartPane
{
  private static final VBox startBox = new VBox(GUIConstants.START_V_BOX_SPACING);
  private static final Button joinButton = new Button(GUIConstants.JOIN_BUTTON_NAME);
  private static final Button hostButton = new Button(GUIConstants.HOST_BUTTON_NAME);
  private static final Label welcomeLabel = new Label(GUIConstants.WELCOME_LABEL_STRING);

  StartPane()
  {
    createStartBox();
  }
  
  public static void createStartBox()
  {
    startBox.setAlignment(Pos.CENTER);
    startBox.getChildren().addAll(welcomeLabel, joinButton, hostButton);
    
    setButtonEvents();
  }
  
  private static void setButtonEvents()
  {
    joinButton.setOnAction(new EventHandler<>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        Dialog<LoginData> clientDialog = RegisterDialog.createDialog();
        Optional<LoginData> result = clientDialog.showAndWait();
        if (result.isPresent())
        {
          LoginData loginData = result.get();
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Lückenfüller");
          alert.setHeaderText("Geschafft!");
          String s = "Jetzt müssen die eben angegebenen Infos noch verarbeitet werden. ";
          alert.setContentText(s);
          // alert.ashow();
          AClient aClient = new AClient(loginData.getHostAdress(), loginData.getPort(), loginData.getName());
          ClientController.setAClient(aClient);
          AMessageController.resetMessages();
          GUIController.setCenterPane(CenterPaneType.CHAT);

        }
      }
    });
    
    hostButton.setOnAction(new EventHandler<>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        Dialog<LoginData> serverDialog = RegisterDialog.createDialog();
        Optional<LoginData> result = serverDialog.showAndWait();
        if (result.isPresent())
        {
          LoginData loginData = result.get();
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Lückenfüller");
          alert.setHeaderText("Geschafft!");
          String s = "Jetzt müssen die eben angegebenen Infos noch verarbeitet werden. ";
          alert.setContentText(s);
          //alert.show();
          AServer server = new AServer(loginData.getPort());
          server.start();
          
          AClient aClient = new AClient(loginData.getHostAdress(), loginData.getPort(), loginData.getName());
          ClientController.setAClient(aClient);

          System.out.println("chat startet");
          AMessageController.resetMessages();
          GUIController.setCenterPane(CenterPaneType.CHAT);
        }
      }
    });
  }
  
  public VBox getPane()
  {
    return startBox;
  }
}

