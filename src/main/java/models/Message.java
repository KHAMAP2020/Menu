package models;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import models.interfaces.GUIConstantss.MessageConstants;

/**
 * Nachricht, die in der Listview des Chats dargestellt
 * werden soll
 *
 * @author A.Hoffmann 5137817
 */
public class Message
{
//-------------------------------------------------------------
//Datenfelder
  
  /**
   * Ob die Nachricht eingehend oder ausgehen ist un ob
   * somit die Nachricht Links oder Rechts eingerückt werden
   * muss.
   */
  private Boolean receivingMessage = null;
  
  /**
   * Textlayout, der den Text beinhalten
   */
  private TextFlow textFlow = new TextFlow();
  
  /**
   * Text der Nachricht
   */
  private Text text = new Text();
  /**
   * Horizontale Box, die das Textlayout beinhalten und
   * ausrichten soll.
   */
  private HBox hBox = new HBox();
 
//-------------------------------------------------------------
//Methoden
  
  /**
   * Konstruktor der Nachricht
   *
   * @param text Text der Nachricht
   * @param receivingMessage Ob die Nachricht eingehen oder
   *                         ausgehend ist
   * @param maxWidth Maximale Breite der Nachricht in
   *                 Abhängigkeit von der Umgebung der Nachricht
   */
  public Message
  (
    String text,
    Boolean receivingMessage,
    ReadOnlyDoubleProperty maxWidth
  )
  {
    this.receivingMessage = receivingMessage;
    this.text.setText(text);
    textFlow.getChildren().add(this.text);
    hBox.getChildren().add(textFlow);
    maxWidthSettings(maxWidth);
  }
  
  //-----------------------------------------------------------
  //Settings
  
  /**
   * Stellt die maximale breite des Textes in Abhängigkeit
   * des gegebenen nur auszulesenden Breiteneigenschaft ein
   *
   * @param maxWidth nur auszulesende Breiteneigenschaft
   */
  private void maxWidthSettings(ReadOnlyDoubleProperty maxWidth)
  {
    //Für die Initiale Maximale Breite des Textes
    textFlow.setMaxWidth
      (
        maxWidth.get() * MessageConstants.MESSAGE_WIDTH_SCALE
      );
  
    //Maximale Breite, wenn sich die Fensterbreite ändert
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
                * MessageConstants.MESSAGE_WIDTH_SCALE
            );
        }
      }
    );
  }
  
  //-----------------------------------------------------------
  //Getter
  
  /**
   * Gibt zurück, ob die Nachricht eingehen oder Ausgehen ist
   * @return ob die Nachricht eingehen oder Ausgehen ist
   */
  public Boolean getReceivingMessage()
  {
    return this.receivingMessage;
  }
  
  /**
   * Gibt die horizontale Box zurück
   * @return
   */
  public HBox getHBox()
  {
    return this.hBox;
  }
}
