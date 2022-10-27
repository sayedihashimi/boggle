package com.sedodream.boggle.drc.mt;

import org.junit.*;

import com.sedodream.boggle.dataStructures.noGeneric.ArrayDictionary;

import java.io.*;
import java.util.*;

public class TestMTSmartWordInBoardPlayer {
	private static final String DICTIONARY = "./files/word.list";
	private static final String BOARD = "./files/board%dx%d.txt";
	private static final String BOARD_RESULTS_ST = "./output/board%dx%d.results.drc.sw2b.st";
	private static final String BOARD_RESULTS_MT = "./output/board%dx%d.results.drc.mt";
	private static final int MAX_SIZE = 100;

	/**
	 * This method tests the WordListFinder.
	 */
	@Test
	public void testWordInBoardPlayer() throws Exception {
		for (int i = 4; i < MAX_SIZE; i *= 2) {
			System.out.println("Word to Board Single Threaded Approach " + i
					+ "x" + i + ":");
			long startTotal = System.nanoTime();
			ArrayDictionary words = new ArrayDictionary();
			IBoard board = readBoardFromFile(String.format(BOARD, i, i));
			IPlayer player = new SmartWordInBoardPlayer(board, words);
			player.setResults(new LinkedList<String>());
			Thread playerThread = new Thread(player);
			long start = System.nanoTime();
			playerThread.start();
			// ---- Wait for player to finish
			playerThread.join();
			long end = System.nanoTime();
			System.out
					.println("Word to Board Single Threaded Approach find took: "
							+ (end - start)
							+ "ns or "
							+ ((end - start) / 1000000) + "ms");
			writeResultsToFile(player.getResults(), String.format(
					BOARD_RESULTS_ST, i, i));
			long endTotal = System.nanoTime();
			System.out
					.println("Word To Board Single Threaded Approach total took: "
							+ (endTotal - startTotal)
							+ "ns or "
							+ ((endTotal - startTotal) / 1000000) + "ms\n\n");
		}
	}

	/**
	 * This method tests the WordListFinder. TODO: THIS WordInBoardPlayer
	 * doesn't work multi threaded there is a problem with the visiting a cell.
	 * Threads sometiems think that they haven't already visited a cell.
	 * 
	 */

	// @Test
	@Ignore
	public void testMTWordInBoardPlayer() throws Exception {
		for (int i = 4; i < MAX_SIZE; i *= 2) {
			System.out.println("Word to Board Multi Threaded Approach " + i
					+ "x" + i + ":");
			long startTotal = System.nanoTime();
			List<String> words = readWordsFromFile(DICTIONARY);
			List<String> r = new LinkedList<String>();
			List<String> results = Collections.synchronizedList(r);
			IBoard board = readBoardFromFile(String.format(BOARD, i, i));
			Thread[] playerThreads = new Thread[16];
			IPlayer[] players = new IPlayer[16];
			for (int j = 0; j < 16; j++) {
				players[j] = new WordInBoardPlayer(board, words.subList(words
						.size()
						/ 16 * j, words.size() / 16 * (j + 1)));
				players[j].setResults(results);
				playerThreads[j] = new Thread(players[j]);
			}
			long start = System.nanoTime();
			for (Thread playerThread : playerThreads) {
				playerThread.start();
			}
			// ---- Wait for player to finish
			for (Thread playerThread : playerThreads) {
				playerThread.join();
			}
			long end = System.nanoTime();
			System.out.println("Multi Threaded Approach find took: "
					+ (end - start) + "ns or " + ((end - start) / 1000000)
					+ "ms");
			writeResultsToFile(players[1].getResults(), String.format(
					BOARD_RESULTS_MT, i, i));
			long endTotal = System.nanoTime();
			System.out.println("Multi Threaded Approach total took: "
					+ (endTotal - startTotal) + "ns or "
					+ ((endTotal - startTotal) / 1000000) + "ms");
		}
	}

	private static List<String> readWordsFromFile(String dictionaryFileName)
			throws Exception {
		List<String> words = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(
				dictionaryFileName));
		String s = null;
		while ((s = reader.readLine()) != null)
			words.add(s);
		reader.close();
		return words;
	}

	public  static IBoard readBoardFromFile(String boardFileName)
			throws Exception {
		List<String> lines = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(
				new FileReader(boardFileName));
		String s = null;
		while ((s = reader.readLine()) != null)
			lines.add(s);
		reader.close();
		String[] letters = lines.toArray(new String[0]);
		int N = (int) Math.sqrt(letters.length);
		return new Board(N, letters);
	}

	private static void writeResultsToFile(List<String> results,
			String outputFileName) throws Exception {
		PrintWriter writer = new PrintWriter(new FileWriter(outputFileName));
		Collections.sort(results);
		for (String s : results) {
			writer.println(s);
		}
		writer.close();
	}

	// @AfterClass
	@Ignore
	public static void Verify() throws Exception {
		List<String> st = readWordsFromFile(BOARD_RESULTS_ST);
		System.out.println("Size of single Threaded result list:" + st.size());
		List<String> mt = readWordsFromFile(BOARD_RESULTS_MT);
		System.out.println("Size of multi Threaded result list:" + mt.size());
		Assert.assertEquals(st, mt);
	}

	public static void main(String args[]) throws Exception {
		for (int i = 4; i < MAX_SIZE; i *= 2) {
			System.out.println("Word to Board Single Threaded Approach " + i
					+ "x" + i + ":");
			long startTotal = System.nanoTime();
			ArrayDictionary words = new ArrayDictionary();
			IBoard board = readBoardFromFile(String.format(BOARD, i, i));
			IPlayer player = new SmartWordInBoardPlayer(board, words);
			player.setResults(new LinkedList<String>());
			Thread playerThread = new Thread(player);
			long start = System.nanoTime();
			playerThread.start();
			// ---- Wait for player to finish
			playerThread.join();
			long end = System.nanoTime();
			System.out
					.println("Word to Board Single Threaded Approach find took: "
							+ (end - start)
							+ "ns or "
							+ ((end - start) / 1000000) + "ms");
			writeResultsToFile(player.getResults(), String.format(
					BOARD_RESULTS_ST, i, i));
			long endTotal = System.nanoTime();
			System.out
					.println("Word To Board Single Threaded Approach total took: "
							+ (endTotal - startTotal)
							+ "ns or "
							+ ((endTotal - startTotal) / 1000000) + "ms\n\n");
		}
	}
	
}
