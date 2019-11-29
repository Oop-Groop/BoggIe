package oopgroop.projects.boggIe.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
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
		private BooleanProperty highlightedProperty = new SimpleBooleanProperty();

		public boolean isHighlighted() {
			return highlightedProperty.get();
		}

		public void setHightlighted(boolean highlighted) {
			highlightedProperty.set(highlighted);
		}

		public BooleanProperty highlightedProperty() {
			return highlightedProperty;
		}

		private final Text text = new Text();
		private final Color backgroundColor = Color.gray(0.05);
		private final Color textFill = Color.gray(0.3);
		{
			Blend blend = new Blend(BlendMode.DIFFERENCE);
			ColorInput input = new ColorInput();
			blend.setTopInput(input);
			input.setWidth(Double.MAX_VALUE);
			input.setHeight(Double.MAX_VALUE);
			input.setPaint(Color.WHITE);

			highlightedProperty.addListener((observable, oldValue, newValue) -> setEffect(newValue ? blend : null));

			setAlignment(Pos.CENTER);
			getChildren().add(text);
			text.setFont(Font.font("monospace", 80));
			text.setFill(textFill);

			setMinSize(0, 0);
			// This makes each tile scale its own size to fit the board correctly. This is
			// used if the window is resized.
			prefWidthProperty().bind(root.widthProperty().divide(columnCount));
			prefHeightProperty().bind(root.heightProperty().divide(rowCount));
			setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));

			final Color hoverColor = Color.gray(0.3), textFill = Color.hsb(Math.random() * 360, 1, 1);

			setOnMouseClicked(event -> onClick(this, event));
			final Transition hoverTransition = new Transition() {
				{
					setCycleCount(1);
					setCycleDuration(Duration.millis(700));
				}

				@Override
				protected void interpolate(final double frac) {
					text.setFill(Die.this.textFill.interpolate(textFill, frac));
					setBackground(new Background(
							new BackgroundFill(backgroundColor.interpolate(hoverColor, frac), null, null)));
				}
			};
			setOnMouseEntered(event -> {
				hoverTransition.setRate(1);
				hoverTransition.play();
			});
			setOnMouseExited(event -> {
				hoverTransition.setRate(-1);
				hoverTransition.play();
			});
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
