package hw4.boggle.board;

import java.util.List;

public abstract class BaseCell implements ICell {
	protected final int x;
	protected final int y;
	private final char letter;
	protected List<ICell> neighbors;
	public BaseCell(int x, int y, char letter) {
		this.x = x;
		this.y = y;
		this.letter = letter;
		this.neighbors = BoardUtil.makeICellNeighborList();
	}
	public char getLetter() {
		return letter;
	}

	public final List<ICell> getNeighbors() {
		return neighbors;
	}

}
