package hw4.boggle.board;

public abstract class SynchronizedBaseCell extends BaseCell {
	private volatile int visitors = 0x00000000;

	public SynchronizedBaseCell(int x, int y, char letter) {
		super(x, y, letter);
	}
	
	public abstract void initializeNeighbors();

	public final boolean isVisited(int threadId) {
		return (visitors & threadId) != 0;
	}

	public final synchronized void vacate(int threadId) {
		visitors &= (~threadId);
	}

	public final synchronized void visit(int threadId) {
		visitors |= threadId;
	}

}
