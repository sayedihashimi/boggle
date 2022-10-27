package hw4.boggle;


/**
 * @author COP5255
 */

public abstract class ParallelBoggle {
	/**
	 * Create a Parallel Boggle instance
	 * @param wordListFile the file containing the dictionary.
	 * @param N the number of rows and columns in the board.
	 * @param numThreads the number of threads to use.
	 */
	public ParallelBoggle(String wordListFile, int N, int numThreads){}
	
	/**
	 * Finds all the words in the given board.
	 * @param board the letters in the board in row major order.
	 * @return all the words that are in the board.
	 */
	abstract String[] getWords(String[] board);
}
