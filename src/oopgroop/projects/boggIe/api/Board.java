package oopgroop.projects.boggIe.api;

public class Board<T> {

	private final T[][] board;

	public T getTile(int x, int y) {
		return board[x][y];
	}

	@SuppressWarnings("unchecked")
	public Board(int length, int height) {
		board = (T[][]) new Object[length][height];
	}
}
