/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.List;

/**
 * This represents a node in a data structure that contains a letter value
 * represented as a byte.
 * @author Ibrahim
 */
public interface ITreeNode<T> {
	/**
	 * Gets the value of the character representation.
	 * @return
	 */
	public T getValue();
	/**
	 * Sets the value of the character representation.
	 */
	public void setValue(T value);
	/**
	 * Sets the value indicating if this is a word.
	 * @param isWord
	 */
	public void setIsWord(Boolean isWord);
	/**
	 * Gets the value indicating if this is a word or not.
	 * @return
	 */
	public boolean isWord();
    
    public List<ITreeNode<T>> getNextLetterNodes();
    
    public void setNextLetterNodes( List<ITreeNode<T>> nextLetterNodes);
//    public void SetNextLetterNodes(List<ILetterNode<T>>);
}
