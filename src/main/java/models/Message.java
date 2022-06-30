package models;

import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import models.interfaces.GUIConstants;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Message
{
    private Boolean massageIsIncomming = null;

    private TextFlow massageFlow = new TextFlow();

    private Text massageText = new Text();

    private HBox massageBox = new HBox();

    public Message(String massageText, Boolean massageIsIncomming)
    {
        this.massageIsIncomming = massageIsIncomming;
        this.massageText.setText(massageText);
        massageFlow.setMaxWidth(GUIConstants.MASSAGE_MAX_WIDTH);
        massageBox.getChildren().add(massageFlow);
        massageFlow.getChildren().add(this.massageText);
    }

    public Message()
    {

    }

    public  Text getMassageText()
    {
        return this.massageText;
    }

    public Boolean getMassageIsIncomming()
    {
        return this.massageIsIncomming;
    }

    public TextFlow getMassageFlow()
    {
        return this.massageFlow;
    }

    public HBox getMassageBox()
    {
        return this.massageBox;
    }

    public void sendMessage() throws IOException
    {



    }
}
