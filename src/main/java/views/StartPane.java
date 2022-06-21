package views;


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

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import models.LoginData;

public class StartPane
{
  private static VBox startBox = new VBox(GUIConstants.START_V_BOX_SPACING);
  private static Button joinButton = new Button(GUIConstants.JOIN_BUTTON_NAME);
  private static Button hostButton = new Button(GUIConstants.HOST_BUTTON_NAME);
  private static Label welcomeLabel = new Label(GUIConstants.WELCOME_LABEL_STRING);
   static Socket clientSocket;

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
    joinButton.setOnAction(new EventHandler<ActionEvent>()
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
          AClient aClient = new AClient(loginData.getServerName(),loginData.getPort(), loginData.getName());
          ClientController.setAClient(aClient);
          GUIController.setCenterPane(CenterPaneType.CHAT);
          
        }
      }
    });
    
    hostButton.setOnAction(new EventHandler<ActionEvent>()
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
          GUIController.setCenterPane(CenterPaneType.CHAT);
          AClient aClient = new AClient(loginData.getServerName(),loginData.getPort(), loginData.getName());
          ClientController.setAClient(aClient);
          System.out.println("chat startet");


        }
      }
    });
  }
  
  public VBox getPane()
  {
    return startBox;
  }
}

