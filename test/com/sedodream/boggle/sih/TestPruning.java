/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.ArrayList;
import java.util.List;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.noGeneric.PruningTreeCharacter;
import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;
import com.sedodream.boggle.drc.IDictionary;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestPruning extends TestBase{

    @Test
    public void DoSimpleTest() throws Exception{
        BoggleMode.DEBUG_MODE = true;
        PruningTreeCharacter wordList = BuildPruningDictionaryFromFile("./Files/pruningTestWords.txt");
        Assert.assertNotNull(wordList);
        
        List<String>words = new ArrayList<String>();
        words.add("AT");
        words.add("ATE");
        words.add("ATTACK");
        words.add("BACK");
        words.add("BOMB");
        words.add("BOLD");
        words.add("BLADE");
        
        for(String word: words){
            SearchResult res1 = wordList.findWord(word.toCharArray());
            Assert.assertEquals(true, res1.getContains());
            SearchResult res2 = wordList.findWord(word.toCharArray());
            Assert.assertEquals(false, res2.getContains());
        }
        
        
        int debug = 3;
    }
    
    
}

