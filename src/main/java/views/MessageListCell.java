package views;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;

import models.Message;

/**
 * Listenzelle für Nachrichten
 *
 * @author A.Hoffmann 5137817
 */
public class MessageListCell extends ListCell<Message>
{
//---------------------------------------------------------
//Methoden

    /**
     * Konstruktor der Listenzelle
     */
    public MessageListCell()
    {
    }

    /**
     * überschreibt was geschieht, wenn etwas zur Liste
     * hinzugefügt wird
     *
     * @param message neue Nachricht
     *
     * @param empty ob die neue Nachricht leer ist
     */
    @Override
    protected void updateItem
    (
        Message message,
        boolean empty
    )
    {
        super.updateItem(message, empty);

        //wenn die Nachricht keinen wert hat, soll nichts
        //Angezeigt werden
        if (message == null)
        {
            setText(null);
        } else
        {
            //wenn die Nachricht eingehend isr soll sie
            //links ausgerichtet werden. Ansonsten wird sie
            //rechts ausgerichtet
            if(message.getIsIncomming()==true)
            {
                message.getHBox()
                .setAlignment(Pos.CENTER_LEFT);
            }
            else
            {
                message.getHBox()
                .setAlignment(Pos.CENTER_RIGHT);
            }
            //Zeigt die Nachricht als Grafik an
            setGraphic(message.getHBox());
        }
    }
}