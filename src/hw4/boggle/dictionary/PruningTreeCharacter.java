package hw4.boggle.dictionary;

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
    public TreeNodeCharacter find(char[] nodePath) {

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
        TreeNodeCharacter leafNode = find(word);
        if(leafNode==null){
            //return false;
        	// SearchResult false,false
            return ISearchResult.CONTAINS_FALSE__HASCHILDREN_FALSE;
        }
        else{
            boolean hasChildren = leafNode.getHasChildren();
            boolean isWord = leafNode.isWord();
            if(hasChildren && isWord) {
            	return ISearchResult.CONTAINS_TRUE__HASCHILDREN_TRUE;
            } else if( !isWord && hasChildren) {
            	return ISearchResult.CONTAINS_FALSE__HASCHILDREN_TRUE;
            } else 
            	return ISearchResult.CONTAINS_TRUE__HASCHILDREN_FALSE;
        }
    }
    @Override
    public TreeNodeCharacter insert(char[] word){
        // TODO Auto-generated method stub
        TreeNodeCharacter leafNode = super.insert(word);
        TreeNodeCharacter parent = leafNode;
        leafNode.IncrementNumWordsRecursive();
        
        while((parent=parent.getParent())!=null){
            parent.IncrementNumWordsRecursive();
        }

        return leafNode;
    }

    protected void decrementNumWordsRecursive(TreeNodeCharacter leafNode){
        TreeNodeCharacter parent = leafNode;
        while((parent=parent.getParent())!=null){
            parent.DecrementNumWordsRecursive();
        }
    }
}
