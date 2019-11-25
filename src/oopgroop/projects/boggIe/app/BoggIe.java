package oopgroop.projects.boggIe.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oopgroop.projects.boggIe.api.Board;

public final class BoggIe extends Application {

	public BoggIe() {
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
		Board board = new Board(4, 4);
		board.getRoot().setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
		primaryStage.setScene(new Scene(board.getRoot()));
	}

}
