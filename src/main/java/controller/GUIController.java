package controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.AServer;
import models.interfaces.GUIConstants.GUIControllerConstants;
import views.CenterPaneType;
import views.MenuSettings;
import views.Menu;
import views.StyleTypes;

import java.io.IOException;

/**
 * erstellt und kontrolliert die Grafische
 * Benutzeroberfläche
 *
 * @author A.Hoffmann 5137817
 */
public class GUIController
{
//-------------------------------------------------------------
//DatenFeld
  
  /**
   * Layout der primären Ebene
   */
  private static final BorderPane borderPane = new BorderPane();
  
  /**
   * Szene der primären Ebene
   */
  private static final Scene scene
    = new Scene
    (
      borderPane,
      GUIControllerConstants.SCENE_WIDTH,
      GUIControllerConstants.SCENE_HEIGHT
    );
  
  /**
   * Aktueller Zentruminhalt des Layouts
   */
  private static CenterPaneType currentCenterPane
    = GUIControllerConstants.INITIAL_CENTER_PANE;
  
  /**
   * Aktueller Stil der Grafischen Benutzeroberfläche
   */
  private static StyleTypes currentStyleTypes = StyleTypes.BRIGHT;
  
  /**
   * Menüleiste der Szene
   */
  private static MenuBar menuBar = null;

//-------------------------------------------------------------
//Methoden
  
  /**
   * setzt alle Initiale Einstellungen der
   * Benutzeroberfläche
   *
   * @param stage primäre Ebene
   */
  public static void initGUI(Stage stage)
  {
    menuBar = Menu.createMenu(stage);
    stageSettings(stage);
    addWindowCloseEvent();
    layoutSettings();
    stage.show();
  }
  
  /**
   * Setzt Ebenen Einstellungen der gegebenen Ebene
   *
   * @param stage die Einzustellende Ebene
   */
  public static void stageSettings(Stage stage)
  {
    stage.setScene(scene);
    stage.setResizable(GUIControllerConstants.RESIZABLE);
    stage.setTitle(GUIControllerConstants.STAGE_NAME);
    stage.setMinWidth(GUIControllerConstants.STAGE_MIN_WIDTH);
    stage.setMinHeight(GUIControllerConstants.STAGE_MIN_HEIGTH);
  }
  
  /**
   * Fügt Inhalte ins Layout ein
   */
  public static void layoutSettings()
  {
    borderPane.setTop(menuBar);
    setCenterPane(currentCenterPane);
  }
  
  /**
   * Fügt ein Ereignisfilter für Fensterschließanfragen
   * hinzu
   */
  public static void addWindowCloseEvent()
  {
    scene.getWindow().addEventFilter
    (
      WindowEvent.WINDOW_CLOSE_REQUEST,
      new EventHandler<WindowEvent>()
      {
        @Override
        public void handle(WindowEvent windowEvent)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          
          alert.setTitle("Lückenfüller");
          alert.setHeaderText("Wichtig");
          String s = "Server muss jetzt geschlosssen werden";
          alert.setContentText(s);
          alert.show();
          try
          {
            AMessageController.stopMessageController();
            AServer.serverSocket.close();
          }catch (IOException e){

          }
          ;


        }
      }
    );
  }
  
  //-----------------------------------------------------------
  //Getter-Methoden
  
  /**
   * Gibt die aktuelle Menüeinstellungen zurück
   *
   * @return die aktuelle Menüeinstellung
   */
  public static MenuSettings getCurrentMenuSettings()
  {
    return currentCenterPane.getMenuSettings();
  }
  
  //-----------------------------------------------------------
  //Setter-Methoden
  
  /**
   * Ändert die Zentruminhalte auf die gegebenen Inhalte
   *
   * @param centerPane der einzustellende Zentruminhalt
   */
  public static void setCenterPane(CenterPaneType centerPane)
  {
    borderPane.setCenter(centerPane.getPane());
    currentCenterPane = centerPane;
    Menu.updateSettings();
  }
  
  /**
   * Ändert den Stil der Szene auf den gegebenen Stil
   *
   * @param styleTypes der einzustellende Stil
   */
  public static void setStyle(StyleTypes styleTypes)
  {
    scene.getStylesheets().remove(currentStyleTypes.getPath());
    scene.getStylesheets().add(styleTypes.getPath());
    currentStyleTypes = styleTypes;
  }
}
