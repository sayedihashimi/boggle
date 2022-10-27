/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.CommonsSynchronizedList;
import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.sih.exceptions.BoardDataException;
import com.sedodream.boggle.sih.player.PlayerUtil;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class CellManager implements ICellManager{
    private AtomicInteger index;
//    private ICell[]allCells;
//    private String intLock;
//    private int cellIndex;
    
    private ISynchronizedList syncList;
    
    @SuppressWarnings("unchecked")
    public CellManager(IBoard board){
        if(BoggleMode.DEBUG_MODE){
            if(board==null){
                throw new BoardDataException("Board provided was null");
            }
        }
//        intLock = "";
//        index = new AtomicInteger(0);
//        cellIndex=-1;
        syncList = new CommonsSynchronizedList();
        
        IRegion entireRegion = PlayerUtil.GetEntireRegion(board);
//        List<ICell>allCellsList = entireRegion.getCellsInRegion();
//        allCells = new ICell[allCellsList.size()];
//        entireRegion.getCellsInRegion().toArray(allCells);
        List<ICell>allCells = entireRegion.getCellsInRegion();
        for(int i =0;i<allCells.size();i++){
            //syncList.addAll(allCells);
            syncList.add(allCells.get(i));
        }
    }
    
    public ICell getNextCellToPlay(){
//        synchronized (intLock){
//            cellIndex++;
//        }
//        if(cellIndex<allCells.length){
//            return allCells[cellIndex];
//        }
        
        
//        int realIndex=index.getAndIncrement();
//        if(realIndex<allCells.length){
//            return allCells[realIndex];
//        }
        synchronized (syncList) {
            if(syncList.size()>0){
                ICell cell = (ICell) syncList.remove(0);
                return cell;
            }    
        }
        
        return null;
    }
    
    
    
    
    
}
