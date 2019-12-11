package oopgroop.projects.boggIe.app;

import java.io.File;

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
import oopgroop.projects.boggIe.api.Player;
import oopgroop.projects.boggIe.api.WordFileProcessor;
import oopgroop.projects.boggIe.api.WordList;

public final class BoggIe extends Application {

	public static void main(final String[] args) {
		Application.launch(args);
	}
	private WordList words = new WordList();
	private @FXML Text playerName;
	private @FXML Text score;
	private @FXML TextField input;
	
	private Player player = new Player("Player 1");

	private @FXML Button submit;
	private @FXML Button shuffle;

	private Board board;

	private void recalculate() {

	}

	private void clear() {
		input.clear();
	}

	private void goBack() {
		if(input.getText().length() > 0) {
			input.setText(input.getText().substring(0, input.getText().length()-1));
		}
	}

	private @FXML void initialize() {
		playerName.setFont(Font.font("monospace", FontWeight.BOLD, 20));
		playerName.setText("Player 1");
		score.setText("0");
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
				
				input.appendText(event.getCode().getName());
				break;
			case BACK_SPACE:
				if (event.isControlDown()) {
					clear();
				} else {
					goBack();
				}
				break;
			case ENTER:
				submit();
			}
		});
		submit.setOnAction(event -> submit());
		shuffle.setOnAction(event -> shuffle());
	}
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.show();
		
		//adding words to WordList
		
		String[] wordList = WordFileProcessor.ConvertFileToWordList(new File("src/oopgroop/projects/boggIe/api/Words.txt"));
		words.PopulateChildren();
		words.AddWordsToWordList(wordList);
		
		board = new Board(4, 4) {
			@Override
			public void onClick(final Die die, final MouseEvent event) {
				input.appendText(die.getLetter());
			}
		};

		final FXMLLoader loader = new FXMLLoader(getClass().getResource("TheUnnamedGUIPart.fxml"));
		loader.setController(this);
		HBox.setHgrow(board.getRoot(), Priority.ALWAYS);
		board.getRoot().setMinSize(400, 400);

		final HBox box = new HBox(board.getRoot(), loader.load());
		box.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		box.setAlignment(Pos.CENTER);
		primaryStage.setScene(new Scene(box));
	}

	private void submit() {
		try {
			if(!player.hasGuessedWord(input.getText()) && input.getText().length() >= 2) {
				int score = words.GetScoreForWord(input.getText());
				player.addGuessedWord(input.getText());
				player.addScore(score);
				this.score.setText(String.valueOf(player.getScore()));
				input.clear();
			}else {
				input.clear();
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void shuffle() {
		board.shuffle();
	}
}
