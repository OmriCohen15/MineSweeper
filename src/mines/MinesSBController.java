package mines;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MinesSBController {
	private StackPane stackPane;
	private Stage stage;
	private GridPane gridPane;
	private Mines board;
	private int width, height, minesCount;
	private MinesButton[][] myButtons;
	
	
	//This methode will save the parameter from the MinesFX file so we can use them in controller
	public void init(StackPane stackPane, Stage stage, GridPane gridPane) {
		this.stackPane = stackPane;
		this.stage = stage;
		this.gridPane = gridPane;
	}
	
	private void setData() {
		/*save the size and mines data in local class atributes in order to use them later and create new 
		Mines instance*/
		this.width = Integer.valueOf(widthtxt.getText());
		this.height = Integer.valueOf(heighttxt.getText());
		this.minesCount = Integer.valueOf(minestxt.getText());
	}
	
    @FXML
    private Button Reset;

    @FXML
    private TextField heighttxt;

    @FXML
    private TextField minestxt;

    @FXML
    private TextField widthtxt;
    
    class MyEvent implements EventHandler<MouseEvent> {  	
    	
		public MyEvent() {
		}

		@Override
		public void handle(MouseEvent event) {
			MinesButton but = (MinesButton) event.getSource();
			boolean gameOver = false;
			int k=0;
			String s = new String();
			String s1 = new String();
			if(event.getButton().toString().equals("PRIMARY")) { //If left mouse clicked
				if(!(board.open(but.getI(), but.getJ()))) {
					board.setShowAll(true);
					gameOver = true;
				}
					s = board.toString();
					for (int i = 0; i < height; i++) {
						for (int j = 0; j < width; j++) {
							s1 = s.charAt(k) + "";
							if (s1.equals(" ")) {
								putImage(new ImageView("imgs/black.png"), myButtons[i][j]);
							}
							if (s1.equals("X")) {
								putImage(new ImageView("imgs/mine.png"), myButtons[i][j]);
							}
							if (s1.equals("1")) {
								putImage(new ImageView("imgs/1.png"), myButtons[i][j]);
							}
							if (s1.equals("2")) {
								putImage(new ImageView("imgs/2.png"), myButtons[i][j]);
							}
							if (s1.equals("3")) {
								putImage(new ImageView("imgs/3.png"), myButtons[i][j]);
							}
							if (s1.equals("4")) {
								putImage(new ImageView("imgs/4.png"), myButtons[i][j]);
							}
							if (s1.equals("5")) {
								putImage(new ImageView("imgs/5.png"), myButtons[i][j]);
							}
							if (s1.equals("6")) {
								putImage(new ImageView("imgs/6.png"), myButtons[i][j]);
							}
							if (s1.equals("7")) {
								putImage(new ImageView("imgs/7.png"), myButtons[i][j]);
							}
							if (s1.equals("8")) {
								putImage(new ImageView("imgs/8.png"), myButtons[i][j]);
							}
							k++;
						}
						k++;
						
					}
					if(gameOver) {
						GameOver();
					}
					if(board.isDone()) {
						GameWin();
					}
					
					
			}
			else if(event.getButton().toString().equals("SECONDARY")){ //If right mouse clicked
				board.toggleFlag(but.getI(), but.getJ());
				if(but.isRightClick()) {
					putImage(new ImageView("imgs/flag.png"), myButtons[but.getI()][but.getJ()]);
					but.setRightClick(false);
				}
				else {
					but.setGraphic(null);
					but.setRightClick(true);
				}
			}					
		}
	}
    
    void GameOver() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("You lost ");
		alert.setHeaderText("You Lost");
		alert.setContentText("try again by pressing Reset");
		alert.showAndWait();
    }
    
    void GameWin() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Congratulations! ");
		alert.setHeaderText("You Won!");
		alert.showAndWait();
    }
    
    @FXML
    void ResetPressed(MouseEvent event) {
    	stackPane.getChildren().remove(gridPane); //Reset the stackpane for new game
    	gridPane.getChildren().clear();
    	setData();
    	this.board = new Mines(height, width, minesCount);
    	myButtons = new MinesButton[height][width];
		//The loops add buttons to the gridpane
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				MinesButton button = new MinesButton(i, j);
				button.setMinSize(50, 50);
				//For every button create mouse event to handle click
				button.setOnMouseClicked(new MyEvent());
				myButtons[i][j] = button;
				gridPane.add(button, j, i);
			}
		}
		stackPane.getChildren().add(gridPane);
		//The next lines resize the scene according to the gridpane size
		stackPane.setPrefHeight(height*50 + 10);
		stackPane.setPrefWidth(width*50 + 175);
		StackPane.setMargin(gridPane, new Insets(0, 0, 0, 175));
		gridPane.setMaxWidth(width*50);
		gridPane.setMinWidth(width*50);
		gridPane.setMaxHeight(height*50);
		gridPane.setMinHeight(height*50);
		gridPane.setAlignment(Pos.CENTER_RIGHT);
		stage.sizeToScene();
		//stage.setResizable (false);
    }
    
	private void putImage(ImageView image, MinesButton b) {
		image.setFitHeight(30);
		image.setFitWidth(30);
		b.setGraphic(image);
	}
}
