/**
 * 
 */
package com.sedodream.boggle.dataStructures;

/**
 * Interface for the tree data structure that will hold
 * words. 
 * @author Ibrahim
 */
public interface IWordTree {
	public void insert(String word) throws Exception;
    
    public void delete(String word);
    
    public int getDepth();
    
    public boolean contains(String word) throws Exception;
}
