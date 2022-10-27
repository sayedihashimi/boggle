package com.sedodream.boggle.sih;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.CommonsSynchronizedList;
import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.player.IPlayer;
import com.sedodream.boggle.sih.player.PlayerUtil;
import com.sedodream.boggle.sih.player.SimplePlayer;

public class TestSimplePlayer extends TestBase {
    protected String wordsIbrahim = ".\\Files\\wordsIbrahim.txt";
    
    
    private IBoard board25x25=null;
    private IDictionary wordList_Complete;
    private boolean boardsRead = false;
    
    @Before
    public void ReadBoards() throws Exception{
        if(!boardsRead){
            board25x25 = BuildBoardFromFile(".\\Files\\board25x25.txt");
            wordList_Complete = BuildDictionaryFromFile(wordListComplete);
            boardsRead=true;
        }
    }
    
    @Before
    public void TestSetup() {
        // set debug mode to false
        BoggleMode.DEBUG_MODE = false;
    }

    @Test
    //@Ignore
    public void TestPlayer1() throws Exception {
        IBoard board = BuildBoardFromFile(filename5x5);
        
        IRegion entireRegion =PlayerUtil.GetEntireRegion(board);
        //IRegion entireRegion = new Region(new Point(0, 0), new Point(board.getBoardSize(), board.getBoardSize()));

        System.out.println("Creating player");
        IPlayer player = new SimplePlayer(board, entireRegion);

        // super.BuildBoardFromFile(words5x5);
        IDictionary wordList = BuildDictionaryFromFile(words5x5);
        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");

            player.startPlay();
            
            resultList.addAll(player.getResultList());
            
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
        List<String> expectedWords = new ArrayList<String>();
        expectedWords.add("ABCD");
        expectedWords.add("AFK");
        expectedWords.add("AFKPU");
        expectedWords.add("AFKPUVWXY");
        expectedWords.add("GLMHIJOTYXWV");

        Assert.assertEquals(expectedWords.size(), resultList.size());
        for (int i = 0; i < expectedWords.size(); i++) {
            Assert
                    .assertEquals(true, resultList.contains(expectedWords
                            .get(i)));
        }
    }

    @Test
    public void Test4x4Qu() throws Exception{
        BoggleMode.DEBUG_MODE = true;
        Date startTime = new Date();

        int size = 4;
        String boardStr = knownBoard4x4.replace(",", "").trim();
        Assert.assertNotNull(boardStr);

        IBoard board = new Board(boardStr);
        Assert.assertNotNull(board);
        Assert.assertEquals(size, board.getBoardSize());

        
        IRegion entireRegion = PlayerUtil.GetEntireRegion(board);
//        IRegion entireRegion = new Region(new Point(0, 0), new Point(board
//                .getBoardSize(), board.getBoardSize()));

        System.out.println("Creating player");
        IPlayer player = new SimplePlayer(board, entireRegion);

        IDictionary wordList = BuildDictionaryFromFile(wordsExpected4x4);

        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList.addAll(player.getResultList());
            
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
        
        //make sure that each result is atleast 2 characters
        for(int i =0;i<resultList.size();i++){
            Assert.assertTrue(resultList.get(i).toString().length()>1);
        }
        
        
        Assert.assertEquals(knownStrings.size(), resultList.size());
    }
    
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

        
        IRegion entireRegion = PlayerUtil.GetEntireRegion(board);
//        IRegion entireRegion = new Region(new Point(0, 0), new Point(board
//                .getBoardSize() - 1, board.getBoardSize() - 1));

        System.out.println("Creating player");
        IPlayer player = new SimplePlayer(board, entireRegion);

        IDictionary wordList = BuildDictionaryFromFile(wordsExpected4x4);

        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList.addAll(player.getResultList());
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
    @Ignore("There may be something wrong with this test case")
    public void TestPlayer8x8() throws Exception {
        System.out.println("\nStarting TestPlayer8x8");
        Date startTime = new Date();

        IBoard board = BuildBoardFromFile(wordsExpected4x4);
        IRegion entireRegion = PlayerUtil.GetEntireRegion(board);
//        IRegion entireRegion = new Region(new Point(0, 0), new Point(board
//                .getBoardSize() - 1, board.getBoardSize() - 1));

        System.out.println("Creating player");
        IPlayer player = new SimplePlayer(board, entireRegion);

        // super.BuildBoardFromFile(words5x5);
        IDictionary wordList = BuildDictionaryFromFile(wordListComplete);
        // IDictionary wordList = BuildDictionaryFromFile(wordsIbrahim);

        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList.addAll(player.getResultList());
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
        System.out.printf("Done playing game");

        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\n\tTime to solve %d\n", milliSpent);

        // List<String> expectedWords = new ArrayList<String>();
        // expectedWords.add("ABCD");
        // expectedWords.add("AFK");
        // expectedWords.add("AFKPU");
        // expectedWords.add("AFKPUVWXY");
        // expectedWords.add("GLMHIJOTYXWV");
        //
        // Assert.assertEquals(expectedWords.size(), resultList.size());
        // for (int i = 0; i < expectedWords.size(); i++) {
        // Assert
        // .assertEquals(true, resultList.contains(expectedWords
        // .get(i)));
        // }
    }

