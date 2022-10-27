package com.sedodream.boggle.drc;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author David Ronald Cusick { drcusick@gmail.com }
 *
 */
public interface IBoard {
	/**
	 * Returns a List of cells that contain this letter.
	 * @param letter desired letter.
	 * @return ICells that contain this letter.
	 */
	List<ICell> getCells(char letter);
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
	List<ICell> getCells(int tlX, int tlY, int brX, int brY);
	/**
	 * Returns a map of the unvisited neighbors.
	 * @param theCell
	 * @param visited
	 * @return
	 */
	Map<String,ICell> getUnvisitedNeighbors(ICell theCell, Map<String,ICell> visitedCells);
	/**
	 * Factory Method.
	 * @return a fresh map
	 */
	Map<String, ICell> createMap();
	/**
	 * Clones the specified map.
	 * @param map that needs cloning
	 * @return the Clone
	 */
	Map<String, ICell> cloneMap(Map<String, ICell> map);
}
