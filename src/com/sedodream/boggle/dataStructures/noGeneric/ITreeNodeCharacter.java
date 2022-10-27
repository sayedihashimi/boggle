/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

import java.util.List;

import com.sedodream.boggle.dataStructures.ITreeNode;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface ITreeNodeCharacter {
    /**
     * Gets the value of the character representation.
     * @return
     */
    public char getValue();
    /**
     * Sets the value of the character representation.
     */
    public void setValue(char value);
    /**
     * Sets the value indicating if this is a word.
     * @param isWord
     */
    public void setIsWord(boolean isWord);
    /**
     * Gets the value indicating if this is a word or not.
     * @return
     */
    public boolean isWord();
    
    public char[] getNextLetterNodes();
    
    public void setNextLetterNodes( char[] nextLetterNodes);
    
    public boolean getHasChildren();
    
    public int getNumRecursiveWords();
    public void setNumRecursiveWords(int numRecursiveWords);
    
}
