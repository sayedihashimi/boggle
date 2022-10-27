/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestWordTreeCharacter {

    /**
     * Fields
     */
    private static List<String> wordList;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // create the word list
        wordList = new ArrayList<String>();
        // add words to the list
        wordList.add("aa");
        wordList.add("aah");
        wordList.add("aahed");
        wordList.add("aahing");
        wordList.add("aahs");
        wordList.add("aal");
        wordList.add("aalii");
        wordList.add("aaliis");
        wordList.add("aals");
    }

    @Test
    public void TestInsert() throws Exception {
        WordTreeCharacter wtc = new WordTreeCharacter();
        Assert.assertNotNull(wtc);

        for (int i = 0; i < wordList.size(); i++) {
            wtc.insert(wordList.get(i));

            Assert.assertEquals(true, wtc.contains(wordList.get(i))
                    .getContains());
        }
    }

    @Test
    public void TestHasChildren() throws Exception {
        WordTreeCharacter wtc = new WordTreeCharacter();
        Assert.assertNotNull(wtc);

        for (int i = 0; i < wordList.size(); i++) {
            wtc.insert(wordList.get(i));
            Assert.assertEquals(true, wtc.contains(wordList.get(i))
                    .getContains());
        }
        System.out.printf("Testing has children\n");
        //loop through every char in each word, except last, and make sure its has children is true
        for(int i=0;i<wordList.size();i++){
            String word = wordList.get(i);
            int length = word.length();
            for(int j=1;j<length-1;j++){
                String path = word.substring(0,j);
                SearchResult result = wtc.contains(path);
                System.out.printf("\tpath [%s,%s,%s]\n", path,result.getHasChildren(),result.getContains());
                if(!result.getHasChildren()){
                    System.out.printf("\t\tWord %s doesn't have children\n",path);
                }
                Assert.assertEquals(true, result.getHasChildren());
            }
        }
    }
}









