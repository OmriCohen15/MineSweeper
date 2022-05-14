//Defining the behavior of the scene 
package SimpleFx2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Singers2Controller {
	int i=0;
	
    @FXML
    private GridPane GridPane;

    @FXML
    private Label Label;

    @FXML
    private Button OfraHaza;

    @FXML
    private VBox VBox;

    @FXML
    private Button YardenaArazi;

    @FXML
    void handle(MouseEvent event) {
    	Button clicked = (Button)event.getSource();
		if(clicked.equals(YardenaArazi)) //Check if the button that clicked is "yardenaButton"
			i--;
		else 
			i++;
		Label.setText("" + i); //Change the text in the lable after action.
    }

}
