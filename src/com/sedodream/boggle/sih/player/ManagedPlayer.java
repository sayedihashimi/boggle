/**
 * 
 */
package com.sedodream.boggle.sih.player;

import java.util.List;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;
import com.sedodream.boggle.sih.CellManager;
import com.sedodream.boggle.sih.IBoard;
import com.sedodream.boggle.sih.ICell;
import com.sedodream.boggle.sih.ICellManager;
import com.sedodream.boggle.sih.IRegion;
import com.sedodream.boggle.sih.exceptions.CellManagerException;
import com.sedodream.boggle.sih.exceptions.InvalidPlayerStatusException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class ManagedPlayer extends SimplePlayer {

    @SuppressWarnings("unused")
    private ICellManager cellManager;
    
    /**
     * @param board
     * @param region
     */
    public ManagedPlayer(IBoard board,ICellManager cellManager) {
        super(board,null);
        this.cellManager=cellManager;
    }
    public void setCellManager(ICellManager cellManager){
        this.cellManager=cellManager;
    }
    public void startPlay() {
        if ( BoggleMode.DEBUG_MODE ) {
            if ( this.status != PlayerStatus.NotStarted ) {
                throw new InvalidPlayerStatusException(
                        "Starting a board that was not in 'NotStarted' mode.");
            }
            if(this.cellManager==null){
                throw new CellManagerException("Cell manager is null");
            }
        }

        this.status = PlayerStatus.Playing;

        ICell cellToPlay = null;
        while((cellToPlay=cellManager.getNextCellToPlay())!=null){
            playCell(cellToPlay, new String(new char[]{cellToPlay.getValue()}));
            //TODO: is this faster new String(new char[]{'d'});
        }
    }
}
