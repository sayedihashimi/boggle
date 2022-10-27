/**
 * 
 */
package com.sedodream.boggle.sih;

import java.io.Serializable;

/**
 * Represents a point on the Boggle board.
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface IPoint extends Serializable {
    public int getRow();
    public void setRow(int row);
    
    public int getCol();
    public void setCol(int col);
}
