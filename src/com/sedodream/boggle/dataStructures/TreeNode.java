/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TreeNode<T> implements ITreeNode<T>
{
    // Fields
    private T           value;
    private Boolean     isWord;
    private List<ITreeNode<T>> nextLetters;
    
    // Constructors
    public TreeNode()
    {
        this.setIsWord(false);
    }

    public TreeNode(T value)
    {
        this();
        this.setValue(value);
    }

    public TreeNode(T value, List<ITreeNode<T>> nextLetters)
    {
        this(value);
        this.setNextLetterNodes(nextLetters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sedodream.boggle.dataStructurs.ILetterNode#GetValue()
     */
    public T getValue()
    {
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sedodream.boggle.dataStructurs.ILetterNode#IsWord()
     */
    public boolean isWord()
    {
        return isWord;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sedodream.boggle.dataStructurs.ILetterNode#SetIsWord(java.lang.Boolean)
     */
    public void setIsWord(Boolean isWord)
    {
        this.isWord = isWord;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sedodream.boggle.dataStructurs.ILetterNode#SetValue(java.lang.Byte)
     */
    public void setValue(T value)
    {
        this.value = value;
    }
    public void setNextLetterNodes( List<ITreeNode<T>> nextLetterNodes)
    {
        this.nextLetters = nextLetterNodes;
    }
    public List<ITreeNode<T>> getNextLetterNodes()
    {
        if(this.nextLetters==null)
            this.nextLetters=new ArrayList<ITreeNode<T>>();
        return this.nextLetters;
    }
}
