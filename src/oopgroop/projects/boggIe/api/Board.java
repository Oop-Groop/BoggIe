package oopgroop.projects.boggIe.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * <p>
 * A class that represents a physical game board. It stores {@link BoardTile}'s
 * in a matrix, ({@link #tiles}) which can each contain a piece, as well as do a
 * few other things.
 *
 * <p>
 * Do note that this class is concrete and provides the full implementation of a
 * board. A {@link Board} object creates ({@link #initBoard()}) and colors board
 * tiles accordingly and keeps track of them through a {@link BoardTile} matrix.
 * A {@link Board} will also place its board tiles inside a {@link GridPane}
 * that can be obtained with {@link #getBoard()}, and then shown on a scene
 * graph. With no other code, this gridpane will simply be an empty chess board.
 *
 * <p>
 * {@link Board}s depend on a {@link BoardLogicHandler} to provide user
 * interfacing. The {@link Board} class will distribute click events, (in terms
 * of the column and row clicked) which will be handled by a logic handler. The
 * logic handler is also tasked with populating the board when it is being
 * prepared for gameplay. See {@link #start(BoardLogicHandler)} for how a logic
 * handler is given to a board, and {@link BoardLogicHandler} for more
 * information on the class itself.
 *
 * <p>
 * One last thing: {@link BoardLogicHandler}, although an inner class, is a
 * class of its own which receives a reference to the board that it's attached
 * to. The board will also keep a reference to its handler to distribute events.
 * Any handler that takes in a {@link Board} can be attached to a board. If a
 * handler wants more specific usability, it can choose to only be attachable to
 * subclasses of board that provide it with even more API methods, or provide it
 * with different types of {@link BoardTile}s, or something.
 *
 * <p>
 * {@link DefaultBoardSetup} is the most immediate subclass of
 * {@link BoardLogicHandler} that I've made.
 */
public class Board {

	/**
	 * <p>
	 * This class handles the functionality and user interfacing for a
	 * {@link Board}. It populates the board with pieces (if it wants to) when it's
	 * attached to a {@link Board}, (by overriding the {@link #start()} method), and
	 * it receives and can handle click events through the {@link #click(int, int)}
	 * method (also meant to be overridden).
	 * <p>
	 * This class contains a lot of final protected methods that can be used to
	 * determine different scenarios or possible piece moves for chess.
	 */
	public abstract class BoardLogicHandler {

		protected ReadOnlyDoubleProperty boardWidth = root.widthProperty(), boardHeight = root.heightProperty();

		protected final Board board = Board.this;

		/**
		 * This method is overridden so that the logic handler can handle click events.
		 *
		 * @param col The column that was clicked.
		 * @param row The row that was clicked.
		 */
		protected abstract void click(int col, int row);

		// Gets a Piece matrix that represents this board. It can be used in some of the
		// "getMove" methods above.
		protected final Piece[][] getBoard() {

			// Declare a Piece matrix.
			Piece[][] pieces = new Piece[getColumnCount()][getRowCount()];

			// Populate Piece matrix.
			for (int col = 0; col < getColumnCount(); col++)
				for (int row = 0; row < getRowCount(); row++)
					pieces[col][row] = getPiece(col, row);

			return pieces;
		}

		protected final Piece getPiece(int col, int row) {
			return getTile(col, row).getPiece();
		}

		protected final Piece getPiece(Position position) {
			return getPiece(position.col, position.row);
		}

		// Gets a list of the pieces in this board. The list is freshly created and
		// mutable.
		protected final List<Piece> getPieces() {

			List<Piece> pieces = new LinkedList<>();

			for (Piece[] col : getBoard())
				for (Piece p : col)
					if (p != null)
						pieces.add(p);

			return pieces;

		}

		/**
		 * Returns the {@link BoardTile} at the specified position.
		 *
		 * @param col The column.
		 * @param row The row.
		 * @return The {@link BoardTile} at the given position.
		 */
		protected final BoardTile getTile(int col, int row) {
			return tiles[col][row];
		}

		protected final boolean hasPiece(int col, int row) {
			return getTile(col, row).hasPiece();
		}

		protected final boolean inBounds(int col, int row, Piece[][] board) {
			return !(col < 0 || col >= board.length || row < 0 || row >= board.length);
		}

		protected final boolean inBounds(Position targetPos, Piece[][] board) {
			return inBounds(targetPos.col, targetPos.row, board);
		}

		protected final boolean isTileEmpty(int col, int row) {
			return !getTile(col, row).hasPiece();
		}

		/**
		 * Returns whether or not there is a piece at the specified position.
		 *
		 * @param position The position to check for being empty.
		 * @return <code>true</code> if the given tile is empty, <code>false</code>
		 *         otherwise.
		 */
		protected final boolean isTileEmpty(Position position) {
			return isTileEmpty(position.col, position.row);
		}

		/**
		 * <p>
		 * Puts a {@link Piece} in the specified location. If there is already a piece
		 * there, the previous piece gets removed from the board and returned. If the
		 * specified piece already exists on the board, it is simply moved.
		 * <p>
		 * This method will work unexpectedly if the specified column and row are not a
		 * position on the board.
		 *
		 * @param piece The {@link Piece} to put at the specified location.
		 * @param col   The column.
		 * @param row   The row.
		 * @return The piece previous located at the specified position, or
		 *         <code>null</code> if there was none.
		 */
		protected final Piece put(Piece piece, int col, int row) {
			return getTile(col, row).setPiece(piece);
		}

		protected final Piece remove(int col, int row) {
			return clear(col, row);
		}

		protected final Piece clear(int col, int row) {
			return getTile(col, row).removePiece();
		}

		protected final Piece remove(Piece piece) {
			return remove(piece.col, piece.row);
		}

		/**
		 * Called when the game is started. This method is overridden to populate the
		 * board, if necessary, and do some initialization in the logic handler.
		 */
		protected abstract void start();

		/**
		 * Switches the locations of two {@link Piece}s. If one, or both of the pieces
		 * are not on the board, this method may function improperly or throw an
		 * exception.
		 *
		 * @param piece The first piece.
		 * @param other The second piece.
		 */
		protected final void switchPieces(Piece piece, Piece other) {
			Position previousPiecePos = new Position(piece);
			put(piece, other.col, other.row);
			put(other, previousPiecePos.col, previousPiecePos.row);
		}

	}

	/**
	 * <p>
	 * A class representing a single tile in a board. This class is also a subclass
	 * of {@link StackPane}.
	 *
	 * <p>
	 * The constructor for {@link BoardTile}s takes in the {@link BoardTile}'s
	 * position on the board. The constructor will use this to calculate its own
	 * color. See {@link BoardTile#BoardTile(int, int)}
	 */
	public class BoardTile extends StackPane {

		// This tile's column and row.
		final int col, row;

		// This is a circle that is centered in the stackpane (like any other node in a
		// stackpane, by default). It is used to show the user possible moves for a
		// selected piece.
		//
		// Since nodes can only appear in the scenegraph once, each BoardTile is being
		// given its own circle to show possible moves.
		private final Circle showMoveNode = new Circle();
		{

			/*
			 * Tile sizing
			 */

			setMinSize(0, 0);
			// This makes each tile scale its own size to fit the board correctly. This is
			// used if the window is resized.
			prefWidthProperty().bind(root.widthProperty().divide(columnCountProperty()));
			prefHeightProperty().bind(root.heightProperty().divide(rowCountProperty()));

			// The board will receive and calculate the position of all click events.
			setMouseTransparent(true);

			/*
			 * Circle Initialization
			 */

			showMoveNode.setRadius(128);
			showMoveNode.scaleXProperty().bind(widthProperty().divide(128 * 3));
			showMoveNode.scaleYProperty().bind(heightProperty().divide(128 * 3));

			showMoveNode.setVisible(false);// This is changed when we show moves.
			getChildren().add(showMoveNode);

			// The fill and stroke may be different depending on what the node is being
			// shown for. showCastleMove() shows a greenish circle, while showMove() shows a
			// grayish circle.
			showMoveNode.setFill(BLACK_BACKGROUND.interpolate(WHITE_BACKGROUND, 0.5));
			showMoveNode.setStroke(BLACK_BACKGROUND.interpolate(WHITE_BACKGROUND, 0.7));

		}

		// Constructor. Sets the column and row of this tile and sets its background
		// accordingly.
		protected BoardTile(int col, int row) {
			this.col = col;
			this.row = row;
			// Now that we are given a row and column, set the color of this tile.
			setBackground(new Background(
					new BackgroundFill((col + row & 1) == 0 ? WHITE_BACKGROUND : BLACK_BACKGROUND, null, null)));
		}

		/**
		 * Gets the {@link Piece} on this tile and returns it. If there is no piece on
		 * this tile, <code>null</code> is returned.
		 *
		 * @return The {@link Piece} on this tile, or <code>null</code> if there is
		 *         none.
		 */
		public Piece getPiece() {
			for (Node n : getChildren())
				if (Piece.isPieceShape(n))
					return Piece.getPiece((Shape) n);
			return null;
		}

		/**
		 * @return <code>true</code> if this tile contains a {@link Piece},
		 *         <code>false</code> otherwise.
		 */
		public boolean hasPiece() {
			return getPiece() != null;
		}

		/**
		 * Hides the {@link #showMoveNode}; this method will hide whatever possible move
		 * is showing.
		 */
		public void hideMove() {
			showMoveNode.setVisible(false);
		}

		/**
		 * @return <code>true</code> if this {@link BoardTile}'s {@link #showMoveNode}
		 *         is visible. In other words, <code>true</code> if this tile is showing
		 *         a piece's possible move, <code>false</code> otherwise.
		 */
		public boolean isShowingMove() {
			return showMoveNode.isVisible();
		}

		/**
		 * Removes whatever {@link Piece} is on this tile, if any, and returns it. If no
		 * piece is on this tile, <code>null</code> is returned.
		 *
		 * @return The previous {@link Piece}, or <code>null</code> if there was none.
		 */
		public Piece removePiece() {
			Piece piece = getPiece();
			if (hasPiece()) {
				getChildren().remove(piece.getShape());
				piece.col = piece.row = -1;
			}
			showMoveNode.setFill(BLACK_BACKGROUND.interpolate(WHITE_BACKGROUND, 0.5));
			showMoveNode.setStroke(BLACK_BACKGROUND.interpolate(WHITE_BACKGROUND, 0.7));
			return piece;
		}

		/**
		 * Sets the {@link Piece} at this tile and returns the previous one, or
		 * <code>null</code> if there wasn't one.
		 *
		 * @param piece The new piece to put on this tile.
		 * @return The previous {@link Piece}, or <code>null</code> if there wasn't one.
		 */
		public Piece setPiece(Piece piece) {
			Piece previous = removePiece();
			if (isShowingMove())
				hideMove();
			getChildren().add(piece.getShape());
			piece.move(new Position(col, row));
			showMoveNode.setFill(Color.RED);
			showMoveNode.setStroke(Color.FIREBRICK);
			return previous;
		}

		/**
		 * Shows a possible castle move for the king. The king can "castle" if neither
		 * the king, nor the rook that the king will move with, has moved yet, and there
		 * are no pieces in between them.
		 */
		public void showCastleMove() {
			showMoveNode.setVisible(true);
			// We should never see Deep Pink because you can't castle if there is a piece in
			// the way. But with lampshade chess, things are weird, so I've added this in,
			// just in case. :)
			showMoveNode.setFill(hasPiece() ? Color.DEEPPINK : Color.OLIVEDRAB);
		}

		/**
		 * Shows a possible move. If this tile contains a piece then this method shows a
		 * red circle, signifying that the selected piece can "kill" the piece on this
		 * tile.
		 */
		public void showMove() {
			showMoveNode.setVisible(true);
			if (hasPiece()) {
				showMoveNode.setFill(Color.RED);
				showMoveNode.setStroke(Color.FIREBRICK);
			} else {
				showMoveNode.setFill(BLACK_BACKGROUND.interpolate(WHITE_BACKGROUND, 0.5));
				showMoveNode.setStroke(BLACK_BACKGROUND.interpolate(WHITE_BACKGROUND, 0.7));
			}
		}
	}

	// Board tile colors
	protected static final Color WHITE_BACKGROUND = Color.ANTIQUEWHITE,
			BLACK_BACKGROUND = new Color(0x63 / 255d, 0x3a / 255d, 0, 1);

	// These are readonly, bindable variables representing the board's sizes (# of
	// columns & # of rows). Although subclasses and stuff can use the JavaFX
	// property methods in this class to observe these properties, I haven't
	// actually implemented board resizability (yet?).
	private final ReadOnlyIntegerWrapper columnCount = new ReadOnlyIntegerWrapper(8),
			rowCount = new ReadOnlyIntegerWrapper(8);
	/**
	 * The <b>default</b> root of this {@link Board}. Subclasses may return a
	 * different {@link Parent} object through {@link Board#getBoard()}, which can
	 * wrap this one.
	 */
	private final GridPane root = new GridPane();

	// The tile matrix
	private final BoardTile[][] tiles = initBoard();

	// This is the board's width *in pixels*. This is used for layout and resizing
	// purposes, and is different from the rowCount and columnCount above.
	protected final ReadOnlyDoubleProperty boardWidth = root.widthProperty(), boardHeight = root.heightProperty();

	private BoardLogicHandler handler;

	public final ReadOnlyDoubleProperty boardHeightProperty() {
		return boardHeight;
	}

	public final ReadOnlyDoubleProperty boardWidthProperty() {
		return boardWidth;
	}

	public final javafx.beans.property.ReadOnlyIntegerProperty columnCountProperty() {
		return columnCount.getReadOnlyProperty();
	}

	/**
	 * This method is a quick construction method for {@link BoardTile}s. If a
	 * subclass wants to provide its own type of {@link BoardTile}, but doesn't want
	 * to have to iterate over every space to add {@link BoardTile}s in, it can
	 * simply pass in a {@link BiFunction} that constructs and returns a
	 * {@link BoardTile} when given the BoardTile's location. This will allow the
	 * {@link Board} itself to calculate how many tiles to place and where to place
	 * them, safely. <b>The resulting BoardTile matrix <i>is returned</i>, not set
	 * as the board's matrix.</b> The purpose of this method is to be called when
	 * overriding {@link #initBoard()}, which can be overridden to provide the
	 * superclass ({@link Board}) with the board tile matrix to use.
	 *
	 * @param constructor A {@link BiFunction} that takes a column and a row and
	 *                    returns a {@link BoardTile}. This is often (and
	 *                    preferably) a method reference.
	 * @return The constructed {@link BoardTile} matrix.
	 */
	protected final BoardTile[][] construct(BiFunction<Integer, Integer, ? extends BoardTile> constructor) {
		return construct(constructor, getRowCount(), getColumnCount());
	}

	/**
	 * This method works just like {@link #construct(BiFunction)}, but takes a
	 * length and a height. The length and height determine the size of the board
	 * matrix returned. The matrix is still populated entirely, regardless of the
	 * size.
	 *
	 * @param constructor A constructor that constructs some kind of
	 *                    {@link BoardTile} given a column and row position.
	 * @param length      The board length, or number of columns.
	 * @param height      The board height, or number of rows.
	 * @return A new {@link BoardTile} matrix.
	 */
	protected final BoardTile[][] construct(BiFunction<Integer, Integer, ? extends BoardTile> constructor, int length,
			int height) {
		BoardTile[][] tiles = new BoardTile[length][height];

		for (int i = 0; i < tiles.length; i++)
			for (int j = 0; j < tiles[0].length; j++) {
				BoardTile tile = constructor.apply(i, j);
				root.add(tile, i, j);
				tiles[i][j] = tile;
			}

		return tiles;
	}

	/**
	 * @return Returns the scenegraph object that stores the game board. This can be
	 *         displayed to the user to show the board.
	 */
	public Parent getBoard() {
		return root;
	}

	/**
	 * @return The height of the game board in pixel units.
	 */
	public final double getBoardHeight() {
		return boardHeightProperty().get();
	}

	/**
	 * @return The width of the game board in pixel units.
	 */
	public final double getBoardWidth() {
		return boardWidthProperty().get();
	}

	public final int getColumnCount() {
		return columnCountProperty().get();
	}

	/*
	 * JavaFX Property Methods
	 */

	protected BoardLogicHandler getHandler() {
		return handler;
	}

	public final int getRowCount() {
		return rowCountProperty().get();
	}

	/**
	 * Returns a copy of the internal matrix that stores board tiles. Changing
	 * things in this matrix will not affect the board itself, but changing
	 * {@link BoardTile}s in the matrix will affect the tiles in this {@link Board}.
	 *
	 * @return A clone of the internal matrix that stores {@link BoardTile}s.
	 */
	protected final BoardTile[][] getTileMat() {

		// This method just returns a (deep) clone of the internal array that stores
		// BoardTiles.

		BoardTile[][] array = new BoardTile[getColumnCount()][getRowCount()];
		for (int col = 0; col < getColumnCount(); col++)
			for (int row = 0; row < getRowCount(); row++)
				array[col][row] = tiles[col][row];
		return array;
	}

	/**
	 * Gets a list of {@link BoardTile}s in this {@link Board}.
	 *
	 * @return A <b>new</b>, mutable {@link List} of all the {@link BoardTile}s in
	 *         this {@link Board}.
	 */
	protected final List<BoardTile> getTiles() {

		List<BoardTile> tiles = new LinkedList<>();
		for (BoardTile[] btArr : this.tiles)
			for (BoardTile bt : btArr)
				tiles.add(bt);

		return tiles;
	}

	/**
	 * Called during initialization to setup the board with {@link BoardTile}s. This
	 * method needs to return a matrix of board tiles with the sizes of
	 * {@link #getColumnCount()} and {@link #getRowCount()}.
	 * (<code>new BoardTile[getColumnCount()][getRowCount()]</code>). This method
	 * then needs to populate all the tiles in the board tile matrix with board
	 * tiles of its choice.
	 *
	 * @return An initialized board, as a matrix of {@link BoardTile}s.
	 */
	protected BoardTile[][] initBoard() {
		return construct(BoardTile::new);
	}

	public final javafx.beans.property.ReadOnlyIntegerProperty rowCountProperty() {
		return rowCount.getReadOnlyProperty();
	}

	/**
	 * This method is used by a client to start the game. The
	 * {@link BoardLogicHandler} that is specified handles all of the game logic.
	 * First its {@link BoardLogicHandler#start(Board)} method is called with an
	 * instance of this object as a parameter. Then, once that method returns, the
	 * BoardLogicHandler's {@link BoardLogicHandler#click(Board, int, int)} method
	 * is called as clicks are received from the user.
	 *
	 * @param logic the {@link BoardLogicHandler} that will handle the logic of this
	 *              Board
	 */
	public void start(BoardLogicHandler logic) {

		// I'm currently too lazy to check if the logic handler is being used, but why
		// not throw an exception if the board is being used.
		if (handler != null)
			throw new IllegalStateException("This board is already in use.");
		handler = logic;
		// Have the game board dispatch click events.
		root.addEventHandler(MouseEvent.MOUSE_CLICKED,
				event -> handler.click((int) (event.getX() / logic.boardWidth.get() * getColumnCount()),
						(int) (event.getY() / logic.boardHeight.get() * getRowCount())));
		logic.start();

	}

}
