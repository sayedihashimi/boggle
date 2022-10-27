package com.sedodream.boggle.drc.mt;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sedodream.boggle.drc.mt.Cell.INeighborFinder;

/**
 * @author David Ronald Cusick { drcusick@gmail.com }
 * 
 * For performance make the Cell an inner class of board.
 * 
 */
public class Board implements IBoard {
	private int N; // Square Board edge length
	private ICell[][] cells;
	private List<ICell>[] letterList = new LinkedList[26];
	private static final char A = 'a';

	public Board(int theN, String[] theBoard) {
		this.N = theN;
		cells = new ICell[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++){
				char c = Character.toLowerCase(theBoard[i*N+j].charAt(0));
				if(i == 0) {
					if(j == 0){
						cells[i][j] = new NorthWestCell(i,j, c);						
					}
					else if(j == N-1) {
						cells[i][j] = new SouthWestCell(i,j,c);
					}
					else {
						cells[i][j] = new WestCell(i,j,c);
					}
				}
				else if(i == N-1) {
					if(j == 0){
						cells[i][j] = new NorthEastCell(i,j, c);						
					}
					else if(j == N-1) {
						cells[i][j] = new SouthEastCell(i,j,c);
					}
					else {
						cells[i][j] = new EastCell(i,j,c);
					}
				}
				else if(j == 0) {
					cells[i][j] = new NorthCell(i,j,c);
				}
				else if( j == N-1) {
					cells[i][j] = new SouthCell(i,j,c);
				}
				else {
					cells[i][j] = new CentralCell(i,j,c);
				}
				if(letterList[c-A] == null) {
					letterList[c-A]= new LinkedList<ICell>();
				}
				letterList[c-A].add(cells[i][j]);
			}
		}
		for( int i = 0; i < letterList.length; i++) {
			if(letterList[i] == null){
				letterList[i] = new LinkedList<ICell>();
			}
		}
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

	public List<ICell> getStartingPoints(int tlX, int tlY, int brX, int brY) {
		List<ICell> list = new ArrayList<ICell>();
		for(int i = tlX; i < brX; i++ ) {
			for(int j = tlY; j < brY; j++) {
				list.add(cells[i][j]);
			}
		}
		return list;
	}
	private class NorthWestCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public NorthWestCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x+1][y]);  	// 	E
			neighbors.add(cells[x][y+1]);   // 	S
			neighbors.add(cells[x+1][y+1]); // 	SE
		}
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId);	
			}

			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}
	private class NorthCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public NorthCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y]);   // W
			neighbors.add(cells[x+1][y]);   // E
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]);   // S
			neighbors.add(cells[x+1][y+1]); // SE
			
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y]);   // W
			neighbors.add(cells[x+1][y]);   // E
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]);   // S
			neighbors.add(cells[x+1][y+1]); // SE
		}
		
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors){
				checkAndAddNeighbor(list, cell, threadId);
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}
	private class NorthEastCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public NorthEastCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S

		}
		
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId);	
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}
	}
	private class EastCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public EastCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x-1][y+1]); // SW
			neighbors.add(cells[x][y+1]); // S
		}
		
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId);	
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}
	private class SouthEastCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public SouthEastCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x-1][y]); // W
		}

		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId);
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}
	}
	private class SouthCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public SouthCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x+1][y-1]); // NE
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x+1][y]); // E
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]); // NW
			neighbors.add(cells[x][y-1]); // N
			neighbors.add(cells[x+1][y-1]); // NE
			neighbors.add(cells[x-1][y]); // W
			neighbors.add(cells[x+1][y]); // E
		}
		
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId); 	// 	NW				
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}
	private class SouthWestCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public SouthWestCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x][y-1]);//N
			neighbors.add(cells[x+1][y-1]);//NE
			neighbors.add(cells[x+1][y]); //E
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x][y-1]);//N
			neighbors.add(cells[x+1][y-1]);//NE
			neighbors.add(cells[x+1][y]); //E

		}
		
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId); 	// 	N
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}
	private class WestCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public WestCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x][y-1]);
			neighbors.add(cells[x+1][y-1]);
			neighbors.add(cells[x+1][y]);
			neighbors.add(cells[x][y+1]);
			neighbors.add(cells[x+1][y+1]);
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x][y-1]);
			neighbors.add(cells[x+1][y-1]);
			neighbors.add(cells[x+1][y]);
			neighbors.add(cells[x][y+1]);
			neighbors.add(cells[x+1][y+1]);
		}
		
		public char getLetter() {
			return letter;
		}

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId); 	// 	SE				
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}
	private class CentralCell implements ICell {

		private final int x;
		private final int y;
		private final char letter;
		private final String id;
		private volatile int visitors = 0x00000000;
		private List<ICell> neighbors;
		
		public CentralCell(int theX, int theY, char theLetter ) {
			x = theX;
			y = theY;
			letter = theLetter;
			id = ""+letter+theX+theY;
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]);
			neighbors.add(cells[x][y-1]);
			neighbors.add(cells[x+1][y-1]);
			neighbors.add(cells[x-1][y]);
			neighbors.add(cells[x+1][y]);
			neighbors.add(cells[x-1][y+1]);
			neighbors.add(cells[x][y+1]);
			neighbors.add(cells[x+1][y+1]);
			
		}
		
		public void initializeNeighbors() {
			neighbors = new LinkedList<ICell>();
			neighbors.add(cells[x-1][y-1]);
			neighbors.add(cells[x][y-1]);
			neighbors.add(cells[x+1][y-1]);
			neighbors.add(cells[x-1][y]);
			neighbors.add(cells[x+1][y]);
			neighbors.add(cells[x-1][y+1]);
			neighbors.add(cells[x][y+1]);
			neighbors.add(cells[x+1][y+1]);
		}
		
		public char getLetter() {
			return letter;
		}
		
		public List<ICell> getUnvisitedNeighbors(int threadId) {
			List<ICell> list = new LinkedList<ICell>();
			for(ICell cell: neighbors) {
				checkAndAddNeighbor(list, cell, threadId); 	// 	NW				
			}
			return list;
		}

		public List<ICell> getUnvisitedNeighbors(long threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<ICell> getUnvisitedNeighbors(UID threadId) {
			// TODO Auto-generated method stub
			return null;
		}

		public  boolean isVisited(int threadId) {
			return (visitors & threadId) != 0;
		}

		public boolean isVisited(long threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isVisited(UID threadId) {
			// TODO Auto-generated method stub
			return false;
		}

		public synchronized void vacate(int threadId) {
			visitors &= (~threadId);
		}

		public void vacate(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void vacate(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public synchronized void visit(int threadId) {
			visitors |= threadId;
		}

		public void visit(long threadId) {
			// TODO Auto-generated method stub
			
		}

		public void visit(UID threadId) {
			// TODO Auto-generated method stub
			
		}

		public List<ICell> getNeighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

	}

	private static void checkAndAddNeighbor(List<ICell> list, ICell theCell,int threadId) {
		if(!theCell.isVisited(threadId)){
			list.add(theCell);
		}
	}
}
