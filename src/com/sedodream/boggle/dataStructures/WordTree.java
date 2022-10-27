/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.*;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class WordTree implements IWordTree {

    //Fields
    private ITree<Character> tree;

    public WordTree() {
        this.tree = new Tree<Character>();
    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.dataStructures.IWordTree#contains(java.lang.String)
     */
    public boolean contains(String word) throws Exception {
        //decompose this into smaller items
        char[] characters = new char[word.length()];
        word.getChars(0, word.length(), characters, 0);

        //now create a list from this
        List<Character> charList = new ArrayList<Character>();
        for (int i = 0; i < characters.length; i++) {
            charList.add(characters[i]);
        }
        
        ITreeNode<Character> leafNode = tree.find(charList);
        if(leafNode==null){
            return false;
        }
        
        //only find whole words
        return leafNode.isWord();
    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.dataStructures.IWordTree#delete(java.lang.String)
     */
    public void delete(String word) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.dataStructures.IWordTree#getDepth()
     */
    public int getDepth() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.dataStructures.IWordTree#insert(java.lang.String)
     */
    public void insert(String word) throws Exception {
        char[] characters = new char[word.length()];
        word.getChars(0, word.length(), characters, 0);

        //now create a list from this
        List<Character> charList = new ArrayList<Character>();
        for (int i = 0; i < characters.length; i++) {
            charList.add(characters[i]);
        }
        
        ITreeNode<Character>leafNode = tree.insertAfter(charList);
        leafNode.setIsWord(true);
    }

}
