package com.sedodream.boggle.drc.mt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;
import com.sedodream.boggle.drc.IDictionary;

public class TestMTSequenceToDictionaryCachedNeighborsPlayer {

	private static final String DICTIONARY = "./files/word.list";
	private static final String BOARD = "./files/board%dx%d.txt";
	private static final String BOARD_RESULTS_ST = "./output/board%dx%d.results.drc.s2dcn.st";
	private static final String BOARD_RESULTS_MT = "./output/board%dx%d.results.drc.s2d.mt";
	private static final int MAX_SIZE = 100;
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSequenceToDictionaryPlayer() throws Exception {
		for(int i = 4; i < MAX_SIZE; i*=2) {
			System.out.println("Sequence To Dictionary Single Threaded Approach "+i+"x"+i+":");
			long startTotal = System.nanoTime();
			IDictionary dictionary = BuildDictionaryFromFile(DICTIONARY);
			IBoard board = readBoardFromFile(String.format(BOARD,i,i));
			IPlayer player = new SequenceToDictionaryCachedNeighborsPlayer(dictionary,board, 0, 0, i,i);
			player.setResults(new LinkedList<String>());
			Thread playerThread = new Thread(player);
			long start = System.nanoTime();
			playerThread.start();
			// ---- Wait for player to finish
			playerThread.join();
			long end = System.nanoTime();
			System.out.println("Single Threaded Approach find took: "
					+ (end - start) + "ns or " + ((end - start) / 1000000) + "ms");
			writeResultsToFile(player.getResults(), String.format(BOARD_RESULTS_ST,i,i));
			long endTotal = System.nanoTime();
			System.out.println("Single Threaded Approach total took: "
					+ (endTotal - startTotal) + "ns or "
					+ ((endTotal - startTotal) / 1000000) + "ms\n\n");
			}
	}

	public static IDictionary BuildDictionaryFromFile(String filename)
			throws Exception {

		System.out.format("Starting to read the word list from file:%s\n",
				filename);

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		System.out.printf("Reading word file %s\n", filename);

		WordTreeCharacter wordList = new WordTreeCharacter();

		int currentCount = 0;
		String currentLine = null;
		Date startTime = new Date();
		try {
			File wordFile = new File(filename);

			if (!wordFile.exists()) {
				String message = String.format("Cannot file file at [%s]",
						wordFile.getAbsoluteFile());
				throw new Exception(message);
			}

			// GenericTreeTest.strings = new ArrayList<String>();

			fileReader = new FileReader(wordFile);
			bufferedReader = new BufferedReader(fileReader);

			while ((currentLine = bufferedReader.readLine()) != null) {
				// TODO: Not sure if we should be upper casing here
				wordList.insert(currentLine.toUpperCase());
				// GenericTreeTest.strings.add(currentLine);
				// if ( currentLine.length() > maxLength )
				// maxLength = currentLine.length();
				currentCount++;
			}
			// d.close();
		} finally {
			if (fileReader != null)
				fileReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}
		Date endTime = new Date();
		long milliSpent = endTime.getTime() - startTime.getTime();
		System.out
				.printf(
						"Finished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",
						currentCount, milliSpent);

		return wordList;
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

	private static IBoard readBoardFromFile(String boardFileName)
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

	@Ignore
	public static void Verify() throws Exception {
		List<String> st = readWordsFromFile(BOARD_RESULTS_ST);
		System.out.println("Size of single Threaded result list:" + st.size());
		List<String> mt = readWordsFromFile(BOARD_RESULTS_MT);
		System.out.println("Size of multi Threaded result list:" + mt.size());
//		Assert.assertEquals(st, mt);
		if(st.size() >= mt.size() ){
			for(int i = 0; i < st.size(); i++){
				if(!mt.contains(st.get(i))){
					System.out.println("mt dont contain:" + st.get(i));
				}
			}
		} else {
			for(int i = 0; i < mt.size(); i++){
				if(!st.contains(mt.get(i))){
					System.out.println("st dont contain:" + st.get(i));
				}
			}
		}
	}

	public static void main(String args[]) throws Exception {
		for(int i = 4; i < MAX_SIZE; i*=2) {
			System.out.println("Sequence To Dictionary Single Threaded Approach "+i+"x"+i+":");
			long startTotal = System.nanoTime();
			IDictionary dictionary = BuildDictionaryFromFile(DICTIONARY);
			IBoard board = readBoardFromFile(String.format(BOARD,i,i));
			IPlayer player = new SequenceToDictionaryPlayer(dictionary,board, 0, 0, i,i);
			player.setResults(new LinkedList<String>());
			Thread playerThread = new Thread(player);
			long start = System.nanoTime();
			playerThread.start();
			// ---- Wait for player to finish
			playerThread.join();
			long end = System.nanoTime();
			System.out.println("Single Threaded Approach find took: "
					+ (end - start) + "ns or " + ((end - start) / 1000000) + "ms");
//			writeResultsToFile(player.getResults(), String.format(BOARD_RESULTS_ST,i,i));
			long endTotal = System.nanoTime();
			System.out.println("Words Found: "+player.getResults().size());
			System.out.println("Single Threaded Approach total took: "
					+ (endTotal - startTotal) + "ns or "
					+ ((endTotal - startTotal) / 1000000) + "ms\n\n");
			}
	}

}
