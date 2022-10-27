package hw4.boggle.board;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseBoard implements IBoard {
	private static AtomicInteger cellIndex = new AtomicInteger(0);
	protected int N;
	protected ICell[][] cells;
	private List<ICell>[] letterList = makeICellListArray(26);
	private static final char A = 'a';
	
	public static void resetNextCellGetter(){
		cellIndex.set(0);
	}
	
	public BaseBoard(int theN, String[] theBoard) {
		this.N = theN;
		cells = new ICell[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++){
				char c = Character.toLowerCase(theBoard[i*N+j].charAt(0));
				if(i == 0) {
					if(j == 0){
						cells[i][j] = makeNorthWestCell(i,j, c);						
					}
					else if(j == N-1) {
						cells[i][j] = makeSouthWestCell(i,j,c);
					}
					else {
						cells[i][j] = makeWestCell(i,j,c);
					}
				}
				else if(i == N-1) {
					if(j == 0){
						cells[i][j] = makeNorthEastCell(i,j, c);						
					}
					else if(j == N-1) {
						cells[i][j] = makeSouthEastCell(i,j,c);
					}
					else {
						cells[i][j] = makeEastCell(i,j,c);
					}
				}
				else if(j == 0) {
					cells[i][j] = makeNorthCell(i,j,c);
				}
				else if( j == N-1) {
					cells[i][j] = makeSouthCell(i,j,c);
				}
				else {
					cells[i][j] = makeCentralCell(i,j,c);
				}
				if(letterList[c-A] == null) {
					letterList[c-A]= makeICellNeighborList();
				}
				letterList[c-A].add(cells[i][j]);
			}
		}
		for( int i = 0; i < letterList.length; i++) {
			if(letterList[i] == null){
				letterList[i] = makeICellNeighborList();
			}
		}
		initializeCells();
	}
	abstract List<ICell> makeICellNeighborList();
	abstract List<ICell>[] makeICellListArray(int size);
	abstract ICell makeNorthWestCell(int x, int y, char letter);
	abstract ICell makeNorthCell(int x, int y, char letter);
	abstract ICell makeNorthEastCell(int x, int y, char letter);
	abstract ICell makeEastCell(int x, int y, char letter);
	abstract ICell makeSouthEastCell(int x, int y, char letter);
	abstract ICell makeSouthCell(int x, int y, char letter);
	abstract ICell makeSouthWestCell(int x, int y, char letter);
	abstract ICell makeWestCell(int x, int y, char letter);
	abstract ICell makeCentralCell(int x, int y, char letter);
	
	
	protected void initializeCells() {
		for(int i = 0; i < N;i++){
			for(int j = 0; j < N; j++){
				cells[i][j].initializeNeighbors();
			}
		}
	}
	
	public List<ICell> getStartingPoints(char letter) {
		List<ICell> list = letterList[Character.toLowerCase(letter)-A];
		return list;
	}
	
	public List<ICell> getStartingPoints(int x, int y, int width, int height) {
		List<ICell> list = makeICellNeighborList();
		for(int i = x; i < width; i++ ) {
			for(int j = y; j < height; j++) {
				list.add(cells[i][j]);
			}
		}
		return list;
	}
	public ICell getNextCell() {
		int indexToReturn = cellIndex.getAndIncrement();
		if(indexToReturn < N * N){
			return cells[indexToReturn/N][indexToReturn%N];
		}else { 
			return null;
		}
	}
}
