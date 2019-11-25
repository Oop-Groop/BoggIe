package oopgroop.projects.boggIe.app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

		class Thing {// The controller.
			public @FXML Text playerName;
			public @FXML TextField input;
			public @FXML Button submit;
		}
		Thing thing = new Thing();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TheUnnamedGUIPart.fxml"));
		loader.setController(thing);
		HBox.setHgrow(board.getRoot(), Priority.ALWAYS);
		board.getRoot().setMinSize(400, 400);

		HBox box = new HBox(20, board.getRoot(), loader.load());
		box.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		box.setAlignment(Pos.CENTER);

		primaryStage.setScene(new Scene(box));
	}

}
