package views;


import controller.AMessageController;
import controller.ClientController;
import controller.GUIController;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import models.*;
import java.io.IOException;

import java.util.Optional;

import models.interfaces.GUIConstants.StartPaneConstants;
import views.types.CenterPaneType;
import views.types.ErrorAlertType;

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
  public StartPane()
  {
    createStartBox();
  }
  
  /**
   * Erstellt das Startlayout
   */
  public static void createStartBox()
  {
    startBox.setAlignment(Pos.CENTER);
    startBox.getChildren().addAll
            (welcomeLabel, joinButton, hostButton);
    
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
            = JoinDialog.createDialog();
          
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
            
            try
            {
              Client client = new Client
                (
                  loginData.getHostAdress(),
                  loginData.getPort(),
                  loginData.getName()
                );
                ClientController.setClient(client);
    
                AMessageController.resetMessages();
                if(Client.startChat){
                  GUIController.setCenterPane
                          (CenterPaneType.CHAT);
                }

            } catch (NullPointerException e)
            {
              System.out.println("fail");
              ErrorAlertType.SERVER_REACH_FAILED.
                      getAlert().showAndWait();
            } catch (IOException ignored)
            {

            }
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
          Dialog<HostData> serverDialog
            = HostDialog.createDialog();
          
          Optional<HostData> result
            = serverDialog.showAndWait();
          
          /*
          Wenn die Nötigen Registrierdaten vorhanden sind,
          werden die Daten zum Erstellen eines Clients und
          eines Servers verwendet. Zudem wird die Message Liste
          zurückgesetzt und in das Chat-Layout gewechselt.
           */
          if (result.isPresent())
          {
            HostData hostData = result.get();
            
            Server server = new Server(hostData.getPort());
            
            server.start();
            try
            {
              Client client = new Client
              (
                hostData.getHostAdress(),
                hostData.getPort(),
                hostData.getName()
              );
              
              ClientController.setClient(client);
  
              System.out.println
                (StartPaneConstants.START_CHAT_STRING);
  
              AMessageController.resetMessages();
              if(Client.startChat && Server.startServer){
                GUIController.setCenterPane(CenterPaneType.CHAT);
              }

            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
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