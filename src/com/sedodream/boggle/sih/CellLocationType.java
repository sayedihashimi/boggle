/**
 * 
 */
package com.sedodream.boggle.sih;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
//public enum CellLocationType {
//    Unknown,        //this is the default until initalized
//    NorthEast,
//    NorthCenter,
//    NorthWest,
//    East,
//    Center,
//    West,
//    SouthEast,
//    SouthCenter,
//    SouthWest
//}
public class CellLocationType implements ICellLocationType{
    private boolean onNorth;
    private boolean onEast;
    private boolean onWest;
    private boolean onSouth;
    
    public CellLocationType(){
        this.onNorth = false;
        this.onEast = false;
        this.onWest = false;
        this.onSouth = false;
    }
    /**
     * @param onNorth
     * @param onEast
     * @param onWest
     * @param onSouth
     */
    public CellLocationType(boolean onNorth, boolean onEast, boolean onWest, boolean onSouth) {
        this();
        this.onNorth = onNorth;
        this.onEast = onEast;
        this.onWest = onWest;
        this.onSouth = onSouth;
    }
    
    
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#isOnEast()
     */
    public boolean isOnEast() {
        return onEast;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#setOnEast(boolean)
     */
    public void setOnEast(boolean onEast) {
        this.onEast = onEast;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#isOnNorth()
     */
    public boolean isOnNorth() {
        return onNorth;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#setOnNorth(boolean)
     */
    public void setOnNorth(boolean onNorth) {
        this.onNorth = onNorth;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#isOnSouth()
     */
    public boolean isOnSouth() {
        return onSouth;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#setOnSouth(boolean)
     */
    public void setOnSouth(boolean onSouth) {
        this.onSouth = onSouth;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#isOnWest()
     */
    public boolean isOnWest() {
        return onWest;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.sih.ICellLocationType#setOnWest(boolean)
     */
    public void setOnWest(boolean onWest) {
        this.onWest = onWest;
    }

    
    
}