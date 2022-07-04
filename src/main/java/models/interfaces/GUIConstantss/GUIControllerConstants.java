package models.interfaces.GUIConstantss;

import views.CenterPaneType;

/**
 * Fenster-, Szenen- und Layout-Konstanten
 *
 * @author A.Hoffmann 5137817
 */
public interface GUIControllerConstants
{
    /**
     * Name der Stage
     */
    public final String STAGE_NAME = "Name der Stage";

    /**
     * Initiale Breite der Szene
     */
    public final int SCENE_WIDTH = 600;

    /**
     * Initiale Höhe der Szene
     */
    public final int SCENE_HEIGHT = 400;

    /**
     * Minimale Fensterbreite
     */
    public final int STAGE_MIN_WIDTH = 600;

    /**
     * Minimale Fensterhöhe
     */
    public final int STAGE_MIN_HEIGTH = 400;

    /**
     * Ob die größe des Fensters veränderbar ist
     */
    public final boolean RESIZABLE = true;

    /**
     * Initialer Zentruminhalt des Layouts
     */
    public final CenterPaneType INITIAL_CENTER_PANE
    = CenterPaneType.START;
}
