package simpleFX;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Singers extends Application {
	private int i; 
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(CreateRoot());
		stage.setTitle("Voting Machine"); //The name of the scene
		stage.setScene(scene);
		stage.show();
	}
	
	private VBox CreateRoot() {
		VBox root = new VBox(10);
		GridPane gridPane = new GridPane(); //The gridpane will represent the two buttons on the first line of the VBox
		Label label = new Label("0");
		Button ofraButton = new Button("Ofra Haza");
		Button yardenaButton = new Button("Yardena Arazi");
		root.setPadding(new Insets(10)); //Set the gap from the scene bounderies
		gridPane.add(ofraButton, 0, 0);
		gridPane.add(yardenaButton, 1, 0);
		gridPane.setHgap(20); //Set the gap between the two buttons
		label.setAlignment(Pos.CENTER);
		label.setMinHeight(35); 
 		label.setMaxWidth(Double.MAX_VALUE); //Wrap the label according to the size of the scene (if resize)
		label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), null)));
		root.getChildren().addAll(gridPane, label);
		
		class LabelIncreaser implements EventHandler<javafx.event.ActionEvent>{
			@Override
			public void handle(javafx.event.ActionEvent event){
				Button clicked = (Button)event.getSource();
				if(clicked.equals(yardenaButton)) //Check if the button that clicked is "yardenaButton"
					i--;
				else 
					i++;
				label.setText("" + i); //Change the text in the lable after action.
				
			}
		}
		ofraButton.setOnAction(new LabelIncreaser());
		yardenaButton.setOnAction(new LabelIncreaser());
		return root;
	}
	
	
}
