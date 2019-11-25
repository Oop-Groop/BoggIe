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

public final class BoggIe extends Application {

	private @FXML Text playerName;
	private @FXML TextField input;
	private @FXML Button submit;

	private @FXML void initialize() {
		playerName.setFont(Font.font("monospace", FontWeight.BOLD, 20));
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
		Board board = new Board(4, 4) {
			@Override
			public void onClick(Die die, MouseEvent event) {
				selectLetter(die.getLetter().charAt(0));
			}
		};

		FXMLLoader loader = new FXMLLoader(getClass().getResource("TheUnnamedGUIPart.fxml"));
		loader.setController(this);
		HBox.setHgrow(board.getRoot(), Priority.ALWAYS);
		board.getRoot().setMinSize(400, 400);

		HBox box = new HBox(board.getRoot(), loader.load());
		box.setBackground(new Background(new BackgroundFill(Color.gray(0.2), null, null)));
		box.setAlignment(Pos.CENTER);

		playerName.setText("Player 1");
		input.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case A:
			case B:
			case C:
			case D:
			case E:
			case F:
			case G:
			case H:
			case I:
			case J:
			case K:
			case L:
			case M:
			case N:
			case O:
			case P:
			case Q:
			case R:
			case S:
			case T:
			case U:
			case V:
			case W:
			case X:
			case Y:
			case Z:
				selectLetter(event.getCode().getName().toLowerCase().charAt(0));
				break;
			case BACK_SPACE:
				if (event.isControlDown())
					clear();
				else
					goBack();
				break;
			case ENTER:

			}
		});

		primaryStage.setScene(new Scene(box));
	}

	private void submit() {

	}

	private void selectLetter(char letter) {

	}

	private void goBack() {

	}

	private void clear() {

	}

}
