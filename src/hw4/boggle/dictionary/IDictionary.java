package hw4.boggle.dictionary;

public interface IDictionary {
	/**
     * Looks to see if the provided word is in the dictionary. 
     * @param word
     * @return
	 */
	public SearchResult findWord(char[] word, int len);
	/**
     * Looks to see if the provided word is in the dictionary. 
     * @param word
     * @return
	 */
	public SearchResult findWord(char[] word);
	

}