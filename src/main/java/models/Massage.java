package models;

import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import models.interfaces.GUIConstants;

public class Massage
{
    private Boolean massageIsIncomming = null;

    private TextFlow massageFlow = new TextFlow();

    private Text massageText = new Text();

    private HBox massageBox = new HBox();

    public Massage (String massageText, Boolean massageIsIncomming)
    {
        this.massageBox.getChildren().add(massageFlow);
        this.massageText.setText(massageText);
        this.massageText.setWrappingWidth(GUIConstants.MASSAGE_MAX_WIDTH);
        this.massageFlow.getChildren().add(this.massageText);
        this.massageIsIncomming = massageIsIncomming;
    }

    public Text getMassageText()
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
}
