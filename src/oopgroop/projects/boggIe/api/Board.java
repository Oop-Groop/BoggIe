package oopgroop.projects.boggIe.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board {

	private final GridPane root = new GridPane();
	// This is the board's width *in pixels*. This is used for layout and resizing
	// purposes, and is different from the rowCount and columnCount above.
	protected final ReadOnlyDoubleProperty boardWidth = root.widthProperty(), boardHeight = root.heightProperty();

	public final ReadOnlyDoubleProperty boardHeightProperty() {
		return boardHeight;
	}

	public final ReadOnlyDoubleProperty boardWidthProperty() {
		return boardWidth;
	}

	private final static List<char[]> DICE_CHARS = new ArrayList<>();
	static {
		Scanner scanner = new Scanner(Board.class.getResourceAsStream("diceletters"));
		while (scanner.hasNextLine())
			DICE_CHARS.add(scanner.nextLine().toCharArray());
		scanner.close();

	}

	private Die[][] board;
	private final IntegerProperty columnCount = new SimpleIntegerProperty(0), rowCount = new SimpleIntegerProperty(0);

	{
		InvalidationListener listener = __ -> layoutBoard();
		columnCount.addListener(listener);
		rowCount.addListener(listener);
	}

	public Die getDie(int x, int y) {
		return board[x][y];
	}

	public Board(int columnCount, int rowCount) {
		board = new Die[columnCount][rowCount];

		// Initialize width and height properties to initial values.
		this.columnCount.set(columnCount);
		this.rowCount.set(rowCount);
	}

	public Board() {
		this(4, 4);
	}

	public void shuffle() {
		for (Die[] d_a : board)
			for (Die d : d_a)
				d.rollDie();
	}

	public final int getColumnCount() {
		return columnCount.get();
	}

	public final int getRowCount() {
		return rowCount.get();
	}

	public final void setColumnCount(int columnCount) {
		this.columnCount.set(columnCount);
	}

	public final void setRowCount(int rowCount) {
		this.rowCount.set(rowCount);
	}

	public final IntegerProperty rowCountProperty() {
		return rowCount;
	}

	public final IntegerProperty columnCountProperty() {
		return columnCount;
	}

	protected void layoutBoard() {
		root.getChildren().clear();
		for (int i = 0, col = 0; col < getColumnCount(); col++)
			for (int row = 0; row < getRowCount(); row++)
				// y is the column, x is the row.
				root.add(board[col][row] = new Die(DICE_CHARS.get((i++) % DICE_CHARS.size()), col, row), col, row);
	}

	public GridPane getRoot() {
		return root;
	}

	public class Die extends StackPane {
		private final Text text = new Text();
		{
			setAlignment(Pos.CENTER);
			getChildren().add(text);
			text.setFont(Font.font("monospace", 80));

			setMinSize(0, 0);
			// This makes each tile scale its own size to fit the board correctly. This is
			// used if the window is resized.
			prefWidthProperty().bind(root.widthProperty().divide(columnCount));
			prefHeightProperty().bind(root.heightProperty().divide(rowCount));
			setBackground(new Background(new BackgroundFill(Color.hsb(Math.random() * 360, 1, 1), null, null)));
		}
		private char[] letters;
		private final int x, y;

		public final int getX() {
			return x;
		}

		public final int getY() {
			return y;
		}

		private Die(char[] chars, int x, int y) {
			this.letters = chars;
			this.x = x;
			this.y = y;
			rollDie();
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
		public void rollDie() {
			char letter = this.letters[new Random().nextInt(6)];
			text.setText(letter == 'Q' ? "Qu" : String.valueOf(letter));
		}

		public String getLetter() {
			return text.getText();
		}

		public char[] getLetterList() {
			return letters;
		}

	}
}
