package hw4.boggle;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import hw4.boggle.*;
import hw4.boggle.board.*;
import hw4.boggle.dictionary.*;
import hw4.boggle.player.*;


public abstract class BaseParallelBoggle extends ParallelBoggle {
	private String wordListFile;
	private int N;
	private boolean firstTimeRunning;
	private int numThreads;
	
	public BaseParallelBoggle(String wordListFile, int N, int numThreads) {
		super(wordListFile, N, numThreads);
		this.wordListFile = wordListFile;
		this.N = N;
		if(numThreads > 32) {
			this.numThreads = 32;
		} else {
			this.numThreads = numThreads;
		}
		this.firstTimeRunning = true;
		setup(numThreads);
	}

	protected String[] getWords(String[] board) {
		//---- This stuff has to be cleaned and reset up if not first time running
		if(!firstTimeRunning){
			clean();
			setup(numThreads);
		}
		//---- Initialize the players with the dictionary 
		initializePlayers(createDictionary(wordListFile), N, createBoard(N, board));
		String[] results = createResults();
		
		firstTimeRunning = false;
		
		return results;
	}
	
	protected abstract void initializePlayers(IDictionary dictionary, int N, IBoard board);
	
	protected abstract String[] createResults();
	
	protected abstract void setup(int numThreads);
	
	private void clean(){
		doClean();
		System.gc();
	}
	protected abstract void doClean();
	
	protected abstract IBoard createBoard(int N, String[] board);
	
	protected abstract IPlayer createPlayer(IDictionary dictionary, IBoard board);
	
	protected IDictionary createDictionary(String filename) {
		return doCreateDictionary(filename);
	}
	
	protected IDictionary doCreateDictionary(String filename) {
		System.out.format("Starting to read the word list from file:%s\n", filename);

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		System.out.printf("Reading word file %s\n", filename);

		PruningTreeCharacter wordList = new PruningTreeCharacter();
		wordList.setDoPruning(false);
		int currentCount = 0;
		String currentLine = null;
		Date startTime = new Date();
		try {
			File wordFile = new File(filename);



			fileReader = new FileReader(wordFile);
			bufferedReader = new BufferedReader(fileReader);

			while ((currentLine = bufferedReader.readLine()) != null) {
				// TODO: Not sure if we should be upper casing here
				wordList.insert(currentLine.toCharArray());
				currentCount++;
			}
		}catch(Exception e) {
		} finally {
			try {
			bufferedReader.close();
			} catch(Exception e) {
				
			}
		}
		wordList.setDoPruning(true);

		Date endTime = new Date();
		long milliSpent = endTime.getTime() - startTime.getTime();
		System.out.printf("Finished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",currentCount, milliSpent);
		return (IDictionary)wordList;
	}	
}
