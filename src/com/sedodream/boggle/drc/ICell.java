package com.sedodream.boggle.drc;

import java.util.List;

public interface ICell {
	/**
	 * Marks this Coordinate as visited by this thread.
	 * If this thread already visited this coordinate then
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void visit(int threadID);
	/**
	 * Creates a list of the coordinates around this coordinate
	 * that the thread with threadId hasn't visited.
	 * @param threadId 
	 * @return the list of unvisited Coordinates
	 */
	List<ICell> getUnvisitedNeighbors(int threadId);
	/**
	 * Creates a list of the coordinates around this coordinate
	 * @param threadId
	 * @return list of neighboring coordinates.
	 */
	List<ICell> getNeighbors(int threadId);
	/**
	 * The letter at this coordinate
	 * @return the letter
	 */
	char getLetter();
	/**
	 * Returns true if this coordinate has been visited
	 * by this thread.
	 * @return
	 */
	boolean isVisited(int threadId);
	/**
	 * Returns the X value of this cell
	 */
	int getX();
	/**
	 * returns the Y value of this cell.
	 * @return
	 */
	int getY();
}
