package views;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import models.Message;

public class MessageListCell extends ListCell<Message>
{

    public MessageListCell()
    {

    }

    @Override
    protected void updateItem(Message item, boolean empty)
    {
        // calling super here is very important - don't skip this!
        super.updateItem(item, empty);

        if (item == null)
        {
            setText(null);
        } else
        {

            if(item.getIsIncomming()==true)
            {

                item.getHBox().setAlignment(Pos.CENTER_LEFT);
            }
            else
            {

                item.getHBox().setAlignment(Pos.CENTER_RIGHT);
            }
            setGraphic(item.getHBox());
        }
    }
}