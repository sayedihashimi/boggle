package hw4.boggle.board;

import java.util.LinkedList;
import java.util.List;

public class UnsynchronizedBoard extends BaseBoard {
	
	public UnsynchronizedBoard(int N, String[] board) {
		super(N, board);
	}
	
	public String[] getLetters(){
		String[] s = new String[N*N];
		for(int i = 0; i < N; i++ ){
			for(int j = 0; j < N; j++) {
				s[i*N+j]=new String(cells[i][j].getLetter()+"");
			}
		}
		return s;
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
		return new LinkedList[size];
	}

	@Override
	List<ICell> makeICellNeighborList() {
		return new LinkedList<ICell>();
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

	private class NorthWestCell extends UnsynchronizedBaseCell {
		
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
	private class NorthCell extends UnsynchronizedBaseCell {
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
	private class NorthEastCell extends UnsynchronizedBaseCell {
		public NorthEastCell(int x, int y, char letter) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S
		}
	}
	private class EastCell extends UnsynchronizedBaseCell {
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
	private class SouthEastCell extends UnsynchronizedBaseCell {
		
		public SouthEastCell(int x, int y, char letter) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
		}
	}
	private class SouthCell extends UnsynchronizedBaseCell {
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
	private class SouthWestCell extends UnsynchronizedBaseCell {

		public SouthWestCell(int x, int y, char letter ) {
			super(x,y,letter);
		}
		public void initializeNeighbors() {
			neighbors.add(cells[x][y-1]);//N
			neighbors.add(cells[x+1][y-1]);//NE
			neighbors.add(cells[x+1][y]); //E
		}
	}
	private class WestCell extends UnsynchronizedBaseCell {
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
	private class CentralCell extends UnsynchronizedBaseCell {
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
}
