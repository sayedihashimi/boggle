/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.CommonsSynchronizedList;
import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.player.IPlayer;
import com.sedodream.boggle.sih.player.ManagedPlayer;
import com.sedodream.boggle.sih.player.PlayerUtil;
import com.sedodream.boggle.sih.player.SimplePlayer;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestManagedPlayer extends TestBase {

    @Test
    //@Ignore
    public void TestKnown4x4() throws Exception {
        BoggleMode.DEBUG_MODE = true;
        Date startTime = new Date();

        int size = 4;
        String boardStr = knownBoard4x4.replace(",", "").trim();
        Assert.assertNotNull(boardStr);

        IBoard board = new Board(boardStr);
        Assert.assertNotNull(board);
        Assert.assertEquals(size, board.getBoardSize());

        System.out.println("Creating player");
        ICellManager cellManager = new CellManager(board);
        IPlayer player = new ManagedPlayer(board,cellManager);

        IDictionary wordList = BuildDictionaryFromFile(wordsExpected4x4);        
        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = null;
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList=player.getResultList();
            if ( BoggleMode.DEBUG_MODE ) {
                System.out
                        .printf(
                                "Playing finished num words found: %s\n Found words:\n",
                                resultList.size());
                for (int i = 0; i < resultList.size(); i++) {
                    System.out.printf("\t%s\n", resultList.get(i));
                }
            }
        }
        catch (Exception ex) {
            String message = String.format(
                    "An error has occurred playing board. Message:\n%s", ex
                            .getMessage());
            System.out.println(message);
        }
        Date endTime = new Date();
        System.out.printf("Done playing game\n\tNum words found:%s\n",
                resultList.size());

        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to solve %d milliseconds\n", milliSpent);

        System.out.println("Checking against known results");
        List<String> knownStrings = new ArrayList<String>();
        knownStrings.add("oat".toUpperCase());
        knownStrings.add("oath".toUpperCase());
        knownStrings.add("oar".toUpperCase());
        knownStrings.add("ate".toUpperCase());
        knownStrings.add("art".toUpperCase());
        knownStrings.add("artie".toUpperCase());
        knownStrings.add("ark".toUpperCase());
        knownStrings.add("ani".toUpperCase());
        knownStrings.add("air".toUpperCase());
        knownStrings.add("nat".toUpperCase());
        knownStrings.add("nato".toUpperCase());
        knownStrings.add("nate".toUpperCase());
        knownStrings.add("nair".toUpperCase());
        knownStrings.add("eat".toUpperCase());
        knownStrings.add("ear".toUpperCase());
        knownStrings.add("earn".toUpperCase());
        knownStrings.add("earth".toUpperCase());
        knownStrings.add("eta".toUpperCase());
        knownStrings.add("toe".toUpperCase());
        knownStrings.add("tao".toUpperCase());
        knownStrings.add("tar".toUpperCase());
        knownStrings.add("tara".toUpperCase());
        knownStrings.add("tan".toUpperCase());
        knownStrings.add("tea".toUpperCase());
        knownStrings.add("tear".toUpperCase());
        knownStrings.add("tehran".toUpperCase());
        knownStrings.add("train".toUpperCase());
        knownStrings.add("tie".toUpperCase());
        knownStrings.add("the".toUpperCase());
        knownStrings.add("thea".toUpperCase());
        knownStrings.add("rae".toUpperCase());
        knownStrings.add("rat".toUpperCase());
        knownStrings.add("rata".toUpperCase());
        knownStrings.add("rate".toUpperCase());
        knownStrings.add("ran".toUpperCase());
        knownStrings.add("rain".toUpperCase());
        knownStrings.add("rna".toUpperCase());
        knownStrings.add("rhea".toUpperCase());
        knownStrings.add("ian".toUpperCase());
        knownStrings.add("ira".toUpperCase());
        knownStrings.add("irate".toUpperCase());
        knownStrings.add("iran".toUpperCase());
        knownStrings.add("irk".toUpperCase());
        knownStrings.add("ito".toUpperCase());
        knownStrings.add("heat".toUpperCase());
        knownStrings.add("hear".toUpperCase());
        knownStrings.add("heart".toUpperCase());
        knownStrings.add("hit".toUpperCase());
        knownStrings.add("kin".toUpperCase());
        knownStrings.add("fit".toUpperCase());

        for (int i = 0; i < knownStrings.size(); i++) {
            String strToTest = knownStrings.get(i);
            System.out.printf("\tverifying word :%s\n", strToTest);
            Assert.assertTrue(resultList.contains(strToTest));
        }
        if(knownStrings.size()!=resultList.size()){
            System.out.printf("\n\tMismatch num known words: %d\n\t\tNum found:%d",
                    knownStrings.size(), resultList.size());
        }
        Assert.assertEquals(knownStrings.size(), resultList.size());
    }
    
    @Test
    //@Ignore
    public void TestKnown16x16() throws Exception {
        BoggleMode.DEBUG_MODE = true;
        Date startTime = new Date();

        int size = 16;
        
//        String boardStr = knownBoard4x4.replace(",", "").trim();
//        Assert.assertNotNull(boardStr);
//
//        IBoard board = new Board(boardStr);
        IBoard board = BuildBoardFromFile(filename16x16);
        Assert.assertNotNull(board);
        Assert.assertEquals(size, board.getBoardSize());

        System.out.println("Creating player");
        ICellManager cellManager = new CellManager(board);
        IPlayer player = new ManagedPlayer(board,cellManager);

        IDictionary wordList = BuildDictionaryFromFile(wordsExpected4x4);

        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = null;
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList=player.getResultList();
            
            if ( BoggleMode.DEBUG_MODE ) {
                System.out
                        .printf(
                                "Playing finished num words found: %s\n Found words:\n",
                                resultList.size());
                for (int i = 0; i < resultList.size(); i++) {
                    System.out.printf("\t%s\n", resultList.get(i));
                }
            }
        }
        catch (Exception ex) {
            String message = String.format(
                    "An error has occurred playing board. Message:\n%s", ex
                            .getMessage());
            System.out.println(message);
        }
        Date endTime = new Date();
        System.out.printf("Done playing game\n\tNum words found:%s\n",
                resultList.size());

        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to solve %d milliseconds\n", milliSpent);
    }
}
