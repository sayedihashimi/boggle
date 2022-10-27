package com.sedodream.boggle.drc;

import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;

public interface IDictionary {
	/**
     * Looks to see if the provided word is in the dictionary. 
     * @param word
     * @return
	 */
	public SearchResult findWord(String word);
	/**
     * Returns the number of words in the dictionary. 
     * @return
	 */
    public int size();
}
