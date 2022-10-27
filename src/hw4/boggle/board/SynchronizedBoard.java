package hw4.boggle.board;

import java.util.LinkedList;
import java.util.List;

public class SynchronizedBoard extends BaseBoard {
	
	public SynchronizedBoard(int N, String[] board) {
		super(N, board);
		resetNextCellGetter();
	}
	
	@Override
	ICell makeCentralCell(int x, int y, char letter) {
		return new CentralCell(x,y,letter);
	}

	@Override
	ICell makeEastCell(int x, int y, char letter) {
		return new EastCell(x,y,letter);
	}

	@Override
	List<ICell>[] makeICellListArray(int size) {
		return BoardUtil.makeICellListArray(size);
	}

	@Override
	List<ICell> makeICellNeighborList() {
		return BoardUtil.makeICellNeighborList();
	}

	@Override
	ICell makeNorthCell(int x, int y, char letter) {
		return new NorthCell(x, y, letter);
	}

	@Override
	ICell makeNorthEastCell(int x, int y, char letter) {
		return new NorthEastCell( x, y, letter);
	}

	@Override
	ICell makeNorthWestCell(int x, int y, char letter) {
		return new NorthWestCell( x, y, letter);
	}

	@Override
	ICell makeSouthCell(int x, int y, char letter) {
		return new SouthCell( x, y, letter);
	}

	@Override
	ICell makeSouthEastCell(int x, int y, char letter) {
		return new SouthEastCell( x, y, letter);
	}

	@Override
	ICell makeSouthWestCell(int x, int y, char letter) {
		return new SouthWestCell( x, y, letter);
	}

	@Override
	ICell makeWestCell(int x, int y, char letter) {
		return new WestCell( x, y, letter);
	}

	private class NorthWestCell extends SynchronizedBaseCell {
		
		public NorthWestCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x+1][y]);  	// 	E
			neighbors.add(cells[x][y+1]);   // 	S
			neighbors.add(cells[x+1][y+1]); // 	SE
		}
	}
	private class NorthCell extends SynchronizedBaseCell {
		public NorthCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y]);   // W
			neighbors.add(cells[x+1][y]);   // E
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]);   // S
			neighbors.add(cells[x+1][y+1]); // SE
		}
	}
	private class NorthEastCell extends SynchronizedBaseCell {
		public NorthEastCell(int x, int y, char letter) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S
		}
	}
	private class EastCell extends SynchronizedBaseCell {
		public EastCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S
		}
	}
	private class SouthEastCell extends SynchronizedBaseCell {
		
		public SouthEastCell(int x, int y, char letter) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
		}
	}
	private class SouthCell extends SynchronizedBaseCell {
		public SouthCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x+1][y-1]); // NE
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x+1][y]); // E
		}
	}
	private class SouthWestCell extends SynchronizedBaseCell {

		public SouthWestCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x][y-1]);//N
			neighbors.add(cells[x+1][y-1]);//NE
			neighbors.add(cells[x+1][y]); //E
		}
	}
	private class WestCell extends SynchronizedBaseCell {
		public WestCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x][y-1]);
			neighbors.add(cells[x+1][y-1]);
			neighbors.add(cells[x+1][y]);
			neighbors.add(cells[x][y+1]);
			neighbors.add(cells[x+1][y+1]);
		}
	}
	private class CentralCell extends SynchronizedBaseCell {
		public CentralCell(int x, int y, char letter){
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y-1]);
			neighbors.add(cells[x][y-1]);
			neighbors.add(cells[x+1][y-1]);
			neighbors.add(cells[x-1][y]);
			neighbors.add(cells[x+1][y]);
			neighbors.add(cells[x-1][y+1]);
			neighbors.add(cells[x][y+1]);
			neighbors.add(cells[x+1][y+1]);
		}
	}
	
	public void initializeCells() {
		for(int i = 0; i < N;i++){
			for(int j = 0; j < N; j++){
				cells[i][j].initializeNeighbors();
			}
		}		
	}

}
