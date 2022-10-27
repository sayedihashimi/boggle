package com.sedodream.boggle.drc.mt;

import java.util.List;

/**
 * @author David Ronald Cusick { drcusick@gmail.com }
 *
 * This player takes the given word list in ascending alphabetical order
 * and tries to find it in the board.
 * 
 * TODO: This technique could be improved by pruning after a prefix is 
 * discovered not to exist in the board.  Instead of using a list of all
 * the words in the dictionary iterating over all of them maybe a better 
 * datastructure, or a better iterator should be used.
 */
public class WordInBoardPlayer implements IPlayer {

	private static int threadIds = 0x00000001;
	private int threadId;
	private IBoard board;
	private List<String> words;
	private List<String> results;
	private static final char Q = 'q';
	private static final char U = 'u';
	private static final String QU = "qu";
	private char[] wordChar;
	private int wordLen;
	
	public WordInBoardPlayer(IBoard theBoard, List<String> theWords){
		this(theBoard, theWords, 100);
	}
	
	public WordInBoardPlayer(IBoard theBoard, List<String> theWords, int maxWordLen){
		this.threadId = threadIds;
		threadIds = threadIds << 1;
		if(threadIds == 0) 
			threadIds = 0x00000001;
		this.board = theBoard;
		this.words = theWords;
		this.wordChar = new char[maxWordLen];
	}
	
	public void run() {
		
		// find all words
		for(String word: words){
			// must be at least 2 letters long
//			if(word.length() < 2){
//				continue;
//			}			
			// find the word
			wordLen = word.length();
			word.getChars(0,wordLen,wordChar, 0);
			
			List<ICell> startingPoints = board.getStartingPoints(wordChar[0]);
			
			for(ICell startingPoint: startingPoints){
				if(containsWord( wordChar, 0, startingPoint)){
					results.add(word);
					break;
				}
			}
		}
	}
	
	private boolean containsWord(char[] word, int index, ICell atCell) {
//		int wordLen = word.length();

		if(index == wordLen) {
			/* TODO: Check to see if this if is ever used.
			 * 
			 * nothing left to find must have found it all
			 * I'd like to skip this case
			 */
			return true;
		} else if( wordLen - index == 1) {
			/* TODO
			 * try and shortcircuit another recursive call
			 */
			if(atCell.getLetter() == word[index]) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			char c = word[index];
			if(atCell.getLetter() == c) {
			//---- Match
				if(c == Q){
					if(wordLen - index < 2)
						return false;
					else if((wordLen - index) >= 2 && word[index+1] != U)
						return false;
					else {
						if((wordLen-index) == 2){
							return true;
						}
						atCell.visit(threadId);
						List<ICell> neighbors = atCell.getUnvisitedNeighbors(threadId);
						for(ICell neighbor: neighbors){
							if(containsWord(word, index+2,neighbor)){
								atCell.vacate(threadId);
								return true;
							}
						}
						atCell.vacate(threadId);
						return false;
					}
				}else {
					atCell.visit(threadId);
					List<ICell> neighbors = atCell.getUnvisitedNeighbors(threadId);
					for(ICell neighbor: neighbors){
						// TODO: no more substring... char array and indexes
						if(containsWord(word,index+1,neighbor)){
							atCell.vacate(threadId);
							return true;
						}
					}
					atCell.vacate(threadId);
					return false;
				}
			}else {
			//---- No Match
				return false;
			}
		}
	}
	public List<String> getResults() {
		return results;
	}
	public void setResults(List<String> resultList) {
		this.results = resultList;
	}

}
