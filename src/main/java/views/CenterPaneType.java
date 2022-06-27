package views;

import javafx.scene.layout.VBox;

public enum CenterPaneType
{
    START
    (
        new StartPane().getPane(),
        MenuSettings.Start
    ),
   CHAT
    (
       new ChatPane().getPane(),
       MenuSettings.Chat
    );

    private VBox pane= null;
    private MenuSettings menuSettings = null;

    CenterPaneType(VBox pane, MenuSettings menuSettings)
    {
        this.pane = pane;
        this.menuSettings = menuSettings;
    }

    public VBox getPane()
    {
        return this.pane;
    }

    public MenuSettings getMenuSettings()
    {
        return this.menuSettings;
    }
}
