/**
 * 
 */
package com.sedodream.boggle.sih;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface ICellManager {
    /**
     * Gets the next cell that needs to be played.
     * If none the null will be returned.
     * @return
     */
    public ICell getNextCellToPlay();
}
