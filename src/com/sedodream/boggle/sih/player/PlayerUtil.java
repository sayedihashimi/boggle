/**
 * 
 */
package com.sedodream.boggle.sih.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.sih.IBoard;
import com.sedodream.boggle.sih.ICell;
import com.sedodream.boggle.sih.IRegion;
import com.sedodream.boggle.sih.Point;
import com.sedodream.boggle.sih.Region;
import com.sedodream.boggle.sih.exceptions.BoardDataException;
import com.sedodream.boggle.sih.exceptions.RegionException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class PlayerUtil {
    public static List<ICell> FindCellsInRegion(IRegion region,IBoard board){
        
        //TODO: Remove this method entirely
        return region.getCellsInRegion();
//        
//        
//        if(BoggleMode.DEBUG_MODE){
//            if(region==null){
//                throw new RegionException("region provided is empty");
//            }
//            if(board==null){
//                throw new BoardDataException("board provided is empty");
//            }
//        }
//        
//        List<ICell> regionCells = new ArrayList<ICell>();
//        
//        for(int row = region.getTopLeft().getRow();row<region.getBottomRight().getRow();row++){
//            for(int col = region.getTopLeft().getCol();col<region.getBottomRight().getCol();col++){
//                regionCells.add(board.getCellAt(new Point(row,col)));
//            }
//        }
//        
//        return regionCells;
    }
    /**
     * This will divide the given board into the number of regions specified.
     * Each cell will be assigned to a region in a round-robin fashion.
     * @param numRegions
     * @param board
     * @return
     */
    public static List<IRegion> CreateRegions(int numRegions,IBoard board){
        if(BoggleMode.DEBUG_MODE){
            if(numRegions<=0){
                throw new RegionException(String.format("Number of regions specified is invalid, number: %d", numRegions));
            }
            if(board==null){
                throw new BoardDataException("board provided is empty");
            }
        }
        
        Map<Integer,IRegion>regionMap = new HashMap<Integer, IRegion>();
        //create the regions
        for(int i=0;i<numRegions;i++){
            regionMap.put(i, new Region());
        }
        //now put the cells into the regions
        int boardSize = board.getBoardSize();
        int currentIndex = 0;
        
        for(int row = 0; row<boardSize;row++){
            for(int col = 0;col<boardSize;col++){
                
                int fixedIndex = currentIndex%numRegions;
                regionMap.get(fixedIndex).addCellToRegion(board.getCellAt(new Point(row,col)));
                currentIndex++;
            }
        }
        
        
        List<IRegion>resultList = new ArrayList<IRegion>();
        Iterator<IRegion>iter = regionMap.values().iterator();
        while (iter.hasNext()) {
            resultList.add(iter.next());
        }
        return resultList;
    }
    public static IRegion GetEntireRegion(IBoard board){
        return CreateRegions(1, board).get(0);
    }
    
}
