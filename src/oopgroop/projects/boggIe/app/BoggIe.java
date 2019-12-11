package oopgroop.projects.boggIe.app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oopgroop.projects.boggIe.api.Board;
import oopgroop.projects.boggIe.api.Board.Die;

public final class BoggIe extends Application {

	public static void main(final String[] args) {
		Application.launch(args);
	}

	private @FXML Text playerName;
	private @FXML TextField input;

	private @FXML Button submit;

	private Board board;

	private void recalculate() {

	}

	private @FXML void initialize() {
		playerName.setFont(Font.font("monospace", FontWeight.BOLD, 20));
		playerName.setText("Player 1");
		submit.setOnAction(event -> submit());
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.show();
		board = new Board(4, 4);

		final FXMLLoader loader = new FXMLLoader(getClass().getResource("TheUnnamedGUIPart.fxml"));
		loader.setController(this);
		HBox.setHgrow(board.getRoot(), Priority.ALWAYS);
		board.getRoot().setMinSize(400, 400);

		final HBox box = new HBox(board.getRoot(), loader.load());
		box.setBackground(new Background(new BackgroundFill(Color.gray(0.2), null, null)));
		box.setAlignment(Pos.CENTER);

		primaryStage.setScene(new Scene(box));
	}

	private void submit() {

	}

}
