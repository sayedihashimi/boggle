/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.List;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public interface ITree<T> {
    
    /**
     * This will insert the treeNode after the sequence of
     * nodes in the nodePath.
     * If you are trying to insert a node right below the
     * root then just pass null as the nodePath.
     * If the node already exists then no change is made. 
     */
    public ITreeNode<T> insertAfter(List<T> nodePath) throws Exception;
    
    public void delete(ITreeNode<T> treeNode);
    
    /**
     * Gets the depth of the tree.
     * @return
     */
    //public int getDepth();
    /**
     * Gets the root node of the tree.
     * @return
     */
    public ITreeNode<T> getRootNode();
    /**
     * Will search the tree under the root,
     * trying to find the sequence of nodes contained in the array
     * @param nodePath
     * @return
     */
    //public boolean contains(T[] nodePath);
    /**
     * If the tree contains the path specified in the array,
     * then the node found is returned.
     * Otherwise false is returned.
     * @param nodePath
     * @return
     * @throws Exception 
     */
    public ITreeNode<T> find( List<T> nodePath) throws Exception;
}
