package com.sedodream.boggle.drc;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
	
	private IBoard board;
	private List<String> words;
	private List<String> results;
	private static final char Q = 'q';
	private static final String QU = "qu";
	private static final char U = 'u';
	
	public WordInBoardPlayer(IBoard theBoard, List<String> theWords){
		this.board = theBoard;
		this.words = theWords;
	}
	
	public void run() {
		
		// find all words
		for(String word: words){
			// must be at least 2 letters long
			if(word.length() < 2){
				continue;
			}
			
			// find the word
			char firstLetter = word.charAt(0);
			List<ICell> startingCells = board.getCells(firstLetter);
			
			for(Iterator<ICell> startCellIter = startingCells.iterator(); startCellIter.hasNext();){
				if(newContainsWord(word, startCellIter.next(), board.createMap())){
					results.add(word);
					break;
				}
			}
		}
	}
	
	private boolean newContainsWord(String word, ICell atCell, Map<String,ICell> visitedCells) {
		int wordLen = word.length();

		if(wordLen == 0) {
			/*
			 * nothing left to find must have found it all
			 * I'd like to skip this case
			 */
			return true;
		} else if(wordLen == 1) {
			/* TODO
			 * try and shortcircuit another recursive call
			 */
			if(atCell.getLetter() == word.charAt(0)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			char c = word.charAt(0);
			if(atCell.getLetter() == c) {
			//---- Match
				if(c == Q){
					if(wordLen < 2)
						return false;
					else if(wordLen >= 2 && word.charAt(1) != U)
						return false;
					else {
						if(wordLen == 2){
							return true;
						}
						Map<String,ICell> neighbors = board.getUnvisitedNeighbors(atCell, visitedCells);
						visitedCells.put(atCell.toString(), atCell);
						for(Iterator iter = neighbors.keySet().iterator(); iter.hasNext();){
							ICell neighbor = neighbors.get(iter.next());
							if(newContainsWord(word.substring(2),neighbor,visitedCells)){
								visitedCells.remove(atCell.toString());
								return true;
							}
						}
						visitedCells.remove(atCell.toString());
						return false;
					}
				}else {
					Map<String,ICell> neighbors = board.getUnvisitedNeighbors(atCell, visitedCells);
					visitedCells.put(atCell.toString(), atCell);
					for(Iterator iter = neighbors.keySet().iterator(); iter.hasNext();){
						ICell neighbor = neighbors.get(iter.next());
						if(newContainsWord(word.substring(1),neighbor,visitedCells)){
							visitedCells.remove(atCell.toString());
							return true;
						}
					}
					visitedCells.remove(atCell.toString());
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
