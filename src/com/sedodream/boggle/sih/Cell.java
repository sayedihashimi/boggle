/**
 * 
 */
package com.sedodream.boggle.sih;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.CommonsSynchronizedList;
import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.sih.exceptions.InvalidPlayerStatusException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class Cell implements ICell {
    private IBoard board;
    private List<ICell>neighboors;
    private IPoint location;
    private char value;
    private ICellLocationType cellLocationType;
    private ISynchronizedList playerList;
    
    
    
    public Cell(IBoard board,IPoint location){
        this.board = board;
        this.location=location;
        this.value=board.getValueAt(location);
        playerList = new CommonsSynchronizedList();
    }
    public IBoard getBoard() {
        return this.board;
    }
    public List<ICell> getNeighbors() {
        if(this.neighboors!=null){
            return this.neighboors;
        }
        
        int row = this.location.getRow();
        int col = this.location.getCol();
        
        List<ICell>nCells = new ArrayList<ICell>();
        ICellLocationType locType = this.getCellLocationType();
        
        /*
         * Get the rectangular neighboors
         */
        if(!locType.isOnNorth()){
            //add the north cell to the list
            IPoint nLoc = new Point(row-1,col);
            nCells.add(board.getCellAt(nLoc));
        }
        if(!locType.isOnSouth()){
            IPoint sLoc = new Point(row+1,col);
            nCells.add(board.getCellAt(sLoc));
        }
        if(!locType.isOnWest()){
            IPoint wLoc = new Point(row,col-1);
            nCells.add(board.getCellAt(wLoc));
        }
        if(!locType.isOnEast()){
            IPoint eLoc = new Point(row,col+1);
            nCells.add(board.getCellAt(eLoc));
        }
        
        /*
         * Diagnoal neighboors
         */
        if(locType.isOnNorth()){
            if(locType.isOnEast()){         //NE
                IPoint swLoc = new Point(row+1,col-1);
                nCells.add(board.getCellAt(swLoc));
            }
            else if(locType.isOnWest()){    //NW
                IPoint seLoc = new Point(row+1,col+1);
                nCells.add(board.getCellAt(seLoc));
            }
            else{
                //add both
                IPoint swLoc = new Point(row+1,col-1);
                nCells.add(board.getCellAt(swLoc));
                
                IPoint seLoc = new Point(row+1,col+1);
                nCells.add(board.getCellAt(seLoc));
            }
        }
        else if(locType.isOnSouth()){
            if(locType.isOnEast()){         //SE
                IPoint neLoc = new Point(row-1,col-1);
                nCells.add(board.getCellAt(neLoc));
            }
            else if(locType.isOnWest()){    //SW
                IPoint nwLoc = new Point(row-1,col+1);
                nCells.add(board.getCellAt(nwLoc));
            }
            else{
                //add both
                IPoint neLoc = new Point(row-1,col-1);
                nCells.add(board.getCellAt(neLoc));
                
                IPoint nwLoc = new Point(row-1,col+1);
                nCells.add(board.getCellAt(nwLoc));
            }
        }
        else if(locType.isOnEast()){
            //Not on North or South add all directions
            IPoint neLoc = new Point(row-1,col-1);
            nCells.add(board.getCellAt(neLoc));
            
            IPoint seLoc = new Point(row+1,col-1);
            nCells.add(board.getCellAt(seLoc));
        }
        else if(locType.isOnWest()){
            IPoint nwLoc = new Point(row-1,col+1);
            nCells.add(board.getCellAt(nwLoc));
            
            IPoint swLoc = new Point(row+1,col+1);
            nCells.add(board.getCellAt(swLoc));
        }
        else{
            //Not on North,South,East or West add all directions
            IPoint swLoc = new Point(row+1,col-1);
            nCells.add(board.getCellAt(swLoc));
            
            IPoint seLoc = new Point(row+1,col+1);
            nCells.add(board.getCellAt(seLoc));
            
            IPoint neLoc = new Point(row-1,col-1);
            nCells.add(board.getCellAt(neLoc));
            
            IPoint nwLoc = new Point(row-1,col+1);
            nCells.add(board.getCellAt(nwLoc));
        }
        
        this.neighboors=nCells;
        
        return this.neighboors;
    }
    public IPoint getLocation() {
        return this.location;
    }
    public char getValue() {
        return this.value;
    }
    public ICellLocationType getCellLocationType(){
        if(this.cellLocationType!=null){
            return this.cellLocationType;
        }
        
        int row = this.location.getRow();
        int col = this.location.getCol();
        int boardSize = this.getBoard().getBoardSize();
        
        boolean onNorth = (row==0?true:false);
        boolean onSouth = (row==boardSize-1?true:false);
        boolean onWest = (col==0?true:false);
        boolean onEast = (col==boardSize-1?true:false);
        
        this.cellLocationType = new CellLocationType(onNorth,onEast,onWest,onSouth);
        
        return cellLocationType;
    }

    @SuppressWarnings("unchecked")
    public void addCurrentlyPlaying(UID playerId){
        if(!this.playerList.contains(playerId)){
            this.playerList.add(playerId);
        }
    }
    public void removeCurrentlyPlaying(UID playerId){
        if(this.playerList.contains(playerId)){
            this.playerList.remove(playerId);
        }
    }
    public boolean isPlayerCurrentlyPlaying(UID playerId){
        return this.playerList.contains(playerId);
    }
}
