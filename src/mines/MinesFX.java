package mines;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MinesFX extends Application{
	
	Mines mines = new Mines(10, 10, 5);
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		//Loading fxml file into stackpane
		StackPane stackpane;
		MinesSBController controller;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MinesSB.fxml"));
			stackpane = loader.load();
			controller = loader.getController();
			Scene scene = new Scene(stackpane);
			stage.setTitle("The Amazing Mines Sweeper"); //The name of the scene
			stage.setScene(scene);
			GridPane gridPane = new GridPane();
			controller.init(stackpane, stage, gridPane);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		stage.show();
	}	
}
