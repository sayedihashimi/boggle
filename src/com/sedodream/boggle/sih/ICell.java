/**
 * 
 */
package com.sedodream.boggle.sih;

import java.rmi.server.UID;
import java.util.List;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface ICell {
    /**
     * Gets the board that is used.
     * @return
     */
    public IBoard getBoard();
    /**
     * Gets the point that this cell is on the board
     * @return
     */
    public IPoint getLocation();
    /**
     * Gets the character located at this point
     * @return
     */
    public char getValue();
    /**
     * Gets all the neighboors of this cell.
     * @return
     */
    public List<ICell>getNeighbors();
    
    public ICellLocationType getCellLocationType();
    
    public void addCurrentlyPlaying(UID playerId);
    public void removeCurrentlyPlaying(UID playerId);
    public boolean isPlayerCurrentlyPlaying(UID playerId);
}
