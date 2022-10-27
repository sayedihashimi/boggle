/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class WordTreeBytes implements IWordTree {
    //Fields
    private ITree<Byte> tree;

    public WordTreeBytes() {
        this.tree = new Tree<Byte>();
    }

    /* (non-Javadoc)
     * @see com.sedodream.boggle.dataStructures.IWordTree#contains(java.lang.String)
     */
    public boolean contains(String word) throws Exception {
        //decompose this into smaller items
        byte[] characters = word.getBytes();
        
        //now create a list from this
        List<Byte> charList = new ArrayList<Byte>();
        for (int i = 0; i < characters.length; i++) {
            charList.add(characters[i]);
        }
        
        ITreeNode<Byte> leafNode = tree.find(charList);
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
        byte[] characters =word.getBytes();

        //now create a list from this
        List<Byte> charList = new ArrayList<Byte>();
        for (int i = 0; i < characters.length; i++) {
            charList.add(characters[i]);
        }
        
        ITreeNode<Byte>leafNode = tree.insertAfter(charList);
        leafNode.setIsWord(true);
    }
}
