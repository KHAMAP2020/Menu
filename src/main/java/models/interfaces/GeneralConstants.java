package models.interfaces;

/**
 * Hier sind alle allgemeinen Kontsanten des Programmns
 * hinterlegt
 *
 * @author A.Hoffmann 5137817
 */
public interface GeneralConstants
{
    /**
     * Wird verwendet für die While-Scheifen um zu definieren
     * ob die While-Schleife starten oder stoppen soll
     */
    public final boolean LOOP_START = true;

    public final boolean LOOP_STOP = false;
    
    /**
     * Kleinste mögliche Portnummer
     */
    public final int portMinValue = 0;
    
    /**
     * Größte mögliche Portnummer
     */
    public final int portMaxValue = 30000;

    /**
     * Wird beim erstellen des Servers als Standard für den
     * Serversocket verwendet.
     */
    public final String HOST_ADRESS = "localhost";
}
