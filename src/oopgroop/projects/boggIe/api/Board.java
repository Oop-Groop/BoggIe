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
		private int x, y;

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		private Die(char[] chars, int x, int y) {
			this.letters = chars;
			this.x = x;
			this.y = y;
		}

		/**
		 * <p>
		 * Rolls this die, as if it were shaken on a board.
		 * </p>
		 * <p>
		 * This method will "select" a random face on this die. Once a letter is
		 * selected, {@link #getLetter()} will return the selected letter, until the die
		 * is re-rolled.
		 * </p>
		 * 
		 */
		public void rollDice() {
			char letter = this.letters[new Random().nextInt(6)];
			this.letter = (letter == 'Q' ? "Qu" : String.valueOf(letter));
		}

		public String getLetter() {
			return letter;
		}

		public char[] getLetterList() {
			return letters;
		}

	}
}
