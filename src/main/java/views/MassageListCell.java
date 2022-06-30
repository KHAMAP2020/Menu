package views;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import models.Message;

public class MassageListCell extends ListCell<Message>
{

    public MassageListCell()
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
            if(item.getMassageIsIncomming()==true)
            {
                item.getMassageBox().setAlignment(Pos.CENTER_LEFT);
            }
            else
            {
                item.getMassageBox().setAlignment(Pos.CENTER_RIGHT);
            }
            setGraphic(item.getMassageBox());
        }
    }
}