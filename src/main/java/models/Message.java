package models;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import models.interfaces.GUIConstants;
/**
@author A.Hoffmann 5137817
 */
public class Message
{
  private final Boolean receivingMessage;
  
  private final TextFlow textFlow = new TextFlow();

  private final HBox hBox = new HBox();
  
  public Message
  (
    String text,
    Boolean receivingMessage,
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
    
    this.receivingMessage = receivingMessage;
    Text text1 = new Text();
    text1.setText(text);
    hBox.getChildren().add(textFlow);
    textFlow.getChildren().add(text1);
  }

  public Boolean getReceivingMessage()
  {
    return this.receivingMessage;
  }

  public HBox getHBox()
  {
    return this.hBox;
  }
}
