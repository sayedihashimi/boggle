package com.sedodream.boggle.drc.mt;

import java.rmi.server.UID;
import java.util.List;

public class Cell implements ICell {

	private final int x;
	private final int y;
	private final char letter;
	private final String id;
	
	
	public Cell(int theX, int theY, char theLetter ) {
		x = theX;
		y = theY;
		letter = theLetter;
		id = ""+letter+theX+theY;
	}
	
	public char getLetter() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ICell> getNeighbors(int threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ICell> getNeighbors(long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ICell> getNeighbors(UID threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ICell> getUnvisitedNeighbors(int threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ICell> getUnvisitedNeighbors(long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ICell> getUnvisitedNeighbors(UID threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isVisited(int threadId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isVisited(long threadId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isVisited(UID threadId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void vacate(int threadId) {
		// TODO Auto-generated method stub
		
	}

	public void vacate(long threadId) {
		// TODO Auto-generated method stub
		
	}

	public void vacate(UID threadId) {
		// TODO Auto-generated method stub
		
	}

	public void visit(int threadId) {
		// TODO Auto-generated method stub
		
	}

	public void visit(long threadId) {
		// TODO Auto-generated method stub
		
	}

	public void visit(UID threadId) {
		// TODO Auto-generated method stub
		
	}

	class NorthWestNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class NorthNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class NorthEastNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class EastNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class SouthEastNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class SouthNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class SouthWestNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class WestNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	class DefaultNeighborFinder implements INeighborFinder {

		public List<ICell> getUnvisitedNeighbors(int threadId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	interface INeighborFinder {
		List<ICell> getUnvisitedNeighbors(int threadId);
	}
	public List<ICell> getNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initializeNeighbors() {
		// TODO Auto-generated method stub
		
	}
}
