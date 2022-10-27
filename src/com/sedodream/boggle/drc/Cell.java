package com.sedodream.boggle.drc;

import java.util.List;

public class Cell implements ICell {
	
	private final char letter;
	private final int X;
	private final int Y;
	private final String id;
	
	public Cell(int theX, int theY, char theLetter) {
		X = theX;
		Y = theY;
		letter = theLetter;
		id = ""+letter+X+Y;
	}
	
	public char getLetter() {
		// TODO Auto-generated method stub
		return letter;
	}

	public int getX() { 
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public String toString() {
		return id;
	}
	
	public List<ICell> getNeighbors(int threadId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
	}

	public List<ICell> getUnvisitedNeighbors(int threadId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public boolean isVisited(int threadId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public void visit(int threadID) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
