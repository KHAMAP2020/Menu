package views;

import models.interfaces.GUIConstants;

public enum MenuSettings
{
    Start
    (
        GUIConstants.START_RETURN_TO_START_ITEM
    ),
    Chat
    (
        GUIConstants.CHAT_RETURN_TO_START_ITEM
    );

    private Boolean returnToStartItem = null;

    MenuSettings(Boolean returnToStartItem)
    {
        this.returnToStartItem = returnToStartItem;
    }

    public boolean getReturnToStartItem ()
    {
        return this.returnToStartItem;
    }
}