    @Test
    //@Ignore
    public void TestPlayer25x25() throws Exception{
        this.TestPlayerGetTime25x25();
    }
    public long TestPlayerGetTime25x25() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        System.out.println("\nStarting TestPlayer25x25");
        Date startTime = new Date();

        //IBoard board25x25 = BuildBoardFromFile(filename25x25);
        IRegion entireRegion = PlayerUtil.GetEntireRegion(board25x25);
//        IRegion entireRegion = new Region(new Point(0, 0), new Point(board25x25
//                .getBoardSize() - 1, board25x25.getBoardSize() - 1));

        System.out.println("Creating player");
        IPlayer player = new SimplePlayer(board25x25, entireRegion);

        //IDictionary wordList_Complete = BuildDictionaryFromFile(wordListComplete);

        player.setWordList(wordList_Complete);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList.addAll(player.getResultList());
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
        System.out.printf("Done playing game\n\tNum words found:%s",
                resultList.size());

        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\n\tTime to solve %d milliseconds\n", milliSpent);

        return milliSpent;
        // List<String> expectedWords = new ArrayList<String>();
        // expectedWords.add("ABCD");
        // expectedWords.add("AFK");
        // expectedWords.add("AFKPU");
        // expectedWords.add("AFKPUVWXY");
        // expectedWords.add("GLMHIJOTYXWV");
        //
        // Assert.assertEquals(expectedWords.size(), resultList.size());
        // for (int i = 0; i < expectedWords.size(); i++) {
        // Assert
        // .assertEquals(true, resultList.contains(expectedWords
        // .get(i)));
        // }
    }

    @Test
    @Ignore
    public void TestPlayer100x100() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        System.out.println("\nStarting TestPlayer100x100");
        Date startTime = new Date();

        IBoard board = BuildBoardFromFile(filename100x100);
        IRegion entireRegion = PlayerUtil.GetEntireRegion(board);
//        IRegion entireRegion = new Region(new Point(0, 0), new Point(board
//                .getBoardSize() - 1, board.getBoardSize() - 1));

        System.out.println("Creating player");
        IPlayer player = new SimplePlayer(board, entireRegion);

        // super.BuildBoardFromFile(words5x5);
        IDictionary wordList = BuildDictionaryFromFile(wordListComplete);
        // IDictionary wordList = BuildDictionaryFromFile(wordsIbrahim);

        player.setWordList(wordList);

//        ISynchronizedList resultList = new CommonsSynchronizedList();
//        player.setResultList(resultList);
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");
            player.startPlay();
            resultList.addAll(player.getResultList());
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

        // List<String> expectedWords = new ArrayList<String>();
        // expectedWords.add("ABCD");
        // expectedWords.add("AFK");
        // expectedWords.add("AFKPU");
        // expectedWords.add("AFKPUVWXY");
        // expectedWords.add("GLMHIJOTYXWV");
        //
        // Assert.assertEquals(expectedWords.size(), resultList.size());
        // for (int i = 0; i < expectedWords.size(); i++) {
        // Assert
        // .assertEquals(true, resultList.contains(expectedWords
        // .get(i)));
        // }
    }

    @Test
    public void TestQ5x5(){
        
    }
    
    
    
    
    public static void main2(String[] args) {
        BoggleMode.DEBUG_MODE=false;
        TestSimplePlayer tsp = new TestSimplePlayer();
        try {
            tsp.ReadBoards();
            tsp.TestPlayerGetTime25x25();
        }
        catch (Exception e) {
            System.out.printf("There was an error playing the game. Message: %s\n",e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BoggleMode.DEBUG_MODE=false;
        int numIterations = 10;
        long allTime = 0;
        
        TestSimplePlayer tsp = new TestSimplePlayer();
        try {
            tsp.ReadBoards();
            for(int i =0;i<numIterations;i++){
                long thisTime = tsp.TestPlayerGetTime25x25();
                allTime+=thisTime;
            }
        }
        catch (Exception e) {
            System.out.printf("There was an error playing the game. Message: %s\n",e.getMessage());
            e.printStackTrace();
        }
        
        System.out.printf("\nNum tests: %d",numIterations);
        System.out.printf("\nTime spent: %d average time %d", allTime,allTime/numIterations);
        
    }
}
