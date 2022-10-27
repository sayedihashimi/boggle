package hw4.boggle;

import java.io.*;
import java.util.LinkedList;

import hw4.boggle.*;
import hw4.boggle.board.*;
import hw4.boggle.dictionary.*;
import hw4.boggle.player.*;


import org.junit.Test;


public class ParallelBoggleEvaluator {
	private static final String FILES_PATH = "./files/";
	private static final String OUTPUT_PATH = "./output/";
	private static final String BOARD_FILE = FILES_PATH+"board%dx%d.txt";
	private static final String RESULTS_FILE = OUTPUT_PATH+"results.%dx%d.%dthreads.txt";
	private static final String DICTIONARY = FILES_PATH+"word.list";
	private static final String SEARCHED_BOARD = "Searched board%dx%d with %d threads in %sns or %dus or %dms";
	private static final String TOTAL_TEST = "Test finished in %dms";
	private static final String TEST_HEADER = "Testing: %s on board of size %dx%d with %d threads %s";
	private static final String DUPED_BOARD_HEADER = "DuplicatedBoard per thread";
	private static final String SYNCHED_BOARD_HEADER = "SynchronizedBoard for all threads";
	private static final int NUM_USER_TESTS = 5;
	
	public static void main(String args[]) {
		System.out.println("Suggested Usage: arg[0]= boardSize, args[1]= numThreads, args[2]= dictionaryFilename");
		int boardSize = 64;
		int numThreads = 16;
		String dictionaryFilename = DICTIONARY;
		if(args.length == 3){
			dictionaryFilename = args[2];
			boardSize = Integer.parseInt(args[0]);
			numThreads = Integer.parseInt(args[1]);			
		}
		if( args.length == 2) {
			boardSize = Integer.parseInt(args[0]);
			numThreads = Integer.parseInt(args[1]);
		}
		else if(args.length == 1){
			numThreads = Integer.parseInt(args[0]);
		}

		if(args.length == 0 ) {
			autoTestSynchedSameSizeBoard(dictionaryFilename, new int[]{64,64,64,64,64}, numThreads);
			autoTestDupedSameSizeBoard(dictionaryFilename, new int[]{64,64,64,64,64}, numThreads);
		}
		if(args.length != 0) {
			userSpecifiedTest(dictionaryFilename, boardSize, numThreads);
		}

	}
	
	public static void autoTestSynchedSameSizeBoard(String dictionaryFilename,int[] boardSizes, int numThreads){
		//---- Get all the boards and read them into arrays in memory for the test
		String[][] boards = new String[boardSizes.length][];
		for(int i = 0; i < boards.length; i++ ){
			boards[i] = readBoardFromFile(String.format(BOARD_FILE,boardSizes[i],boardSizes[i]));
		}
		//---- Start timing the whole test
		long startTotalTime = System.nanoTime();
		
		//---- create the one ParallelBoggle instance
		ParallelBoggle hw4 = new SynchedBoardParallelBoggle(dictionaryFilename, boardSizes[0], numThreads);
		
		//---- do all the tests for this ParallelBoggle
		for(int i = 0; i < boards.length; i++) {
			
			//---- start time for this test
			long startTime = System.nanoTime();

			//---- do the test
			
			String[] results = hw4.getWords(boards[i]);
			
			//---- end time for this test
			long endTime = System.nanoTime();
			
			//---- Calculate and Print time for test
			System.out.println(String.format(SEARCHED_BOARD,boardSizes[i],boardSizes[i],numThreads, new Long((endTime-startTime)).toString(), ((endTime-startTime) / 1000),((endTime-startTime) / 1000000))+"\n");
			System.out.flush();
			writeResultsToFile(String.format(RESULTS_FILE,(boardSizes[i]+10*i),(boardSizes[i]+10*i),numThreads), results);
		}
		long endTotalTime = System.nanoTime();
		System.out.println(String.format(TOTAL_TEST,((endTotalTime-startTotalTime) / 1000000)));
	}
	public static void autoTestDupedSameSizeBoard(String dictionaryFilename,int[] boardSizes, int numThreads){
		//---- Get all the boards and read them into arrays in memory for the test
		String[][] boards = new String[boardSizes.length][];
		for(int i = 0; i < boards.length; i++ ){
			boards[i] = readBoardFromFile(String.format(BOARD_FILE,boardSizes[i],boardSizes[i]));
		}
		//---- Start timing the whole test
		long startTotalTime = System.nanoTime();
		
		//---- create the one ParallelBoggle instance
		ParallelBoggle hw4 = new DupedBoardParallelBoggle(dictionaryFilename, boardSizes[0], numThreads);
		
		//---- do all the tests for this ParallelBoggle
		for(int i = 0; i < boards.length; i++) {
			
			//---- start time for this test
			long startTime = System.nanoTime();

			//---- do the test
			
			String[] results = hw4.getWords(boards[i]);
			
			//---- end time for this test
			long endTime = System.nanoTime();
			
			//---- Calculate and Print time for test
			System.out.println(String.format(SEARCHED_BOARD,boardSizes[i],boardSizes[i],numThreads, new Long((endTime-startTime)).toString(), ((endTime-startTime) / 1000),((endTime-startTime) / 1000000))+"\n");
			System.out.flush();
			writeResultsToFile(String.format(RESULTS_FILE,(boardSizes[i]+i),(boardSizes[i]+i),numThreads), results);
		}
		long endTotalTime = System.nanoTime();
		System.out.println(String.format(TOTAL_TEST,((endTotalTime-startTotalTime) / 1000000)));
	}

