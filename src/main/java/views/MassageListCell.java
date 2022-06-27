package views;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListCell;
import javafx.scene.text.TextAlignment;
import models.Client;
import models.Massage;
import models.interfaces.GUIConstants;
import views.ChatPaneCreator;
public class MassageListCell extends ListCell<Massage>
{

    public MassageListCell()
    {

    }

    @Override
    protected void updateItem(Massage item, boolean empty)
    {
        // calling super here is very important - don't skip this!
        super.updateItem(item, empty);

        if (item == null)
        {
            setText(null);
        } else
        {
            HBox hBox = new HBox();
            setGraphic(item.getMassageBox());
            if(item.getMassageIsIncomming()==true)
            {
                item.getMassageBox().setAlignment(Pos.CENTER_LEFT);
            }
            else
            {
                item.getMassageBox().setAlignment(Pos.CENTER_RIGHT);
            }
        }
    }
}