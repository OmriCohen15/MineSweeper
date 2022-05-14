package SimpleFx2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Singers2Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		//Loading fxml file into vbox
		VBox vbox;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Singers2.fxml"));
			vbox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene scene = new Scene(vbox);
		stage.setTitle("Voting Machine2"); //The name of the scene
		stage.setScene(scene);
		stage.show();
	}
}

