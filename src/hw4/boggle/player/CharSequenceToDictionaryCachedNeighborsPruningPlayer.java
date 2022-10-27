package hw4.boggle.player;

import java.util.List;
import hw4.boggle.*;
import hw4.boggle.board.*;
import hw4.boggle.dictionary.*;
import hw4.boggle.player.*;


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
	private IDictionary dictionary;
	private List<String> results;
	private static final char Q = 'q';
	private static final char U = 'u';
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
	public CharSequenceToDictionaryCachedNeighborsPruningPlayer(IDictionary theDictionary, IBoard theBoard) {
		this(theDictionary, theBoard, 100);
	}
	
	public CharSequenceToDictionaryCachedNeighborsPruningPlayer(IDictionary theDictionary, IBoard theBoard, int maxWordSize) {
		this.dictionary = theDictionary;
		this.board = theBoard;
		this.threadId = threadIds;
		threadIds = threadIds << 1;
		if(threadIds == 0) 
			threadIds = 0x00000001;
		this.charWord = new char[maxWordSize];
		results = PlayerUtil.makeWordResultList();
	}
	
	public void run() {
		
		ICell atCell = null;
		while((atCell = board.getNextCell()) != null) {
			scour(charWord, 0, atCell);
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
		ISearchResult result = dictionary.findWord(word.toCharArray());
		if(result.getContains()) {
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

