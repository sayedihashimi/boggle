/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

import com.sedodream.boggle.BoggleMode;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class PruningTreeCharacter extends TreeCharacter {
    private boolean doPruning;

    public PruningTreeCharacter() {
        super();
        doPruning = false;
    }

    public boolean isDoPruning() {
        return doPruning;
    }

    public void setDoPruning(boolean doPruning) {
        this.doPruning = doPruning;
    }

    @Override
    public TreeNodeCharacter find(char[] nodePath) throws Exception {
        if ( BoggleMode.DEBUG_MODE ) {
            if ( nodePath == null || nodePath.length <= 0 ) {
                throw new IllegalArgumentException("nodePath cannot be empty");
            }
        }

        TreeNodeCharacter currentNode = this.getRootNode();

        for (int i = 0; i < nodePath.length; i++) {
            char currentValue = nodePath[i];
            currentNode = this.find(currentNode, currentValue, false);
            if ( currentNode == null )
                return null;

            //TODO: Uncomment later
            if ( doPruning && currentNode.getNumWordsRecursive() <= 0 ) {
                return null;
            }
        }

        return currentNode;
    }

    @Override
    protected TreeNodeCharacter find(TreeNodeCharacter parentNode, char value,
            boolean createNonExisting) {
        TreeNodeCharacter foundNode = doFindUnderOneLevel(value, parentNode);

        if ( foundNode == null && createNonExisting ) {
            foundNode = new TreeNodeCharacter(value);
            foundNode.setParent(parentNode);

            TreeNodeCharacter[] nextNodes = parentNode.getNextLetterNodes();
            TreeNodeCharacter[] newNextNodes = new TreeNodeCharacter[1];

            if ( nextNodes != null ) {
                newNextNodes = new TreeNodeCharacter[nextNodes.length + 1];
                System.arraycopy(nextNodes, 0, newNextNodes, 0,
                        nextNodes.length);
            }
            newNextNodes[newNextNodes.length - 1] = foundNode;
            // parentNode.getNextLetterNodes().add(foundNode);
            parentNode.setNextLetterNodes(newNextNodes);
        }
        return foundNode;
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
                boolean isWord = leafNode.isWord();
                if(isWord){
                    //decrementNumWordsRecursive(word)
                }
                SearchResult result=new SearchResult(isWord,hasChildren);
                return result;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            RuntimeException re = new RuntimeException(e);
            throw re;
        }
    }
    @Override
    public TreeNodeCharacter insert(char[] word) throws Exception {
        // TODO Auto-generated method stub
        TreeNodeCharacter leafNode = super.insert(word);
        TreeNodeCharacter parent = leafNode;
        leafNode.IncrementNumWordsRecursive();
        
        while((parent=parent.getParent())!=null){
            //parent.setNumWordsRecursive(numWordsRecursive);
            parent.IncrementNumWordsRecursive();
        }
        
//        try {
//            // go through and increment the num words under for each node
//            //incrementNumWordsRecursive(word);
//            
//        }
//        catch (Exception ex) {
//            // TODO: REMOVE
//            @SuppressWarnings("unused")
//            String messgag = ex.getMessage();
//        }
        return leafNode;
    }

    // protected void incrementNumWordsRecursive(String word){
    // if(word==null||word.length()<=0){
    // //TODO: Remove
    // @SuppressWarnings("unused")
    // int debug = 33;
    // return;
    // }
    // TreeNodeCharacter[]nodes = getNodes(word.replace("QU",
    // "~").toCharArray());
    // for(TreeNodeCharacter node: nodes){
    // int num = node.getNumWordsRecursive()+1;
    // node.setNumWordsRecursive(num);
    // }
    // }
    protected void decrementNumWordsRecursive(TreeNodeCharacter leafNode){
        TreeNodeCharacter parent = leafNode;
        while((parent=parent.getParent())!=null){
            parent.DecrementNumWordsRecursive();
        }
    }
//    protected void decrementNumWordsRecursive(String word) {
//        TreeNodeCharacter[] nodes = getNodes(word.toCharArray());
//        for (TreeNodeCharacter node : nodes) {
//            node.DecrementNumWordsRecursive();
////            int num = node.getNumWordsRecursive() - 1;
////            node.setNumWordsRecursive(num);
//        }
//    }
}
