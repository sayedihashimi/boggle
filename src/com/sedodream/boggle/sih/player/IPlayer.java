/**
 * 
 */
package com.sedodream.boggle.sih.player;

import java.rmi.server.UID;
import java.util.List;

import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.dataStructures.noGeneric.TreeCharacter;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.IBoard;
import com.sedodream.boggle.sih.ICellManager;
import com.sedodream.boggle.sih.IRegion;
import com.sedodream.boggle.sih.exceptions.InvalidPlayerStatusException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface IPlayer extends Runnable{
    /**
     * Sets the board that is to be played.
     * This must be set before the board is played.
     * @param board
     */
    public void setBoard(IBoard board);
    /**
     * Gets the board.
     * @return
     */
    public IBoard getBoard();
    /**
     * Sets the region that is should be played by this player.
     * @param region
     */
    public void setRegion(IRegion region);
    /**
     * Gets the region that is should be played by this player.
     * @return
     */
    public IRegion getRegion();
    /**
     * Sets the result list.
     * @param resultList
     */
    //public void setResultList(ISynchronizedList resultList);
    /**
     * Gets the result list.
     * @return
     */
    public List<String> getResultList();
    
    public void setWordList(IDictionary wordList);
    public void setTreeCharacter(TreeCharacter treeCharacter);
    public IDictionary getWordList();
    
    public void startPlay();
    public void cancelPlay();
    
    public void setMaxLength(int maxLength);
    public int getMaxLength();
    
    public UID getUid();
    /**
     * Sets the cell manager for this player.
     * Not all players will require this, check
     * the implementing class for details.
     * @param cellManager
     */
    public void setCellManager(ICellManager cellManager);
    
}
