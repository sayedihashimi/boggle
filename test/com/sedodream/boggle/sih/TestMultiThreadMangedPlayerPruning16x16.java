/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.player.IPlayer;
import com.sedodream.boggle.sih.player.ManagedPlayer;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestMultiThreadMangedPlayerPruning16x16 extends TestBase {
    @Test
    public void Test1Thread() throws Exception{
        DoTest(1);
    }
    @Test
    //@Ignore
    public void Test2Thread() throws Exception{
        DoTest(2);
    }
    @Test
    //@Ignore
    public void Test8Thread() throws Exception{
        DoTest(8);
    }
    @Test
    //@Ignore
    public void Test16Thread() throws Exception{
        DoTest(16);
    }
    @Test
    @Ignore
    public void Test64Thread() throws Exception{
        DoTest(64);
    }
    public void DoTest(int numThreads) throws Exception{
        System.out.printf("\nBegin play for %d threads ============================================================\n", 
                numThreads);
               
        BoggleMode.DEBUG_MODE = false;
        Date startTime = new Date();

        IBoard board = BuildBoardFromFile(filename16x16);
        Assert.assertNotNull(board);
        
        System.out.println("Creating player(s)");
        
        IDictionary wordList = BuildDictionaryFromFile(wordListComplete);
        //ISynchronizedList resultList = new CommonsSynchronizedList();
        
        List<IPlayer> playerList = new ArrayList<IPlayer>();
        ICellManager cellManager = new CellManager(board);
        for(int i=0;i<numThreads;i++){
            IPlayer newPlayer = new ManagedPlayer(board,cellManager);
            Assert.assertNotNull(newPlayer);
            newPlayer.setWordList(wordList);
            //newPlayer.setResultList(resultList);
            playerList.add(newPlayer);
        }

        List<Thread>threads = new ArrayList<Thread>();
        
        List<String>resultList = new ArrayList<String>();
        try {
            System.out.println("Starting to play");
            
            for(int i=0;i<playerList.size();i++){
                Thread playerThread = new Thread(playerList.get(i));
                threads.add(playerThread);
                
                System.out.printf("\tStarting play for: [%d] -----------------------------------------------\n",i,playerThread.getId());
                
                playerThread.start();
            }
            
            
            //wait for threads to finish
            for(int i =0;i<threads.size();i++){
                threads.get(i).join();
            }

            for(int i=0;i<playerList.size();i++){
                resultList.addAll(playerList.get(i).getResultList());
            }
            
            if ( BoggleMode.DEBUG_MODE ) {
                System.out
                        .printf(
                                "Playing finished num words found: %s\n Found words:\n",
                                resultList.size());
                for (int i = 0; i < resultList.size(); i++) {
                    System.out.printf("\t%s\n", resultList.get(i));
                }
            }
            int numExpectedResults = 3724;
            int numFound = resultList.size();
            if(numExpectedResults!=numFound){
                System.out.printf("Expected results incorrect. Expected %d, found %d.\n", numExpectedResults,numFound);
                for (int i = 0; i < resultList.size(); i++) {
                    System.out.printf("\t%s\n", resultList.get(i));
                }
            }
            //Assert.assertEquals(numExpectedResults, numFound);
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
    public static void main(String[] args) throws Exception {
        BoggleMode.DEBUG_MODE=false;
        TestMultiThreadMangedPlayerPruning16x16 tester = new TestMultiThreadMangedPlayerPruning16x16();
        tester.Test8Thread();
    }
}
