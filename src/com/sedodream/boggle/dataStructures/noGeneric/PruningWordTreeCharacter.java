///**
// * 
// */
//package com.sedodream.boggle.dataStructures.noGeneric;
//
///**
// * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
// */
//public class PruningWordTreeCharacter extends WordTreeCharacter {
//
//    public PruningWordTreeCharacter() {
//        super();
//        this.tree = new PruningTreeCharacter();
//    }
//    public SearchResult contains(String word) throws Exception {
//        
//        StringBuilder sb = new StringBuilder();
//        
//        word = word.toUpperCase().replace("QU", "~");
//        //decompose this into smaller items
//        char[] characters = new char[word.length()];
//        word.getChars(0, word.length(), characters, 0);
//        
//        TreeNodeCharacter leafNode = tree.find(characters);
//        if(leafNode==null){
//            //return false;
//            SearchResult result=new SearchResult(false,false);
//            return result;
//        }
//        else{
//            boolean hasChildren = leafNode.getHasChildren();
//            SearchResult result=new SearchResult(leafNode.isWord(),hasChildren);
//            return result;
//        }
//    }
//    @Override
//    public SearchResult findWord(String word) {
//        // TODO Auto-generated method stub
//        return super.findWord(word);
//    }
//    @Override
//    public void insert(String word) throws Exception {
//        // TODO Auto-generated method stub
//        super.insert(word);
//        try{
//        //go through and increment the num words under for each node
//        incrementNumWordsRecursive(word);
//        }
//        catch(Exception ex){
//            //TODO: REMOVE
//            @SuppressWarnings("unused")
//            String messgag =ex.getMessage();
//        }
//    } 
//    protected void incrementNumWordsRecursive(String word){
//        if(word==null||word.length()<=0){
//            //TODO: Remove
//            @SuppressWarnings("unused")
//            int debug = 33;
//            return;
//        }
//        TreeNodeCharacter[]nodes = tree.getNodes(word.replace("QU", "~").toCharArray());
//        for(TreeNodeCharacter node: nodes){
////            int num = node.getNumWordsRecursive()+1;
////            node.setNumWordsRecursive(num);
//            node.IncrementNumWordsRecursive();
//        }
//    }
//    protected void decrementNumWordsRecursive(String word){
//        TreeNodeCharacter[]nodes = tree.getNodes(word.toCharArray());
//        for(TreeNodeCharacter node: nodes){
////            int num = node.getNumWordsRecursive()-1;
////            node.setNumWordsRecursive(num);
//            node.DecrementNumWordsRecursive();
//        }
//    }
//    public void startPruning() {
//        ((PruningTreeCharacter)this.tree).setDoPruning(true);
//    }
//}
