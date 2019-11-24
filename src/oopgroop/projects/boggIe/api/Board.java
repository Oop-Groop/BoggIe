package oopgroop.projects.boggIe.api;

import java.util.Random;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {

	private final Die[][] board;

	public Die getTile(int x, int y) {
		return board[x][y];
	}

	public Board(int length, int height) {
		board = new Die[length][height];
	}

	public class Die {
		private char[] letters;
		private String letter;

		private Die(String s, int x, int y) {
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
