/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

import java.util.List;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.ITreeNode;
import com.sedodream.boggle.dataStructures.TreeNode;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TreeCharacter {
    //Fields-----------------------
    private TreeNodeCharacter rootNode;
    //-----------------------------
    
    
    //Constructors-----------------
    public TreeCharacter(){
        this.rootNode = new TreeNodeCharacter();
    }
    //-----------------------------
    
    public TreeNodeCharacter find(char[] nodePath) throws Exception { 
        if(BoggleMode.DEBUG_MODE){
            if(nodePath==null||nodePath.length<=0){
                throw new IllegalArgumentException("nodePath cannot be empty");
            }
        }
        
        TreeNodeCharacter currentNode = this.getRootNode();
        
        for(int i =0;i<nodePath.length;i++){
            char currentValue = nodePath[i];
            currentNode = this.find(currentNode,currentValue,false);
            if(currentNode==null)
                return null;
        }
        
        return currentNode;
    }

    public TreeNodeCharacter[]getNodes(char[]nodePath){
        if(BoggleMode.DEBUG_MODE){
            if(nodePath==null||nodePath.length<=0){
                throw new IllegalArgumentException("nodePath cannot be empty");
            }
        }
        
        TreeNodeCharacter[]result = new TreeNodeCharacter[nodePath.length];
        TreeNodeCharacter currentNode = this.getRootNode();
        
        for(int i =0;i<nodePath.length;i++){
            char currentValue = nodePath[i];
            currentNode = this.find(currentNode,currentValue,false);
            result[i]=currentNode;
            if(currentNode==null)
                return null;
        }
        
        return result;
    }
    
    public TreeNodeCharacter getRootNode() {
        return this.rootNode;
    }

    public TreeNodeCharacter insertAfter(char[] nodePath) throws Exception {
//        if(BoggleMode.DEBUG_MODE){
//            if(nodePath==null||nodePath.size()<=0){
//                throw new IllegalArgumentException("nodePath cannot be empty");
//            }
//        }
        
        TreeNodeCharacter currentNode = this.getRootNode();
        
        for(int i =0;i<nodePath.length;i++){
            char currentValue = nodePath[i];
            currentNode = this.find(currentNode, currentValue, true);
            if ( currentNode == null ) {
                //since were supposed to be creating we should never be in here
                throw new RuntimeException("unable to create new node");
            }
        }
        return currentNode;
    }

    public TreeNodeCharacter insert(char[] characters) throws Exception {
        //word = word.toUpperCase().replace("QU", "~");
        
        //char[] characters = new char[word.length()];
        //word.getChars(0, word.length(), characters, 0);

        TreeNodeCharacter leafNode = insertAfter(characters);
        leafNode.setIsWord(true);
        return leafNode;
    }
    public SearchResult findWord(char[] word) {
        try {
            TreeNodeCharacter leafNode = find(word);
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
        catch (Exception e) {
            e.printStackTrace();
            RuntimeException re = new RuntimeException(e);
            throw re;
        }
    }
    public void delete(TreeNodeCharacter treeNode) {
        // TODO Auto-generated method stub
    }
    
    //None public items------------
    protected TreeNodeCharacter find(TreeNodeCharacter parentNode, char value,boolean createNonExisting){      
        TreeNodeCharacter foundNode = doFindUnderOneLevel(value,parentNode);
        if(foundNode==null && createNonExisting){
            foundNode = new TreeNodeCharacter(value);
            TreeNodeCharacter[] nextNodes = parentNode.getNextLetterNodes();
            TreeNodeCharacter[] newNextNodes = new TreeNodeCharacter[1];
            
            if(nextNodes!=null){
                newNextNodes = new TreeNodeCharacter[nextNodes.length+1];
                System.arraycopy(nextNodes, 0, newNextNodes, 0, nextNodes.length);        
            }
            newNextNodes[newNextNodes.length-1]=foundNode;
            //parentNode.getNextLetterNodes().add(foundNode);
            parentNode.setNextLetterNodes(newNextNodes);
        }
        return foundNode;
    }
    /**
     * This will search 1 level deep for the value provided.
     * If not found the <b>null</b> will be returned.
     * @return
     */
    protected TreeNodeCharacter doFindUnderOneLevel(char value,TreeNodeCharacter node){
        if ( BoggleMode.DEBUG_MODE ) {
            if ( node == null ) {
                throw new IllegalArgumentException("nodePath cannot be null");
            }
        }

        TreeNodeCharacter[]childNodes = node.getNextLetterNodes();
        if(childNodes==null||childNodes.length<=0){
            return null;
        }
        
        for(int i =0;i<childNodes.length;i++){
            TreeNodeCharacter currentNode = childNodes[i];
            if( currentNode.getValue()==value)
                return currentNode;
        }   
        
        return null;
    }
}
