package oopgroop.projects.boggIe.api;

public final class Position {
	public final int col, row;

	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public Position(Piece piece) {
		this(piece.col, piece.row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 *
	 * Overridden for testing/debugging purposes.
	 */
	@Override
	public String toString() {
		return "Position[col=" + col + ",row=" + row + "]";
	}

}