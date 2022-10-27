package hw4.boggle.dictionary;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeNodeCharacter {
    //  Fields
    private char    value;
    private Boolean isWord;
    private TreeNodeCharacter[]  nextLetters;

    private boolean hasChildren;
    private boolean wordRead;
    
    private AtomicInteger atomicNumWordsRecursive;
    
    private TreeNodeCharacter parent;
    // Constructors
    public TreeNodeCharacter() {
        this.setIsWord(false);
        this.hasChildren=true;
        this.wordRead = false;
        this.atomicNumWordsRecursive = new AtomicInteger(0);
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
        return this.nextLetters;
    }

    @Override
    public String toString() {
        String nextStr = "(null)";
        if(nextLetters!=null){
            nextStr = Integer.toString(nextLetters.length);
        }
        return String.format("[%s,isWord=%s,%s]", value,isWord,nextStr);
    }

    public boolean getHasChildren(){
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
    }
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
