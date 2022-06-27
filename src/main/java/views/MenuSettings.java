package views;

import models.interfaces.GUIConstants;

public enum MenuSettings
{
    Start
    (
        //GUIConstants.START_RETURN_TO_START_ITEM
            false,false
    ),
    Chat
    (
        //GUIConstants.CHAT_RETURN_TO_START_ITEM
        true, true
    );

    private Boolean returnToStartItem = null;
    private Boolean returnMenu = null;

    MenuSettings(Boolean returnToStartItem, Boolean returnMenu)
    {
        this.returnToStartItem = returnToStartItem;
        this.returnMenu = returnMenu;
    }

    public Boolean getReturnToStartItem ()
    {
        return this.returnToStartItem;
    }

    public Boolean getReturnMenu()
    {
        return returnMenu;
    }
}
