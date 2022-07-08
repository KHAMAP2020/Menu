package views;

import javafx.stage.Stage;
import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import models.interfaces.GUIConstants;

public class StartMenu
{
  private static Stage stage = null;
  private static RadioMenuItem schemeBrightItem
    = new RadioMenuItem(GUIConstants.SCHEME_BRIGHT_NAME);
  private static RadioMenuItem schemeDarkItem
    = new RadioMenuItem(GUIConstants.SCHEME_DARK_NAME);
  
  private static MenuBar menuBar = new MenuBar();
  
  private static Menu schemeMenu
    = new Menu(GUIConstants.SCHEME_NAME);
  private static MenuItem returnToStartItem
    = new MenuItem(GUIConstants.RETURN_TO_START_BUTTEN_STRING);
  private static MenuItem endStageItem
    = new MenuItem(GUIConstants.END_STAGE_BUTTON_STRING);
  private static Menu returnMenu
    = new Menu(GUIConstants.END_CHAT_MENU);
  private static ToggleGroup schemeToggleGroup
    = new ToggleGroup();
  
  public static MenuBar createMenu(Stage newStage)
  {
    stage = newStage;
    schemeBrightItem.setToggleGroup(schemeToggleGroup);
    schemeDarkItem.setToggleGroup(schemeToggleGroup);
    
    schemeMenu.getItems()
      .setAll(schemeBrightItem, schemeDarkItem);
    returnMenu.getItems()
      .addAll(endStageItem, returnToStartItem);
    setItemEvents();
    
    //Initiale RadioEinstellung
    schemeBrightItem
      .setSelected(GUIConstants.SCHEME_BRIGHT_INITIAL_STATUS);
    schemeDarkItem
      .setSelected(GUIConstants.SCHEME_DARK_INITIAL_STATUS);
    updateSettings();
    menuBar.getMenus().setAll(schemeMenu, returnMenu);
    
    return menuBar;
  }
  
  private static void setItemEvents()
  {
    setSchemeBrightEvent();
    setSchemeDarkEvents();
    setReturnToStartEvent();
    setEndStageEvent();
  }
  
  private static void setSchemeBrightEvent()
  {
    schemeBrightItem.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent e)
      {
        GUIController.setStyle(Style.BRIGHT);
      }
    });
    
  }
  
  private static void setSchemeDarkEvents()
  {
    schemeDarkItem.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent e)
      {
        GUIController.setStyle(Style.DARK);
      }
    });
  }
  
  private static void setReturnToStartEvent()
  {
    returnToStartItem.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        
        GUIController.setCenterPane(CenterPaneType.START);
      }
    });
  }
  
  private static void setEndStageEvent()
  {
    endStageItem.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        stage.getScene().getWindow().fireEvent(new WindowEvent(stage.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
      }
    });
  }
  
  public static void updateSettings()
  {
    returnToStartItem.setVisible(GUIController.getCurrentMenuSettings().getReturnToStartItem());
  }
}
