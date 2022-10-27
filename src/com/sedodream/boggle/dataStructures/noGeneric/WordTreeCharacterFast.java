/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class WordTreeCharacterFast extends WordTreeCharacter {

    public WordTreeCharacterFast() {
        super();
    }

    public SearchResult contains(String word) throws Exception {
        // decompose this into smaller items
        int length = word.length();
        char[] characters = new char[length + 1];
        characters[0] = (char) length;

        word.getChars(0, length, characters, 1);

        TreeNodeCharacter leafNode = tree.find(characters);
        if ( leafNode == null ) {
            SearchResult result = new SearchResult(false, false);
            return result;
        }
        else {
            boolean hasChildren = leafNode.getHasChildren();
            // boolean hasChildren=false;
            // TreeNodeCharacter[]children = leafNode.getNextLetterNodes();
            // if(children!=null&&children.length>0)
            // hasChildren=true;
            // //oldTODO: We can optimize by placing this info in the
            // TreeNodeCharacter object
            SearchResult result = new SearchResult(leafNode.isWord(),
                    hasChildren);
            return result;
        }
        // only find whole words
        // return leafNode.isWord();
    }

    public void insert(String word) throws Exception {
        int length = word.length();
        char[] characters = new char[length + 1];
        characters[0] = (char) length;
        word.getChars(0, length, characters, 1);

        TreeNodeCharacter leafNode = tree.insertAfter(characters);
        leafNode.setIsWord(true);
    }
}
