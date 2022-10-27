/**
 * 
 */
package com.sedodream.boggle.sih;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.sedodream.boggle.sih.exceptions.BoardDataException;
import com.sedodream.boggle.sih.exceptions.BoardSizeException;
import com.sedodream.boggle.sih.player.PlayerUtil;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestRegion extends TestBase {
    private String filename4x4=".\\Files\\board4x4.txt";
    private String filename5x5=".\\Files\\board5x5.txt";
    
    @Test
    @Ignore("Region impl changed")
    public void TestUtil() throws IOException, BoardDataException, BoardSizeException{
        System.out.printf("Started TestUtil()\n");
        String fileText = ReadFile(filename5x5);
        int size = 5;
        //now we need to create the board
        IBoard board = new Board(fileText);
        
        IRegion regionAll = PlayerUtil.GetEntireRegion(board);
//        IRegion regionAll = new Region(
//                new Point(0,0),new Point(board.getBoardSize(),board.getBoardSize()));
        Assert.assertNotNull(regionAll);

        List<ICell>cells = PlayerUtil.FindCellsInRegion(regionAll, board);
        Assert.assertNotNull(cells);
        Assert.assertEquals(size*size, cells.size());
        
        IRegion region1 = new Region(new Point(0,0),new Point(2,2));
        cells = PlayerUtil.FindCellsInRegion(region1, board);
        System.out.printf("Num cells in region %d\n", cells.size());
        Assert.assertNotNull(cells);
        Assert.assertEquals(4, 4);
    }
    
    @Test
    public void TestEntireRegion() throws IOException, BoardDataException, BoardSizeException{
        System.out.printf("Started TestEntireRegion()------------------\n");
        String fileText = ReadFile(filename5x5);
        
        int size = 5;
        //now we need to create the board
        IBoard board = new Board(fileText);
     
        List<IRegion>regionList = PlayerUtil.CreateRegions(1, board);
        Assert.assertNotNull(regionList);
        
        IRegion regionAll = regionList.get(0);
        Assert.assertNotNull(regionAll);
        Assert.assertEquals(5*5,regionAll.getCellsInRegion().size());
        //Assert.assertEquals(5*5, )

        System.out.printf("End TestEntireRegion()----------------------\n");
    }
    @Test
    public void Test2Regions() throws IOException, BoardDataException, BoardSizeException{
        System.out.printf("Started TestEntireRegion()------------------\n");
        String fileText = ReadFile(filename5x5);
        
        int size = 5;
        //now we need to create the board
        IBoard board = new Board(fileText);
        int numRegions = 2;
        List<IRegion>regionList = PlayerUtil.CreateRegions(numRegions, board);
        Assert.assertNotNull(regionList);
        Assert.assertEquals(numRegions, regionList.size());
        
        int numCells = size*size;
        int currentNum = 0;
        for(int i =0;i<regionList.size();i++){
            Assert.assertNotNull(regionList.get(i));
            int numCellsInRegion = regionList.get(i).getCellsInRegion().size();
            currentNum += numCellsInRegion;
            System.out.printf("\t# cells in region: %d\n", regionList.get(i).getCellsInRegion().size());
        }
        Assert.assertEquals(numCells, currentNum);
        
        
        System.out.printf("End Test2Regions()----------------------\n");        
    }
    
    
//    private String ReadFile(String filename) throws IOException{
//        if(filename==null){
//            throw new IllegalArgumentException("filename was null");
//        }
//        
//        File file = new File(filename);
//        if(!file.exists()){
//            throw new FileNotFoundException(String.format("File not found: [%s]", file.getAbsolutePath()));
//        }
//        
//        BufferedReader bufferedReader = null;
//        FileReader fileReader = null;
//        System.out.printf("Reading word file %s\n", file.getAbsolutePath());
//
//        int currentCount = 0;
//        String currentLine = null;
//        Date startTime = new Date();
//        StringBuffer sb = new StringBuffer();
//        try {
//            fileReader = new FileReader(file);
//            bufferedReader = new BufferedReader(fileReader);
//
//            
//            while ((currentLine = bufferedReader.readLine()) != null) {
//                sb.append(currentLine.trim());
//            }
//        }
//        catch(Exception e){
//            String message = String.format("Unable to read the file. Message:\n%s", e.getMessage());
//            System.out.println(message);
//        }
//        finally {
//            if ( fileReader != null )
//                fileReader.close();
//            if ( bufferedReader != null )
//                bufferedReader.close();
//        }
//        
//        return sb.toString();
//    }
}
