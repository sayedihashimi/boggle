package hw4.boggle.board;

import java.util.List;
/**
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
	 * Call this method to proclaim that the 
	 * player is no longer using this cell.
	 * throws RuntimeException.
	 * 
	 * @param threadID
	 * @return the Letter at this coordinate
	 */
	void vacate(int threadId);
}
