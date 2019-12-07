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

	private void clear() {

	}

	private void goBack() {

	}

	private @FXML void initialize() {
		playerName.setFont(Font.font("monospace", FontWeight.BOLD, 20));
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
				submit();
			}
		});
		submit.setOnAction(event -> submit());
	}

	private void selectLetter(final char letter) {
		for (int i = 0; i < board.getRowCount(); i++)
			for (int j = 0; j < board.getColumnCount(); j++) {
				Die die = board.getDie(j, i);
				if (Character.toLowerCase(letter) == Character.toLowerCase(die.getLetter().charAt(0)))
					die.setColor(Color.DARKRED);
			}
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.show();
		board = new Board(4, 4) {
			@Override
			public void onClick(final Die die, final MouseEvent event) {
				selectLetter(die.getLetter().charAt(0));
			}
		};

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
