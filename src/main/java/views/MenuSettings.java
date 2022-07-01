package views;

import models.interfaces.GUIConstants;

public enum MenuSettings
{
    Start
    (
        //GUIConstants.START_RETURN_TO_START_ITEM
            false
    ),
    Chat
    (
        //GUIConstants.CHAT_RETURN_TO_START_ITEM
        true
    );

    private Boolean returnToStartItem = null;

    MenuSettings(Boolean returnToStartItem)
    {
        this.returnToStartItem = returnToStartItem;
    }

    public Boolean getReturnToStartItem ()
    {
        return this.returnToStartItem;
    }
}
