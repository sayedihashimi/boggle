package com.sedodream.boggle.drc;

import java.io.*;
import java.util.*;

import org.junit.Test;
import com.sedodream.boggle.sih.player.*;

/**
 * @author David Ronald Cusick { drcusick@gmail.com }
 *
 * This class tests the WordListFinder.
 */
public class TestWordInBoardPlayer {
	private static final String DICTIONARY = "./files/word.list";
	private static final String BOARD = "./files/board4x4.txt";
	private static final String BOARD_RESULTS = "./output/board4x4.results.st";
	/**
	 * This method tests the WordListFinder.
	 */
	@Test
	public void testWordInBoardPlayer() throws Exception {
		System.out.println("Single Threaded Approach:");
		long startTotal = System.nanoTime();
		List<String> words = readWordsFromFile(DICTIONARY);
		IBoard board = readBoardFromFile(BOARD);
		IPlayer player = new WordInBoardPlayer(board, words);
		player.setResults(new LinkedList<String>());
		Thread playerThread = new Thread(player);
		long start = System.nanoTime();
		playerThread.start();
		//---- Wait for player to finish
		playerThread.join();
		long end = System.nanoTime();
		System.out.println("Single Threaded Approach took total: " +(end- start)+"ns or "+((end- start)/1000000)+"ms");
		writeResultsToFile(player.getResults(), BOARD_RESULTS);
		long endTotal = System.nanoTime();
		System.out.println("Single Threaded Approach took total: " +(endTotal- startTotal)+"ns or "+((endTotal- startTotal)/1000000)+"ms");
	}
	

	private List<String> readWordsFromFile(String dictionaryFileName) throws Exception {
		List<String> words = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(dictionaryFileName));
		String s =null;
		while((s = reader.readLine()) != null)
			words.add(s);
		reader.close();
		return words;
	}
	
	private IBoard readBoardFromFile(String boardFileName) throws Exception {
		List<String> lines = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(boardFileName));
		String s = null;
		while((s = reader.readLine()) != null)
			lines.add(s);
		reader.close();
		String[] letters = lines.toArray(new String[0]);
		int N = (int)Math.sqrt(letters.length);
		return new Board(N,letters);
	}
	private void writeResultsToFile(List<String> results, String outputFileName) throws Exception {
		PrintWriter writer = new PrintWriter(new FileWriter(outputFileName));
		Collections.sort(results);
		for(String s: results) {
			writer.println(s);
		}
		writer.close();
	}
	
	@Test
	public void testSimplePlayer() throws Exception {
		
	}
}
