package com.sedodream.boggle.drc.mt;

import java.util.List;

import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;
import com.sedodream.boggle.dataStructures.noGeneric.TreeCharacter;
import com.sedodream.boggle.drc.IDictionary;
/**
 * 
 * @author David Ronald Cusick { drcusick@gmail.com }
 * 
 * This player takes a region of cells in the board as starting points
 * expanding in a dfs outward from each creating sequences that are
 * looked up in the dictionary.
 *
 */
public class CharSequenceToDictionaryCachedNeighborsPruningPlayer implements IPlayer{
	private static int threadIds = 0x00000001;
	private int threadId;
	private IBoard board;
	private TreeCharacter dictionary;
	private int tlX,tlY,width,height;
	private List<String> results;
	private static final char Q = 'q';
	private static final char U = 'u';
	private static final String QU = "qu";
	private char[] charWord;
	
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
	public CharSequenceToDictionaryCachedNeighborsPruningPlayer(TreeCharacter theDictionary, IBoard theBoard, int theTlx, int theTly, int width, int height) {
		this(theDictionary, theBoard, theTlx, theTly, width, height, 100);
	}
	
	public CharSequenceToDictionaryCachedNeighborsPruningPlayer(TreeCharacter theDictionary, IBoard theBoard, int theTlx, int theTly, int width, int height, int maxWordSize) {
		this.dictionary = theDictionary;
		this.board = theBoard;
		this.tlX = theTlx;
		this.tlY = theTly;
		this.width = width;
		this.height = height;
		this.threadId = threadIds;
		threadIds = threadIds << 1;
		this.charWord = new char[maxWordSize]; 
	}
	
	public void run() {
		
		// get all starting points
		List<ICell> startingPoints = board.getStartingPoints(tlX, tlY, width, height);
		for(ICell startingPoint: startingPoints) {
			// create word containing letter at starting cell
			scour(charWord, 0, startingPoint);
		}
	}
	/*
	 * Find all the sequences starting from this cell that are in
	 * the dictionary.
	 * 
	 * index = next available slot
	 */
	private void scour(char[] sequence, int index, ICell atCell) {
		
		char letter = atCell.getLetter();
		int letterLen = 1;
		if(letter == Q){
			sequence[index]=Q;sequence[index+1]=U;
			letterLen = 2;
		} else {
			sequence[index] = letter;
		}
		String word = new String(sequence,0,index+letterLen);
		SearchResult result = dictionary.findWord(word.toCharArray());
		if(result.getContains()) {
//			if(!results.contains(word))
				results.add(word);
		}
		if(!result.getHasChildren()){
			return;
		}

		atCell.visit(threadId);
		List<ICell> neighbors = atCell.getNeighbors();
		for(ICell neighbor: neighbors) {
			if(!neighbor.isVisited(threadId))
				scour(sequence, index + letterLen, neighbor);
		}
		atCell.vacate(threadId);
	}

    /* (non-Javadoc)
     * @see com.sedodream.boggle.drc.IPlayer#getResults()
     */
    public List<String> getResults() {
    	return results;
    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.drc.IPlayer#setResults(java.util.List)
     */
    public void setResults(List<String> resultList) {
    	results = resultList;
    }
}

