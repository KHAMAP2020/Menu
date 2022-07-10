package models;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import models.interfaces.GUIConstants;
/*
@author Philipp Gohlke 5157842
 */
public class Message
{
  private Boolean isIncomming = null;
  
  private TextFlow textFlow = new TextFlow();
  
  private Text text = new Text();
  
  private HBox hBox = new HBox();
  
  public Message
  (
    String text,
    Boolean isIncomming,
    ReadOnlyDoubleProperty maxWidth
  )
  {
    textFlow.setMaxWidth
    (
      maxWidth.get() * GUIConstants.MESSAGE_WIDTH_SCALE
    );
    
    maxWidth.addListener
    (
      new ChangeListener<Number>()
      {
        @Override
        public void changed
        (
          ObservableValue<? extends Number> observableValue,
          Number oldNumber,
          Number newNumber
        )
        {
          textFlow.setMaxWidth
          (
            (Double) newNumber
            * GUIConstants.MESSAGE_WIDTH_SCALE
          );
        }
      }
    );
    
    this.isIncomming = isIncomming;
    this.text.setText(text);
    hBox.getChildren().add(textFlow);
    textFlow.getChildren().add(this.text);
  }
  
  public Text getText()
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
}
