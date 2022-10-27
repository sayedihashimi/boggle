package com.sedodream.boggle.dataStructures;
///**
// * 
// */
//package com.sedodream.boggle.dataStructures;
//
//import java.util.*;
//
//import javax.sql.rowset.serial.SerialArray;
//
//import com.sedodream.boggle.BoggleMode;
//
///**
// * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
// */
//public class Tree<T> implements ITree<T> {
//
//    // Fields
//    private ITreeNode<T> rootNode;
//    //private int          depth;
//
//    // Constructors
//    public Tree() {
//        //depth = 0;
//        rootNode = new TreeNode<T>();
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.sedodream.boggle.dataStructures.ITree#contains(T[])
//     */
//    public ITreeNode<T> find(List<ITreeNode<T>> nodePath) throws Exception {
//        ITreeNode<T> result = this.findUnder(this.getRootNode(), nodePath);
//        return result;
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.sedodream.boggle.dataStructures.ITree#delete(com.sedodream.boggle.dataStructures.ITreeNode)
//     */
//    public void delete(ITreeNode treeNode) {
//        // oldTODO Auto-generated method stub
//
//    }
//
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.sedodream.boggle.dataStructures.ITree#getRootNode()
//     */
//    public ITreeNode<T> getRootNode() {
//        return this.rootNode;
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.sedodream.boggle.dataStructures.ITree#insert(com.sedodream.boggle.dataStructures.ITreeNode)
//     */
//    public void insertAfter(ITreeNode<T> treeNode,List<ITreeNode<T>> nodePath) throws Exception {
//        ITreeNode<T> currentSearchNode = rootNode;
//
////        if ( nodePath == null ) {
////            // see if the root node already contains this node
////            ITreeNode<T>foundNode = findNode(treeNode,this.rootNode);
////            if(foundNode == null){
////                //add it
////                this.rootNode.getNextLetterNodes().add(treeNode);
////                return;
////            }
////            else{
////                //node already exists
////                return;
////            }
////        }
//        
//        List<ITreeNode<T>>newNodePath = new ArrayList<ITreeNode<T>>();
//        if(nodePath!=null){
//            for(int i =0;i<nodePath.size();i++){
//                newNodePath.add(nodePath.get(i));
//            }
//            
//        }
//        newNodePath.add(treeNode);
//        //todo: Finish this
//        this.findOrCreate(rootNode, newNodePath);
//    }
//    
//    // -----------------
//    // Non public items
//    // -----------------
//    protected void insertNode(ITreeNode<T> nodeToInsert, ITreeNode<T> nodeToInsertOn) {
//        if(BoggleMode.DEBUG_MODE){
//            if ( nodeToInsertOn == null ) {
//                throw new IllegalArgumentException("nodeToInsertOn cannot be null");
//            }
//            if ( nodeToInsert == null ) {
//                throw new IllegalArgumentException("nodeToInsert cannot be null");
//            }
//        }
//        
//        List<ITreeNode<T>> nodeList = nodeToInsertOn.getNextLetterNodes();
//        // make sure it isn't already there
//        for (int i = 0; i < nodeList.size(); i++) {
//            T currentValue = nodeList.get(i).getValue();
//            if ( currentValue.equals(nodeToInsert.getValue()) )
//                return;
//        }
//        nodeList.add(nodeToInsert);
//    }
//
//    /**
//     * Performs a search 1 level deep for <b>nodeToFind</b> under
//     * <b>nodeToSearcUnder</b>. If it exists the it will be returned otherwise
//     * <b>null</b> will be returned.
//     * 
//     * @param nodeToFind
//     * @param nodeToSearchUnder
//     * @return
//     * @throws Exception 
//     */
//    protected ITreeNode<T> findNode(T nodeToFind, ITreeNode<T> nodeToSearchUnder) throws Exception {
//        if ( BoggleMode.DEBUG_MODE ) {
//            if ( nodeToSearchUnder == null ) {
//                throw new IllegalArgumentException(
//                        "nodeToSearchUnder cannot be null");
//            }
//            if ( nodeToFind == null ) {
//                throw new IllegalArgumentException("nodeToFind cannot be null");
//            }
////            if(nodeToFind.getValue()==null){
////                throw new IllegalArgumentException("nodeToFind cannot contain a null value.");
////            }
//        }
//        
//        List<ITreeNode<T>>nodes = nodeToSearchUnder.getNextLetterNodes();
//        int numNodes = nodes.size();
//        for(int i =0;i<numNodes;i++){
//            ITreeNode<T>node = nodes.get(i);
//            if ( BoggleMode.DEBUG_MODE ){
//                if(node==null){
//                    throw new Exception("Null node found");
//                }
//            }
//            
//            T value = node.getValue();
//            if(BoggleMode.DEBUG_MODE){
//                if(value==null){
//                    throw new Exception("Null value found");
//                }
//            }
//         
//            if(value.equals(nodeToFind))
//                return node;
//        }
//
//        return null;
//    }
//    
//    protected ITreeNode<T> findUnder(ITreeNode<T> parentNode,List<T>nodePath) throws Exception{
//        if(BoggleMode.DEBUG_MODE){
//            if(parentNode==null){
//                throw new IllegalArgumentException("parentNode cannot be empty");
//            }
//            if(nodePath==null||nodePath.size() <= 0){
//                throw new IllegalArgumentException("nodePath to find cannot be empty");
//            }
//        }
//        
//        //check primitive case
//        if(nodePath.size()==1){
//            ITreeNode<T> foundNode = this.findNode(nodePath.get(0), parentNode);
//            return foundNode;
//        }
//
//        List<T>newPath = new ArrayList<T>();
////      strip off the first node & recurse
//        for(int i =1;i<nodePath.size();i++){
//            newPath.add(nodePath.get(i));
//        }
//        return findUnder(nodePath.get(0),newPath);
//    }
//    
//
//    /**
//     * Will find the node at the end of the node path given.
//     * If it doesn't exist all nodes will be created that are
//     * needed.
//     * @param nodePath
//     * @return
//     * @throws Exception 
//     */
//    protected ITreeNode<T> findOrCreate(ITreeNode<T>parentNode,List<T> nodePath) throws Exception{
//        if(BoggleMode.DEBUG_MODE){
//            if(parentNode==null){
//                throw new IllegalArgumentException("parentNode cannot be empty");
//            }
//            if(nodePath==null||nodePath.size() <= 0){
//                throw new IllegalArgumentException("nodePath to find cannot be empty");
//            }
//        }
//        
//        //check primitive case
//        if(nodePath.size()==1){
//            ITreeNode<T> foundNode = this.findNode(nodePath.get(0), parentNode);
//            if(foundNode == null){
//                foundNode = new TreeNode<T>();
//                foundNode.setValue(nodePath.get(0));
//                parentNode.getNextLetterNodes().add(new TreeNode<T>(nodePath.get(0)));
//            }
//            return foundNode;
//        }
//
//        List<ITreeNode<T>>newPath = new ArrayList<ITreeNode<T>>();
////      strip off the first node & recurse
//        for(int i =1;i<nodePath.size();i++){
//            newPath.add(nodePath.get(i));
//        }
//        return findOrCreate(nodePath.get(0),newPath);
//    }
//}
