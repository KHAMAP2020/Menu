package models;

import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import models.interfaces.GUIConstants;

import java.io.IOException;

public class Message
{
    private Boolean isIncomming = null;

    private TextFlow textFlow = new TextFlow();

    private Text text = new Text();

    private HBox hBox = new HBox();

    public Message(String text, Boolean isIncomming)
    {
        this.isIncomming = isIncomming;
        this.text.setText(text);
        textFlow.setMaxWidth(GUIConstants.MASSAGE_MAX_WIDTH);
        hBox.getChildren().add(textFlow);
        textFlow.getChildren().add(this.text);
    }

    public Message()
    {

    }

    public  Text getText()
    {
        return this.text;
    }

    public Boolean getIsIncomming()
    {
        return this.isIncomming;
    }

    public TextFlow getTextFlow()
    {
        return this.textFlow;
    }

    public HBox getHBox()
    {
        return this.hBox;
    }

    public void sendMessage() throws IOException
    {



    }
}
