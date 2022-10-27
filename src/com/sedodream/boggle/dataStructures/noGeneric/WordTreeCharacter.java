/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

import com.sedodream.boggle.drc.IDictionary;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class WordTreeCharacter implements IDictionary{
    //Fields
    protected TreeCharacter tree;
    protected int size;
    
    public WordTreeCharacter() {
        this.tree = new TreeCharacter();
    }

    public SearchResult contains(String word) throws Exception {
        
        StringBuilder sb = new StringBuilder();
        
        word = word.toUpperCase().replace("QU", "~");
        //decompose this into smaller items
        char[] characters = new char[word.length()];
        word.getChars(0, word.length(), characters, 0);
        
        TreeNodeCharacter leafNode = tree.find(characters);
        if(leafNode==null){
            //return false;
            SearchResult result=new SearchResult(false,false);
            return result;
        }
        else{
            boolean hasChildren = leafNode.getHasChildren();
            SearchResult result=new SearchResult(leafNode.isWord(),hasChildren);
            return result;
        }
    }
    public void delete(String word) {
        // TODO Auto-generated method stub
    }
    public int getDepth() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void insert(String word) throws Exception {
        word = word.toUpperCase().replace("QU", "~");
        
        char[] characters = new char[word.length()];
        word.getChars(0, word.length(), characters, 0);

        TreeNodeCharacter leafNode = tree.insertAfter(characters);
        leafNode.setIsWord(true);
        
        size++;
    }
    public int size(){
        return this.size;
    }
    /* (non-Javadoc)
     * @see com.sedodream.boggle.drc.IDictionary#findWord(java.lang.String)
     */
    public SearchResult findWord(String word) {
        try {
            return this.contains(word);
        }
        catch (Exception e) {
            e.printStackTrace();
            RuntimeException re = new RuntimeException(e);
            throw re;
        }
    }
}
