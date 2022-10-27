package hw4.boggle.board;

import java.util.List;

public interface IBoard {
	/**
	 * Returns a List of cells that contain this letter.
	 * @param letter desired letter.
	 * @return ICells that contain this letter.
	 */
	List<ICell> getStartingPoints(char letter);
	/**
	 * Returns a List of cells that are in the specified 
	 * region.  This list includes the cell at tlX,tlY
	 * as well as the cell at brX,brY.
	 * @param tlX
	 * @param tlY
	 * @param brX
	 * @param brY
	 * @return List of ICells
	 */
	List<ICell> getStartingPoints(int tlX, int tlY, int brX, int brY);
	/**
	 * 
	 */
	public ICell getNextCell();

}