	public static void userSpecifiedTest(String dictionaryFilename, int boardSize, int numThreads) {
		doUserSynchSpecifiedTest(dictionaryFilename, boardSize, numThreads, NUM_USER_TESTS);
		doUserDupedSpecifiedTest(dictionaryFilename, boardSize, numThreads, NUM_USER_TESTS);
	}
	public static void doUserSynchSpecifiedTest(String dictionaryFilename, int boardSize, int numThreads, int numTests) {
		System.out.println(String.format(TEST_HEADER,SYNCHED_BOARD_HEADER,boardSize,boardSize, numThreads,""));
		
		String[] board = readBoardFromFile(String.format(BOARD_FILE, boardSize,boardSize));
		
		//---- Start timing the whole test
		long startTotalTime = System.nanoTime();
		
		//---- create the one ParallelBoggle instance
		ParallelBoggle hw4 = new SynchedBoardParallelBoggle( dictionaryFilename, boardSize, numThreads);
		for(int i = 0; i < numTests; i++) {
			//---- start time for this test
			long startTime = System.nanoTime();
	
			//---- do the test
			String[] results = hw4.getWords(board);
	
			//---- end time for this test
			long endTime = System.nanoTime();
			
			//---- Calculate and Print time for test
			System.out.println(String.format(SEARCHED_BOARD,boardSize,boardSize,numThreads, new Long((endTime-startTime)).toString(), ((endTime-startTime) / 1000),((endTime-startTime) / 1000000)));
			writeResultsToFile(String.format(RESULTS_FILE,(boardSize+1)*20,(boardSize+1)*20,numThreads), results);
		}
		//--- End timing whole test
		long endTotalTime = System.nanoTime();
		String msg = String.format(TOTAL_TEST, ((endTotalTime-startTotalTime) / 1000000));
		System.out.println(String.format(TEST_HEADER,SYNCHED_BOARD_HEADER, boardSize, boardSize, numThreads, "\n\t"+msg+"\n\n"));
	}
	
	public static void doUserDupedSpecifiedTest(String dictionaryFilename, int boardSize, int numThreads, int numTests) {
		System.out.println(String.format(TEST_HEADER,DUPED_BOARD_HEADER,boardSize,boardSize, numThreads,""));
		
		String[] board = readBoardFromFile(String.format(BOARD_FILE, boardSize,boardSize));
		
		//---- Start timing the whole test
		long startTotalTime = System.nanoTime();
		
		//---- create the one ParallelBoggle instance
		ParallelBoggle hw4 = new DupedBoardParallelBoggle( dictionaryFilename, boardSize, numThreads);
		for(int i = 0; i < numTests; i++) {
			//---- start time for this test
			long startTime = System.nanoTime();
	
			//---- do the test
			String[] results = hw4.getWords(board);
	
			//---- end time for this test
			long endTime = System.nanoTime();
			
			//---- Calculate and Print time for test
			System.out.println(String.format(SEARCHED_BOARD,boardSize,boardSize,numThreads, new Long((endTime-startTime)).toString(), ((endTime-startTime) / 1000),((endTime-startTime) / 1000000)));
			writeResultsToFile(String.format(RESULTS_FILE,(boardSize+1)*30,(boardSize+1)*30,numThreads), results);
		}
		//--- End timing whole test
		long endTotalTime = System.nanoTime();
		String msg = String.format(TOTAL_TEST, ((endTotalTime-startTotalTime) / 1000000));
		System.out.println(String.format(TEST_HEADER,DUPED_BOARD_HEADER, boardSize, boardSize, numThreads, "\n\t"+msg+"\n\n"));
	}
	
	
	@Test 
	public void tester() {
		main(new String[1]);
	}
	public static String[] readBoardFromFile(String filename){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			LinkedList<String> list = new LinkedList<String>();
			String line = null;
			while((line=reader.readLine()) != null) {
				list.add(line);
			}
			String[] resultArray = new String[list.size()];
			return list.toArray(resultArray);
		}
		catch(Exception e){return null;}
		finally{ try{ if(reader!=null) reader.close(); } catch(Exception e){} }
		
	}
	
	public static void writeResultsToFile(String filename, String[] results) {
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(new FileWriter(filename));
			int resultsLen = results.length;
			for(int i = 0; i < resultsLen; i++) {
				writer.println(results[i]);
			}
		}
		catch(Exception e){}
		finally {try{if(writer!= null)writer.close();} catch(Exception e) {}}
	}
}
