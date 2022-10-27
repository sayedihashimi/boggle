/**
 * 
 */
package com.sedodream.boggle.sih;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.CommonsSynchronizedList;
import com.sedodream.boggle.dataStructures.ISynchronizedList;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.player.IPlayer;
import com.sedodream.boggle.sih.player.PlayerUtil;
import com.sedodream.boggle.sih.player.SimplePlayer;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestMultiThreadSimplePlayer25x25 extends TestBase{
    
//    public static String board25x25Contents = null;
    
//    @BeforeClass
//    public static void ReadBoards(){
//        board25x25Contents = BuildBoardFromFile(filename25x25)
//    }
    
    @Test
    public void Test1Thread() throws Exception{
        DoTest(1);
    }
    @Test
    @Ignore
    public void Test2Thread() throws Exception{
        DoTest(2);
    }
    @Test
    public void Test8Thread() throws Exception{
        DoTest(8);
    }
    @Test
    public void Test16Thread() throws Exception{
        DoTest(16);
    }
    public void DoTest(int numThreads) throws Exception{
        System.out.printf("\nBegin play for %d threads ============================================================\n", 
                numThreads);
       
        
        BoggleMode.DEBUG_MODE = false;
        Date startTime = new Date();

        //IBoard board = new Board(boardStr);
        IBoard board = BuildBoardFromFile(".\\Files\\board32x32.txt");
        Assert.assertNotNull(board);
        //Assert.assertEquals(size, board.getBoardSize());
        
        List<IRegion>regions = PlayerUtil.CreateRegions(numThreads, board);
        Assert.assertNotNull(regions);
        Assert.assertEquals(numThreads, regions.size());
        
        for(int i=0;i<regions.size();i++){
            Assert.assertNotNull(regions.get(i));
        }
        
        System.out.println("Creating player(s)");
        
        IDictionary wordList = BuildDictionaryFromFile(wordListComplete);
        //ISynchronizedList resultList = new CommonsSynchronizedList();
        List<String>resultList = new ArrayList<String>();
        
        List<IPlayer> playerList = new ArrayList<IPlayer>();
        for(int i=0;i<regions.size();i++){
            IPlayer newPlayer = new SimplePlayer(board,regions.get(i));
            Assert.assertNotNull(newPlayer);
            newPlayer.setWordList(wordList);
            //newPlayer.setResultList(resultList);
            playerList.add(newPlayer);
        }

        List<Thread>threads = new ArrayList<Thread>();
        
        try {
            System.out.println("Starting to play");
            
            for(int i=0;i<playerList.size();i++){
                Thread playerThread = new Thread(playerList.get(i));
                threads.add(playerThread);
                
                System.out.printf("\tStarting play for: [%d] -----------------------------------------------\n",i,playerThread.getId());
                
                playerThread.start();
                
                //playerList.get(i).startPlay();
                //System.out.printf("\tPlayer %d finished--------------------------------------------------\n", i);
            }
            //wait for threads to finish
            for(int i =0;i<threads.size();i++){
                threads.get(i).join();
                resultList.addAll(playerList.get(i).getResultList());
            }
            
            
            //player.startPlay();
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
    
    public static void main(String[] args) throws Exception {
        TestMultiThreadSimplePlayer25x25 tester = new TestMultiThreadSimplePlayer25x25();
        tester.Test8Thread();
    }
}
