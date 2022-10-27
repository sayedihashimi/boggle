/**
 * 
 */
package com.sedodream.boggle.sih.player;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;
import com.sedodream.boggle.dataStructures.noGeneric.TreeCharacter;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.IBoard;
import com.sedodream.boggle.sih.ICell;
import com.sedodream.boggle.sih.ICellManager;
import com.sedodream.boggle.sih.IRegion;
import com.sedodream.boggle.sih.exceptions.CellManagerException;
import com.sedodream.boggle.sih.exceptions.InvalidPlayerStatusException;
import com.sedodream.boggle.sih.exceptions.NotImplementedYetException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class SimplePlayer implements IPlayer {
    private IBoard            board;
    private IRegion           region;
    protected PlayerStatus      status;
    protected IDictionary       wordList;
    protected TreeCharacter treeCharacter;
    
    protected int               maxLength;
    protected List<String> resultList;
    protected UID uid;
    
    
    public SimplePlayer(IBoard board, IRegion region) {
        uid = new UID();
        
        this.status = PlayerStatus.NotStarted;
        this.setBoard(board);
        this.setRegion(region);
        this.maxLength=100;
        this.resultList= new ArrayList<String>();
    }

    /**
     * @return the board
     */
    public IBoard getBoard() {
        return board;
    }

    /**
     * @param board
     *            the board to set
     */
    public void setBoard(IBoard board) {
        this.board = board;
    }

    /**
     * @return the region
     */
    public IRegion getRegion() {
        return region;
    }

    /**
     * @param region
     *            the region to set
     */
    public void setRegion(IRegion region) {
        this.region = region;
    }

    public void startPlay() {
        if ( BoggleMode.DEBUG_MODE ) {
            if ( this.status != PlayerStatus.NotStarted ) {
                throw new InvalidPlayerStatusException(
                        "Starting a board that was not in 'NotStarted' mode.");
            }
        }

        this.status = PlayerStatus.Playing;

        List<ICell> cellsInRegion = PlayerUtil.FindCellsInRegion(this
                .getRegion(), this.getBoard());
        for(int i =0;i<cellsInRegion.size();i++){
            ICell cell = cellsInRegion.get(i);
            playCell(cell,new String(new char[]{cell.getValue()}));
            //playCell(cell, ""+cell.getValue());
        }
    }

    /**
     * @return the wordList
     */
    public IDictionary getWordList() {
        return wordList;
    }

    /**
     * @param wordList
     *            the wordList to set
     */
    public void setWordList(IDictionary wordList) {
        this.wordList = wordList;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /*
     * Non public methods
     */
    // protected void playCell() {
    //        
    // }
    protected void playCell(ICell cell, String strSoFar) {
        if(BoggleMode.DEBUG_MODE){
            long threadId = java.lang.Thread.currentThread().getId();
            String str = String.format("\t[%s]strSoFar=%s cell:%s",threadId, strSoFar,cell.getValue());
            System.out.println(str);
        }
        cell.addCurrentlyPlaying(this.uid);
        // find all neighboors
        List<ICell> neighboors = cell.getNeighbors();
        //strSoFar = strSoFar + cell.getValue();
        if(strSoFar.length()>maxLength)
        {
            if(BoggleMode.DEBUG_MODE){
                String str = String.format("\tString exceeds max length returning");
                System.out.println(str);
            }    
            return; //end the recursion
        }
        
        for (int i = 0; i < neighboors.size(); i++) {
            ICell nCell = neighboors.get(i);
            if(nCell.isPlayerCurrentlyPlaying(this.uid)){
                //don't back track
                continue;
            }
            
            String nStr = strSoFar + nCell.getValue();
            // see if its in the dictionary
            SearchResult result = wordList.findWord(nStr);
            
            if(BoggleMode.DEBUG_MODE){
                System.out.printf("\t\t%s Result: %s\n",nStr,result);
            }
            
            if ( result.getContains() ) {
                // add this to the result list
                /*if ( !resultList.contains(nStr) )*/ {
                    resultList.add(nStr);
                }
            }
            if(result.getHasChildren()){
                if(BoggleMode.DEBUG_MODE){
                    System.out.printf("\t\t\tBeing play for %s\n", nStr);
                }
                playCell(nCell, nStr);
            }
        }
        cell.removeCurrentlyPlaying(this.uid);
    }

    public void cancelPlay() {
        this.status = PlayerStatus.Canceled;
    }
    public List<String> getResultList() {
        return this.resultList;
    }
    public void setDictionary(IDictionary wordList) {
        this.wordList = wordList;
    }

    public void run() {
        
        this.startPlay();
    }
    public UID getUid(){
        return this.uid;
    }
    public void setCellManager(ICellManager cellManager){
        throw new CellManagerException("Simple player doesn't use cell manager");
    }

    public void setTreeCharacter(TreeCharacter treeCharacter) {
        this.treeCharacter=treeCharacter;
    }
}
