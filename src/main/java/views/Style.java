package views;

import models.interfaces.GUIConstants;

public enum Style
{
    BRIGHT(GUIConstants.BRIGHT_THEME_PATH),

    DARK(GUIConstants.DARK_THEME_PATH);

    private String path = null;
    Style(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return this.path;
    }
}
