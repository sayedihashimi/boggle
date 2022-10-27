package com.sedodream.boggle.drc.mt;

import java.util.List;
import java.rmi.server.UID;
import com.sedodream.boggle.drc.mt.ICell;
/**
 * @author David Ronald Cusick { drcusick@gmail.com }
 * 
 * When using the integer technique to keep track of who
 * has visited the cell try using a volatile and creating
 * another method to the interface that returns a boolean.
 * that says whether the op was successful, if the op wasn't 
 * try again.  this way the visit method can be using a copy
 * on write which would be faster than always having to 
 * synchronize.
 */
public interface ICell {
	/**
	 * Marks this Coordinate as visited by this thread.
	 * If this thread already visited this coordinate then
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void visit(int threadId);
	/**
	 * Marks this Coordinate as visited by this thread.
	 * If this thread already visited this coordinate then
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void visit(long threadId);
	/**
	 * Marks this Coordinate as visited by this thread.
	 * If this thread already visited this coordinate then
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void visit(UID threadId);
	/**
	 * Creates a list of the coordinates around this coordinate
	 * that the thread with threadId hasn't visited.
	 * @param threadId 
	 * @return the list of unvisited Coordinates
	 */
	List<ICell> getUnvisitedNeighbors(int threadId);
	/**
	 * Creates a list of the coordinates around this coordinate
	 * that the thread with threadId hasn't visited.
	 * @param threadId 
	 * @return the list of unvisited Coordinates
	 */
	List<ICell> getUnvisitedNeighbors(long threadId);
	/**
	 * Creates a list of the coordinates around this coordinate
	 * that the thread with threadId hasn't visited.
	 * @param threadId 
	 * @return the list of unvisited Coordinates
	 */
	List<ICell> getUnvisitedNeighbors(UID threadId);
	/**
	 * Gets the list of neighbors adjacent to this cell.
	 * Must check if they have been visited.
	 * 
	 */
	List<ICell> getNeighbors();
	/**
	 * initializes the neighbor list.
	 * Call this method only once.
	 */
	void initializeNeighbors();
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
	 * Returns true if this coordinate has been visited
	 * by this thread.
	 * @return
	 */
	boolean isVisited(long threadId);
	/**
	 * Returns true if this coordinate has been visited
	 * by this thread.
	 * @return
	 */
	boolean isVisited(UID threadId);
	/**
	 * Call this method to proclaim that the 
	 * player is no longer using this cell.
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void vacate(int threadId);
	/**
	 * Call this method to proclaim that the 
	 * player is no longer using this cell.
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void vacate(long threadId);
	/**
	 * Call this method to proclaim that the 
	 * player is no longer using this cell.
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void vacate(UID threadId);

}
