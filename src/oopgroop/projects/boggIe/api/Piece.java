package oopgroop.projects.boggIe.api;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Piece {

	private static final Object PIECE_KEY = new Object();

	public static Piece getPiece(Shape shape) {
		return (Piece) shape.getProperties().get(PIECE_KEY);
	}

	/**
	 * Returns whether or not the specified node belongs to a {@link Piece} object.
	 *
	 * @param n The {@link Node} that is to be checked for belonging to a
	 *          {@link Piece}.
	 * @return <code>true</code> if there is a {@link Piece} object that has the
	 *         specified node as its {@link #shape}, <code>false</code> otherwise.
	 */
	public static boolean isPieceShape(Node n) {
		return n.getProperties().containsKey(PIECE_KEY);
	}

	private final Shape shape;

	public final Object type;

	public int col = -1, row = -1;

	public Piece(Shape shape, Object type) {
		this.shape = shape;
		this.type = type;

		shape.getProperties().put(PIECE_KEY, this);

		// The board will receive and calculate the position of all click events.
		shape.setMouseTransparent(true);
	}

	public Shape getShape() {
		return shape;
	}

	public void move(Position to) {
		col = to.col;
		row = to.row;
	}

	@Override
	public String toString() {
		return String.valueOf(type);
	}

}
