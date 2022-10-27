package hw4.boggle.board;

public abstract class UnsynchronizedBaseCell extends BaseCell {
	private boolean visited = false;

	public UnsynchronizedBaseCell(int x, int y, char letter) {
		super(x, y, letter);
	}

	public abstract void initializeNeighbors();

	public boolean isVisited(int threadId) {
		return visited;
	}

	public void vacate(int threadId) {
		visited = false;
	}

	public void visit(int threadId) {
		visited = true;
	}
}