/**
 * 
 */
package com.sedodream.boggle.sih;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface ICellLocationType {

    /**
     * @return the onEast
     */
    public abstract boolean isOnEast();

    /**
     * @param onEast the onEast to set
     */
    public abstract void setOnEast(boolean onEast);

    /**
     * @return the onNorth
     */
    public abstract boolean isOnNorth();

    /**
     * @param onNorth the onNorth to set
     */
    public abstract void setOnNorth(boolean onNorth);

    /**
     * @return the onSouth
     */
    public abstract boolean isOnSouth();

    /**
     * @param onSouth the onSouth to set
     */
    public abstract void setOnSouth(boolean onSouth);

    /**
     * @return the onWest
     */
    public abstract boolean isOnWest();

    /**
     * @param onWest the onWest to set
     */
    public abstract void setOnWest(boolean onWest);

}