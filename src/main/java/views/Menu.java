package views;

import controller.MessageController;
import javafx.stage.Stage;
import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import models.Client;
import models.Server;
import models.interfaces.GUIConstants.MenuConstants;
import views.types.CenterPaneType;
import views.types.StyleTypes;

/**
 * Menüleiste für das Chatlayout und das Startlayout
 *
 * @author A.Hoffmann 5137817
 */
public class Menu
{
//-------------------------------------------------------------
//Datenfeld
  
  /**
   * Ebene für einstellung des Stils
   */
  private static Stage stage = null;
  
  /**
   * Die Menüleiste
   */
  private static final MenuBar menuBar = new MenuBar();
  
  //-----------------------------------------------------------
  //Stil Menü
  
  /**
   * Stil Menü in dem der Stil des Fensters ausgewählt wird
   */
  private static final javafx.scene.control.Menu schemeMenu
    = new javafx.scene.control.Menu(MenuConstants.SCHEME_NAME);
  
  /**
   * Heller Stil Menü-Auswahlschaltfläche
   */
  private static final RadioMenuItem schemeBrightItem
    = new RadioMenuItem(MenuConstants.SCHEME_BRIGHT_NAME);
  
  /**
   * Dunkler Stil Menü-Auswahlschaltfläche
   */
  private static final RadioMenuItem schemeDarkItem
    = new RadioMenuItem(MenuConstants.SCHEME_DARK_NAME);
  
  /**
   * Auswahlgruppe für die Stil-Menüauswahlflächen
   */
  private static final ToggleGroup schemeToggleGroup
    = new ToggleGroup();
  
  //-----------------------------------------------------------
  //Verlassen Menü
  
  /**
   * Menü zum Verlassen der Ebene und des Chats
   */
  private static final javafx.scene.control.Menu returnMenu
    = new javafx.scene.control.Menu(MenuConstants.END_CHAT_MENU);
  
  /**
   * Menüschaltfläche zum Zurückkehren zum Start
   */
  private static final MenuItem returnToStartItem
    = new MenuItem(MenuConstants.RETURN_TO_START_BUTTON_STRING);
  
  /**
   * Menüschaltfläche zum Beenden der Ebene
   */
  private static final MenuItem endStageItem
    = new MenuItem(MenuConstants.END_STAGE_BUTTON_STRING);
  
//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor des Menüs
   *
   * @param newStage Ebene, dessen Stil eingestellt werden soll
   * @return die Menüleiste
   */
  public static MenuBar createMenu(Stage newStage)
  {
    stage = newStage;
    schemeBrightItem.setToggleGroup(schemeToggleGroup);
    schemeDarkItem.setToggleGroup(schemeToggleGroup);
    
    schemeMenu.getItems()
      .setAll(schemeBrightItem, schemeDarkItem);
    returnMenu.getItems()
      .addAll(endStageItem, returnToStartItem);
    itemEventSettings();
    
    //Initiale RadioEinstellung
    schemeBrightItem
      .setSelected(MenuConstants.SCHEME_BRIGHT_INITIAL_STATUS);
    schemeDarkItem
      .setSelected(MenuConstants.SCHEME_DARK_INITIAL_STATUS);
    updateSettings();
    menuBar.getMenus().setAll(schemeMenu, returnMenu);
    
    return menuBar;
  }
  
  /**
   * Aktualisiert die Menüeinstellungen
   */
  public static void updateSettings()
  {
    returnToStartItem.setVisible
      (
        GUIController.getCurrentMenuSettings()
          .getReturnToStartItem()
      );
  }
  
  //-----------------------------------------------------------
  //Settings
  /**
   * Stellt alle Ereignisse für die einzelnen Menüelemente ein
   */
  private static void itemEventSettings()
  {
    schemeBrightEventSetting();
    schemeDarkEventSetting();
    returnToStartEventSetting();
    setEndStageEvent();
  }
  
  /**
   * Setzt das Ereignis für das helle-Stil-Menüelement fest
   */
  private static void schemeBrightEventSetting()
  {
    schemeBrightItem.setOnAction
    (
      new EventHandler<>()
      {
        @Override
        public void handle(ActionEvent e)
        {
          GUIController.setStyle(StyleTypes.BRIGHT);
        }
      }
    );
  }
  
  /**
   * Setzt das Ereignis für das dunkle-Stil-Menüelement fest
   */
  private static void schemeDarkEventSetting()
  {
    schemeDarkItem.setOnAction
    (
      new EventHandler<>()
      {
        @Override
        public void handle(ActionEvent e)
        {
          GUIController.setStyle(StyleTypes.DARK);
        }
      }
    );
  }
  
  /**
   * Setzt das Ereignis für das zurück-zum-Start-Menüelement
   * fest
   */
  private static void returnToStartEventSetting()
  {
    returnToStartItem.setOnAction
    (
      new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent event)
        {
          Client.closeEverything();
          MessageController.stopMessageController();
          Server.closeEverything();
          GUIController.setCenterPane(CenterPaneType.START);
        }
      }
    );
  }
  
  /**
   * Setzt das Ereignis für das Ebene-Verlassen-Menüelement
   * fest
   */
  private static void setEndStageEvent()
  {
    endStageItem.setOnAction
    (
      new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent event)
        {
          stage.getScene().getWindow().fireEvent
          (
            new WindowEvent
            (
              stage.getScene().getWindow(),
              WindowEvent.WINDOW_CLOSE_REQUEST
            )
          );
        }
      }
    );
  }
}
