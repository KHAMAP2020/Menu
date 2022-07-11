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


import java.util.Optional;

import models.LoginData;
import models.interfaces.GUIConstants.StartPaneConstants;

/**
 * Das Startlayout, wenn das Programm ausgeführt wird
 *
 * @author A. Hoffmann 5137817 und P. Gohlke 5157842
 */
public class StartPane
{
//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Das Layout für den Start
   */
  private static final VBox startBox
  = new VBox(StartPaneConstants.START_V_BOX_SPACING);
  
  /**
   * Schaltfläche zum Beitreten eines Chats
   */
  private static final Button joinButton
  = new Button(StartPaneConstants.JOIN_BUTTON_NAME);
  
  /**
   * Schaltfläche zum Erstellen eines neuen Chats
   */
  private static final Button hostButton
  = new Button(StartPaneConstants.HOST_BUTTON_NAME);
  
  /**
   * Willkommensschriftzug
   */
  private static final Label welcomeLabel
  = new Label(StartPaneConstants.WELCOME_LABEL_STRING);

//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor des Startlayouts
   */
  StartPane()
  {
    createStartBox();
  }
  
  /**
   * Erstellt das Startlayout
   */
  public static void createStartBox()
  {
    startBox.setAlignment(Pos.CENTER);
    startBox.getChildren().addAll(welcomeLabel, joinButton, hostButton);
    
    buttonEventSettings();
  }
  
  //-----------------------------------------------------------
  //Settings
  
  /**
   *  Stellt Schaltflächen-Events ein
   */
  private static void buttonEventSettings()
  {
    joinButtonEventSettings();
    hostButtonEventSettings();
  }
  
  /**
   * Stellt das Event für die Aktivierung der Chat-beitreten-
   * Schaltflächen Event
   */
  private static void joinButtonEventSettings()
  {
    joinButton.setOnAction
    (
      new EventHandler<>()
      {
        @Override
        public void handle(ActionEvent actionEvent)
        {
          /*
          Startet den Registrierdialog für den erhalt der
          Nötigen Informationen, um einen Chat zu starten
           */
          Dialog<LoginData> clientDialog
            = RegisterDialog.createDialog();
          
          Optional<LoginData> result
            = clientDialog.showAndWait();
          
          /*
          Wenn die Nötigen Registrierdaten vorhanden sind,
          werden die Daten zum Erstellen eines Clients
          verwendet. Zudem wird die Message Liste zurückgesetzt
          und in das Chat-Layout gewechselt.
           */
          if (result.isPresent())
          {
            LoginData loginData = result.get();
            
            AClient aClient
              = new AClient
                (
                  loginData.getHostAdress(),
                  loginData.getPort(),
                  loginData.getName()
                );
            
            ClientController.setAClient(aClient);
            
            AMessageController.resetMessages();
            
            GUIController.setCenterPane(CenterPaneType.CHAT);
          
          }
        }
      }
    );
  }
  
  /**
   * Stellt das Event für die Aktivierung der Chat-erstellen-
   * Schaltflächen Event
   */
  private static void hostButtonEventSettings()
  {
    hostButton.setOnAction
    (
      new EventHandler<>()
      {
        @Override
        public void handle(ActionEvent actionEvent)
        {
           /*
          Startet den Registrierdialog für den erhalt der
          Nötigen Informationen, um einen Chat zu erstellen
           */
          Dialog<LoginData> serverDialog
            = RegisterDialog.createDialog();
          
          Optional<LoginData> result
            = serverDialog.showAndWait();
          
          /*
          Wenn die Nötigen Registrierdaten vorhanden sind,
          werden die Daten zum Erstellen eines Clients und
          eines Servers verwendet. Zudem wird die Message Liste
          zurückgesetzt und in das Chat-Layout gewechselt.
           */
          if (result.isPresent())
          {
            LoginData loginData = result.get();
            
            AServer server = new AServer(loginData.getPort());
            
            server.start();
          
            AClient aClient
              = new AClient
                (
                  loginData.getHostAdress(),
                  loginData.getPort(),
                  loginData.getName()
                );
            
            ClientController.setAClient(aClient);
          
            System.out.println
              (StartPaneConstants.WELCOME_LABEL_STRING);
            
            AMessageController.resetMessages();
            
            GUIController.setCenterPane(CenterPaneType.CHAT);
          }
        }
      }
    );
  }
  
  //-------------------------------------------------------
  //Getter
  
  /**
   * Gibt Layout des Starts zurück
   *
   * @return das Startlayout
   */
  public VBox getPane()
  {
    return startBox;
  }
}

