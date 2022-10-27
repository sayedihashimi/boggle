/**
 * 
 */
package com.sedodream.boggle.sih;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class Point implements IPoint{
    private static final long serialVersionUID = 7166489417469798753L;
    private int row;
    private int col;

    /**
     * @param row
     * @param col
     */
    public Point(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }
    /**
     * @return the x
     */
    public int getRow() {
        return row;
    }
    /**
     * @param x the x to set
     */
    public void setRow(int x) {
        this.row = x;
    }
    /**
     * @return the y
     */
    public int getCol() {
        return col;
    }
    /**
     * @param y the y to set
     */
    public void setCol(int y) {
        this.col = y;
    }
    public boolean equals(Object obj){
        if(obj==null) {
            return false;
        }
        
        if(!obj.getClass().equals(Point.class)) {
            return false;
        }
        
        Point other = (Point)obj;
        if(this.row!=other.row) {
            return false;
        }
        if(this.col != other.col) {
            return false;
        }
        
        return true;
    }
    public int hashCode(){
        return this.row + this.col;
    }
    
    /*
     * Default values
     */
    public static IPoint EmptyPoint = new Point(Integer.MIN_VALUE,Integer.MIN_VALUE);
}
