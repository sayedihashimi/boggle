/**
 * 
 */
package com.sedodream.boggle.sih;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface IBoard {
    /**
     * Gets the board size. Which is the number of
     * cells in one direction. Both directions have
     * equal number of cells.
     * @return
     */
    public int getBoardSize();
    /**
     * Gets the cell at the specified location.
     * @param location
     * @return
     */
    public char getValueAt(IPoint location);
    /**
     * Gets the ICell at the location provided.
     * @param location
     * @return
     */
    public ICell getCellAt(IPoint location);
}
