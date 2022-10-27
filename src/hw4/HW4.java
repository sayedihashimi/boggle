package hw4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.SortedSet;
import java.util.TreeSet;

import hw4.boggle.*;
import hw4.boggle.board.*;
import hw4.boggle.dictionary.*;
import hw4.boggle.player.*;


public class HW4 extends BaseParallelBoggle {
	
	private Thread[] threads;
	private IPlayer[] players;
	private IDictionary dictionary;
	
	public HW4(String wordListFile, int N, int numThreads) {
		super(wordListFile, N, numThreads);
		dictionary = createDictionary(wordListFile);
	}

	@Override
	protected IBoard createBoard(int N, String[] board) {
		return new SynchronizedBoard(N, board);
	}

	@Override
	protected IPlayer createPlayer(IDictionary dictionary, IBoard board) {
		// TODO Auto-generated method stub
		return new CharSequenceToDictionaryCachedNeighborsPruningPlayer(dictionary, board);
	}

	protected IDictionary createDictionary(String filename) {
		if(dictionary != null) {
			return dictionary;
		}
		else {
			System.out.println("Dictionary null creating Dictionary");
			return super.createDictionary(filename);
		}
	}
	
	/**
	 * over ride this with the char version.
	 */
	protected IDictionary doCreateDictionary(String filename) {
		
//		return super.doCreateDictionary(filename);
		BufferedReader reader = null;

		PruningTreeCharacter wordList = new PruningTreeCharacter();
		wordList.setDoPruning(false);

		try {
			reader = new BufferedReader(new FileReader(filename));
			// bufferedReader.read
			char[] currentWord = new char[50];
			boolean done = false;
			boolean stillReadingLine = true;
			// read one word at at time
			int currentChar;

			int endOfLineChar = (int) '\n';
			int currentPosition = 0;

			//    int maxNumReads = 270000*45;
			//    int currentReadNum = 0;
			do {
				//        if(currentReadNum>maxNumReads){
				//            System.out.printf("\tToo many reads performed stopping!\n");
				//            break;
				//        }
				do {
					//            if(currentReadNum++>maxNumReads){
					//                System.out.printf("\tToo many reads performed stopping!\n");
					//                break;
					//            }
					currentChar = reader.read();
					if (currentChar < 0) {
						done = true;
						stillReadingLine = false;
						//System.out.printf("Were done!\n");
						// todo: see if there is still a word in the buffer
					} else if (currentChar == endOfLineChar) {
						stillReadingLine = false;

						char[] theWord = new char[currentPosition]; //make sure to not include the newline in the word
						System.arraycopy(currentWord, 0, theWord, 0,
								currentPosition);
						String word = new String(theWord);
						//                System.out.printf("\tread word: %s\n",word);
						wordList.insert(theWord);

						currentPosition = 0;
					} else {
						currentWord[currentPosition] = (char) currentChar;
						currentPosition++;
					}
				} while (stillReadingLine);
			} while (!done);
		} 
		catch(Exception e) {System.out.println(e);} 
		finally { if(reader!= null) { try { reader.close(); } catch(Exception e) {} } }
		wordList.setDoPruning(true);
		return wordList;
	}
	
	public void setup(int numThreads) {
		threads = new Thread[numThreads];
		players = new IPlayer[numThreads];
	}
	
	public void doClean() {
		int numThreads = threads.length;
		for(int i  = 0; i < numThreads; i++ ) {
			players[i] = null;
			threads[i]= null;
		}
		dictionary = null;		
	}

	@Override
	public void initializePlayers(IDictionary dictionary, int N, IBoard board) {
		int numThreads = threads.length;  
		for(int i = 0; i < numThreads; i++){
			players[i] = createPlayer(dictionary, board);
			threads[i] = new Thread(players[i]);
		}
	}

	protected String[] createResults() {
		int numThreads = threads.length;
		for(int i = 0; i < numThreads; i++ ){
			threads[i].start();
		}
		for(int i = 0; i < numThreads; i++) {
			try { threads[i].join(); } catch(InterruptedException e) {} 
		}
		SortedSet<String> resultSet = new TreeSet<String>();
		for(int i = 0; i < numThreads; i++) {
			resultSet.addAll(players[i].getResults());
		}
		String[] resultArray = new String[resultSet.size()];
		resultArray = resultSet.toArray(resultArray);
		
		return resultArray;
	}

	
}

