/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class Region implements IRegion {
    /*
     * Fields
     */
    private IPoint topLeft;
    private IPoint bottomRight;
    private List<ICell>cells;
    /*
     * Constructors
     */
    /*
     * TODO: Remove this constructor?
     */
    public Region(){
        super();
        this.cells = new ArrayList<ICell>();
        this.setTopLeft(Point.EmptyPoint);
        this.setBottomRight(Point.EmptyPoint);
    }
    /**
     * @param topLeft
     * @param bottomRight
     */
    public Region(IPoint topLeft, IPoint bottomRight) {
        super();
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    /**
     * @return the bottomRight
     */
    public IPoint getBottomRight() {
        return bottomRight;
    }
    /**
     * @param bottomRight the bottomRight to set
     */
    public void setBottomRight(IPoint bottomRight) {
        this.bottomRight = bottomRight;
    }
    /**
     * @return the topLeft
     */
    public IPoint getTopLeft() {
        return topLeft;
    }
    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeft(IPoint topLeft) {
        this.topLeft = topLeft;
    }

    public void addCellToRegion(ICell cell) {
        cells.add(cell);
    }

    public List<ICell> getCellsInRegion() {
        return this.cells;
    }
    
    
    
    
}
