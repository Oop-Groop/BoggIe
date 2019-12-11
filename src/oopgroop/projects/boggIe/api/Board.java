package oopgroop.projects.boggIe.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.animation.Animation.Status;
import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Board {

	public class Die extends StackPane {

		private final Text text = new Text();
		private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.gray(.05)),
				from = new SimpleObjectProperty<>();

		private boolean visited = false;
		
		private final Color textFill = Color.gray(0.3);
		private Color textHoverFill = Color.hsb(Math.random() * 360, 1, 1);
		
		public void isVisited() {
			this.visited = true;
		}
		public boolean getVisited() {
			return this.visited;
		}
		
		public void setColor(Color color) {
			if (color == null)
				color = Color.gray(.05);
			backgroundColor.set(color);
			if (!isHover())
				setBackground(new Background(new BackgroundFill(color, null, null)));
		}
		public Color getColor() {
			return backgroundColor.get();
		}

		{
			setBackground(new Background(new BackgroundFill(backgroundColor.get(), null, null)));
			backgroundColor.addListener((observable, oldValue, newValue) -> from.set(oldValue));

			setAlignment(Pos.CENTER);
			getChildren().add(text);
			text.setFont(Font.font("monospace", 80));
			text.setFill(textFill);

			setMinSize(0, 0);
			// This makes each tile scale its own size to fit the board correctly. This is
			// used if the window is resized.
			prefWidthProperty().bind(root.widthProperty().divide(columnCount));
			prefHeightProperty().bind(root.heightProperty().divide(rowCount));
			setBackground(new Background(new BackgroundFill(backgroundColor.get(), null, null)));

			setOnMouseClicked(event -> onClick(this, event));
		}

		private final char[] letters;
		private final int x, y;

		private Die(final char[] chars, final int x, final int y) {
			letters = chars;
			this.x = x;
			this.y = y;
			rollDie();
		}

		public String getLetter() {
			return text.getText();
		}

		public char[] getLetterList() {
			return letters;
		}

		public final int getX() {
			return x;
		}

		public final int getY() {
			return y;
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
			final char letter = letters[new Random().nextInt(6)];
			text.setText(letter == 'Q' ? "Qu" : String.valueOf(letter));
		}

	}

	private final static List<char[]> DICE_CHARS = new ArrayList<>();

	static {
		final Scanner scanner = new Scanner(Board.class.getResourceAsStream("diceletters"));
		while (scanner.hasNextLine())
			DICE_CHARS.add(scanner.nextLine().toCharArray());
		scanner.close();

	}
	//gets a list of all of the places that this character is
	public ArrayList<Die> getDie(char c) {
		ArrayList<Die> dice = ArrayList<>();
		for(int i = 0; i < this.getRowCount(); i++) {
			for(int j = 0; j < this.getColumnCount(); j++) {
				if(this.getDie(i, j).getLetter().equals(c)) {
					dice.add(getDie(i, j));
				}
			}
		}
		return dice;
	}
	
	public boolean inBounds(Die d) {
		int dieX = d.getX();
		int dieY = d.getY();
		
		if(dieX < 0 || dieY < 0)
			return false;
		else if(dieX > 3 || dieY > 3)
			return false;
		else
			return true;
	}
	
	//get the surrounding dice
	public ArrayList<Die> getSurrounding(Die d) {
		int dieX = d.getX();
		int dieY = d.getY();
		ArrayList<Die> dice = new ArrayList<>();
		
		if(inBounds(this.getDie(dieX + 1, dieY))) {
			dice.add(this.getDie(dieX + 1, dieY));
		}
		if(inBounds(this.getDie(dieX - 1, dieY))) {
			dice.add(this.getDie(dieX - 1, dieY));
		}
		if(inBounds(this.getDie(dieX, dieY + 1))) {
			dice.add(this.getDie(dieX, dieY + 1));
		}
		if(inBounds(this.getDie(dieX, dieY - 1))) {
			dice.add(this.getDie(dieX, dieY - 1));
		}
		if(inBounds(this.getDie(dieX + 1, dieY + 1))) {
			dice.add(this.getDie(dieX + 1, dieY + 1));
		}
		if(inBounds(this.getDie(dieX + 1, dieY - 1))) {
			dice.add(this.getDie(dieX + 1, dieY - 1));
		}
		if(inBounds(this.getDie(dieX - 1, dieY + 1))) {
			dice.add(this.getDie(dieX - 1, dieY + 1));
		}
		if(inBounds(this.getDie(dieX - 1, dieY - 1))) {
			dice.add(this.getDie(dieX - 1, dieY - 1));
		}
		return dice;
	}
	

	private final GridPane root = new GridPane();
	// This is the board's width *in pixels*. This is used for layout and resizing
	// purposes, and is different from the rowCount and columnCount above.
	protected final ReadOnlyDoubleProperty boardWidth = root.widthProperty(), boardHeight = root.heightProperty();

	private final Die[][] board;
	private final IntegerProperty columnCount = new SimpleIntegerProperty(0), rowCount = new SimpleIntegerProperty(0);

	{
		final InvalidationListener listener = __ -> layoutBoard();
		columnCount.addListener(listener);
		rowCount.addListener(listener);
	}

	public Board() {
		this(4, 4);
	}

	public Board(final int columnCount, final int rowCount) {
		board = new Die[columnCount][rowCount];

		// Initialize width and height properties to initial values.
		this.columnCount.set(columnCount);
		this.rowCount.set(rowCount);
	}

	public final ReadOnlyDoubleProperty boardHeightProperty() {
		return boardHeight;
	}

	public final ReadOnlyDoubleProperty boardWidthProperty() {
		return boardWidth;
	}

	public final IntegerProperty columnCountProperty() {
		return columnCount;
	}

	public final int getColumnCount() {
		return columnCount.get();
	}

	public Die getDie(final int x, final int y) {
		return board[x][y];
	}

	public GridPane getRoot() {
		return root;
	}

	public final int getRowCount() {
		return rowCount.get();
	}

	protected void layoutBoard() {
		root.getChildren().clear();
		for (int i = 0, col = 0; col < getColumnCount(); col++)
			for (int row = 0; row < getRowCount(); row++)
				// y is the column, x is the row.
				root.add(board[col][row] = new Die(DICE_CHARS.get(i++ % DICE_CHARS.size()), col, row), col, row);
	}

	public void onClick(final Die die, final MouseEvent event) {
		
	}
	

	public final IntegerProperty rowCountProperty() {
		return rowCount;
	}

	public final void setColumnCount(final int columnCount) {
		this.columnCount.set(columnCount);
	}

	public final void setRowCount(final int rowCount) {
		this.rowCount.set(rowCount);
	}

	public void shuffle() {
		for (final Die[] d_a : board)
			for (final Die d : d_a)
				d.rollDie();
	}
}
