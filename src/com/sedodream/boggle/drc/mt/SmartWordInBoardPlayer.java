package com.sedodream.boggle.drc.mt;

import java.util.List;

import com.sedodream.boggle.dataStructures.noGeneric.ArrayDictionary;

public class SmartWordInBoardPlayer implements IPlayer {


	private static int threadIds = 0x00000001;
	private int threadId;
	private IBoard board;
	private ArrayDictionary words;
	private List<String> results;
	private static final char Q = 'q';
	private static final String QU = "qu";
	private static final char U = 'u';
	private int depth = 0;
	private int prefixLen = 0;
	private int wordLen = 0;
	private char[] wordChar;

	public SmartWordInBoardPlayer(IBoard theBoard, ArrayDictionary theWords){
		this(theBoard, theWords, 100);
	}
	
	public SmartWordInBoardPlayer(IBoard theBoard, ArrayDictionary theWords, int maxWordLen){
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
		String word;
		while((word = words.nextWord(wordLen-prefixLen)) != null){
			// must be at least 2 letters long
			// TODO: Might not be any words with less than 2 letters
//			if(word.length() < 2){
//				continue;
//			}
			// find the word
			prefixLen = 0;
			wordLen = word.length();
			word.getChars(0, wordLen, wordChar, 0);
			List<ICell> startingPoints = board.getStartingPoints(wordChar[0]);

			for(ICell startingPoint: startingPoints){
				depth=0;
				if(containsWord(wordChar,0, startingPoint)){
					results.add(word);
					break;
				} else {
					prefixLen = Math.max(prefixLen, depth);
				}
			}
		}
	}
	
	private boolean containsWord(char[] word,int index, ICell atCell) {

		if(index == wordLen) {
			/* TODO: Check to see if this if is ever used.
			 * 
			 * nothing left to find must have found it all
			 * I'd like to skip this case
			 */
			System.out.println("wordLen == 0");
			return true;
		} else if(wordLen - index == 1) {
			/* TODO
			 * try and shortcircuit another recursive call
			 */
			if(atCell.getLetter() == word[index]) {
				this.prefixLen = this.wordLen;
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
					else if((wordLen - index) >= 2 && word[index+1] != U) {
						depth = Math.max(depth, this.wordLen-wordLen+1);
						return false;
					}
					else {
						if((wordLen-index) == 2){
							this.prefixLen = this.wordLen;
							return true;
						}
						depth = Math.max(depth, index+2);
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
					depth = Math.max(depth, index+1);
					atCell.visit(threadId);
					List<ICell> neighbors = atCell.getUnvisitedNeighbors(threadId);
					for(ICell neighbor: neighbors){
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
