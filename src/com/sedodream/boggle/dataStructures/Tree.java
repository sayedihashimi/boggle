/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.List;

import com.sedodream.boggle.BoggleMode;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class Tree<T> implements ITree<T> {

    //Fields-----------------------
    private ITreeNode<T> rootNode;
    
    //-----------------------------
    
    
    //Constructors-----------------
    public Tree(){
        this.rootNode = new TreeNode<T>();
    }
    //-----------------------------
    

    public ITreeNode<T> find(List<T> nodePath) throws Exception { 
        if(BoggleMode.DEBUG_MODE){
            if(nodePath==null||nodePath.size()<=0){
                throw new IllegalArgumentException("nodePath cannot be empty");
            }
        }
        
        ITreeNode currentNode = this.getRootNode();
        
        for(int i =0;i<nodePath.size();i++){
            T currentValue = nodePath.get(i);
            currentNode = this.find(currentNode,currentValue,false);
            if(currentNode==null)
                return null;
        }
        
        return currentNode;
    }

    public ITreeNode<T> getRootNode() {
        return this.rootNode;
    }

    public ITreeNode<T> insertAfter(List<T> nodePath) throws Exception {
        if(BoggleMode.DEBUG_MODE){
            if(nodePath==null||nodePath.size()<=0){
                throw new IllegalArgumentException("nodePath cannot be empty");
            }
        }
        
        ITreeNode currentNode = this.getRootNode();
        
        for(int i =0;i<nodePath.size();i++){
            T currentValue = nodePath.get(i);
            currentNode = this.find(currentNode, currentValue, true);
            if ( currentNode == null ) {
                //since were supposed to be creating we should never be in here
                throw new RuntimeException("unable to create new node");
            }
        }
        return currentNode;
    }

    public void delete(ITreeNode<T> treeNode) {
        // TODO Auto-generated method stub
    }
    
    //None public items------------
    protected ITreeNode<T> find(ITreeNode parentNode, T value,boolean createNonExisting){
        if(BoggleMode.DEBUG_MODE){
            if(value==null){
                throw new IllegalArgumentException("value cannot be null");
            }
        }
        
        ITreeNode<T> foundNode = doFindUnderOneLevel(value,parentNode);
        if(foundNode==null && createNonExisting){
            foundNode = new TreeNode<T>(value);
            parentNode.getNextLetterNodes().add(foundNode);
        }
        
        return foundNode;
    }
    /**
     * This will search 1 level deep for the value provided.
     * If not found the <b>null</b> will be returned.
     * @return
     */
    protected ITreeNode<T> doFindUnderOneLevel(T value,ITreeNode<T> node){
        if ( BoggleMode.DEBUG_MODE ) {
            if ( node == null ) {
                throw new IllegalArgumentException("nodePath cannot be null");
            }
        }
        
        List<ITreeNode<T>>childNodes = node.getNextLetterNodes();
        if(childNodes==null||childNodes.size()<=0){
            return null;
        }
        
        for(int i =0;i<childNodes.size();i++){
            ITreeNode currentNode = childNodes.get(i);
            if( currentNode.getValue().equals(value))
                return currentNode;
        }   
        
        return null;
    }
    //-----------------------------
}
