/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.List;

/**
 * Represents a region of the Boggle board.
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface IRegion {
//    public IPoint getTopLeft();
//    public void setTopLeft(IPoint topLeft);
//    public IPoint getBottomRight();
//    public void setBottomRight(IPoint bottomRight);
    
    
    public void addCellToRegion(ICell cell);
    public List<ICell>getCellsInRegion();
}