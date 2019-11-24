package oopgroop.projects.boggIe.api;

import java.util.Random;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {

	private final Dice[][] board;

	public Dice getTile(int x, int y) {
		return board[x][y];
	}

	public Board(int length, int height) {
		board = new Dice[length][height];
	}

	public class Dice {
		private char[] letters;
		private String letter;

		private Dice(String s, int x, int y) {
			this.letters = s.toCharArray();
		}

		public String rollDice() {
			Random rand = new Random();
			char letter = this.letters[rand.nextInt(6)];
			if (letter == 'Q') {
				this.letter = "Qu";
				return "Qu";
			}
			this.letter = String.valueOf(letter);
			return this.letter;
		}

		public String getLetter() {
			return letter;
		}

		public char[] getLetters() {
			return letters;
		}

	}
}
