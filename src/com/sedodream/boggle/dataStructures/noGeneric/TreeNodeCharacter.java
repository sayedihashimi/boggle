/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import sun.io.Converters;

import com.sedodream.boggle.dataStructures.ITreeNode;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TreeNodeCharacter {
    //  Fields
    private char    value;
    private Boolean isWord;
    private TreeNodeCharacter[]  nextLetters;

    private boolean hasChildren;
    private boolean hasChildrenInitalized;
    private boolean wordRead;
    //private int numWordsRecursive;
    
    private AtomicInteger atomicNumWordsRecursive;
    
    private TreeNodeCharacter parent;
    // Constructors
    public TreeNodeCharacter() {
        this.setIsWord(false);
        this.hasChildrenInitalized=false;
        this.hasChildren=true;
        wordRead = false;
        //numWordsRecursive=0;
        atomicNumWordsRecursive = new AtomicInteger(0);
    }

    public TreeNodeCharacter(char value) {
        this();
        this.setValue(value);
    }

    public TreeNodeCharacter(char value, TreeNodeCharacter[] nextLetters) {
        this(value);
        this.setNextLetterNodes(nextLetters);
    }

    public char getValue() {
        return value;
    }

    public boolean getIsWordReal(){
        return isWord();
    }
    public boolean isWord() {
        if(this.wordRead){
            return false;
        }
        
        wordRead=true;
        return isWord;
//        if(isWord){
//            isWord=false;
//            return true;
//        }
//        return false;
    }

    public void setIsWord(Boolean isWord) {
        this.isWord = isWord;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setNextLetterNodes(TreeNodeCharacter[] nextLetterNodes) {
        this.nextLetters = nextLetterNodes;
    }

    public TreeNodeCharacter[] getNextLetterNodes() {
        //if(this.nextLetters==null)
        //this.nextLetters=new ArrayList<ITreeNode<T>>();
        return this.nextLetters;
    }

    @Override
    public String toString() {
        String nextStr = "(null)";
        if(nextLetters!=null){
            //nextStr = new String()
            nextStr = Integer.toString(nextLetters.length);
            //nextStr=""+nextLetters.length;
        }
        return String.format("[%s,isWord=%s,%s]", value,isWord,nextStr);
        
        //return super.toString();
    }

    public boolean getHasChildren(){
        //we cant do this it throws things off
//        if(!this.hasChildrenInitalized){
//            this.hasChildren=(this.nextLetters==null||this.nextLetters.length<=0)?false:true;
//
//            this.hasChildrenInitalized=true;
//        }
        
        return this.hasChildren;
    }

    public void IncrementNumWordsRecursive(){
        atomicNumWordsRecursive.incrementAndGet();
    }
    public void DecrementNumWordsRecursive(){
        atomicNumWordsRecursive.decrementAndGet();
    }
    public int getNumWordsRecursive() {
        return atomicNumWordsRecursive.get();
        //return numWordsRecursive;
    }
//    public void setNumWordsRecursive(int numWordsRecursive) {
//        this.numWordsRecursive = numWordsRecursive;
//    }

    /**
     * @return the parent
     */
    public TreeNodeCharacter getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(TreeNodeCharacter parent) {
        this.parent = parent;
    }
}
