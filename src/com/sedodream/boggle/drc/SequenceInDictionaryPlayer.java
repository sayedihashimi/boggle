package com.sedodream.boggle.drc;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;

/**
 * 
 * @author David Ronald Cusick { drcusick@gmail.com }
 * 
 * This player takes a region of cells in the board as starting points
 * expanding in a dfs outward from each creating sequences that are
 * looked up in the dictionary.
 *
 */
public class SequenceInDictionaryPlayer implements IPlayer {

	private IBoard board;
	private IDictionary dictionary;
	private int tlX,tlY,brX,brY;
	private List<String> results;
	/**
	 * Creates a SequenceFinder player that tries to find sequences in the
	 * dictionary starting from the points specified by the region.
	 * If this player should cover the whole board just pass in 
	 *  1, 1, board.width, board.height.
	 * 
	 * @param dictionary the dictionary of words
	 * @param board complete board
	 * @param tlx top left corner x coord
	 * @param tly top left corner y coord
	 * @param brx bottom right corner x coord
	 * @param bry bottom right corner y coord
	 */
	public SequenceInDictionaryPlayer(IDictionary theDictionary, IBoard theBoard, int theTlx, int theTly, int theBrx, int theBry) {
		this.dictionary = theDictionary;
		this.board = theBoard;
		this.tlX = theTlx;
		this.tlY = theTly;
		this.brX = theBrx;
		this.brY = theBry;
	}
	
	public void run() {
		
		// get all starting points
		ICell atCell;
		StringBuilder sequence;
		Map<String,ICell> visitedCells;
		for(Iterator<ICell> startingCellsIter = board.getCells(tlX, tlY, brX, brY).iterator();startingCellsIter.hasNext();) {
			// get starting cell
			atCell = startingCellsIter.next();
			sequence = new StringBuilder();
			visitedCells = board.createMap();
			// create word containing letter at starting cell
			scour(sequence, atCell, visitedCells);
		}
	}

	private void scour(StringBuilder sequence, ICell atCell, Map<String,ICell> visitedCells ) {
		
		// visit cell
		char letter = atCell.getLetter();
		sequence.append((letter == 'q'? "qu": letter ));

		// add cell to visited cells
		visitedCells.put(atCell.toString(),atCell);
		
		String word = sequence.toString();

		if(sequence.length() > 1) {
			SearchResult result = dictionary.findWord(word);
			if(result.getContains()) {
				results.add(word);
			}
			if(!result.getHasChildren()){
				return;
			}
		}

		Map<String, ICell> neighbors = board.getUnvisitedNeighbors(atCell, visitedCells);
		ICell neighborCell;
		for(Iterator<String> neighborIter = neighbors.keySet().iterator(); neighborIter.hasNext();){
			neighborCell = neighbors.get(neighborIter.next());
			scour(new StringBuilder(word), neighborCell, board.cloneMap(visitedCells));
		}
		
	}

    /* (non-Javadoc)
     * @see com.sedodream.boggle.drc.IPlayer#getResults()
     */
    public List<String> getResults() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.drc.IPlayer#setResults(java.util.List)
     */
    public void setResults(List<String> resultList) {
        // TODO Auto-generated method stub
        
    }
}
