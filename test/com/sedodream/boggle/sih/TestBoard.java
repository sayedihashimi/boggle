/**
 * 
 */
package com.sedodream.boggle.sih;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.sih.exceptions.BoardDataException;
import com.sedodream.boggle.sih.exceptions.BoardSizeException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestBoard extends TestBase {
    // private String filename4x4=".\\Files\\board4x4.txt";
    // private String filename5x5=".\\Files\\board5x5.txt";
    @Test
    public void Test4x4() throws IOException, BoardDataException,
            BoardSizeException {
        String fileText = ReadFile(filename4x4);
        // now we need to create the board
        IBoard board = new Board(fileText);
        fileText = fileText.toUpperCase().replace("QU", "Q");
        
        Assert.assertNotNull(board);
        Assert.assertEquals(4, board.getBoardSize());

        // check the contents of the board
        int currentIndex = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                char fromFile = fileText.charAt(currentIndex);
                IPoint loc = new Point(row, col);
                
                if(fromFile=='Q'){
                    fromFile='~';
                }
                
                Assert.assertEquals(fromFile, board.getValueAt(loc));
                currentIndex++;
                ICell cell = board.getCellAt(loc);
                Assert.assertNotNull(cell);
                Assert.assertEquals(loc, cell.getLocation());
                Assert.assertEquals(fromFile, cell.getValue());
            }
        }

        System.out.println("Done executing Test4x4");
    }

    @Test
    public void TestNeighboors() throws IOException, BoardDataException,
            BoardSizeException {
        BoggleMode.DEBUG_MODE = true;

        String fileText = ReadFile(filename5x5);
        int size = 5;
        // now we need to create the board
        IBoard board = new Board(fileText);

        Assert.assertNotNull(board);
        Assert.assertEquals(5, board.getBoardSize());

        // check the contents of the board
        int currentIndex = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char fromFile = fileText.charAt(currentIndex);
                IPoint loc = new Point(row, col);
                Assert.assertEquals(fromFile, board.getValueAt(loc));
                currentIndex++;
                ICell cell = board.getCellAt(loc);
                Assert.assertNotNull(cell);
                Assert.assertEquals(loc, cell.getLocation());
                Assert.assertEquals(fromFile, cell.getValue());
            }
        }

        // see how many neighboors the edges have
        System.out.println(String.format("\tTesting north edeg", null));

        // North row
        for (int col = 0; col < size; col++) {
            ICell cell = board.getCellAt(new Point(0, col));
            Assert.assertNotNull(cell);

            if ( BoggleMode.DEBUG_MODE ) {
                System.out.printf("Testing col %s\n", col);
            }

            List<ICell> neighbors = cell.getNeighbors();
            Assert.assertNotNull(neighbors);

            int numNeighboors = cell.getNeighbors().size();

            System.out.println(String.format(
                    "\t\tFinding neighbors [%d,%d] Num neighbors: %d", 0, col,
                    numNeighboors));
            if ( col == 0 || col == size - 1 ) {
                Assert.assertEquals(3, numNeighboors);
            }
            else {
                Assert.assertEquals(5, numNeighboors);
            }
        }
        
        System.out.println(String.format("\tTesting south edge", null));
        // south row
        for (int col = 0; col < size; col++) {
            ICell cell = board.getCellAt(new Point(size - 1, col));
            Assert.assertNotNull(cell);
            List<ICell> neighbors = cell.getNeighbors();
            Assert.assertNotNull(neighbors);

            int numNeighboors = cell.getNeighbors().size();
            System.out.println(String.format(
                    "\t\tFinding neighbors [%d,%d] Num neighbors: %d", 0, col,
                    numNeighboors));
        }
        // east edge
        for (int row = 0; row < size; row++) {
            ICell cell = board.getCellAt(new Point(row, 0));
            Assert.assertNotNull(cell);
            
            if(row==1){
                @SuppressWarnings("unused")
                String anErrorWillOcccur="There is an error";
            }
             
            
            
            List<ICell> neighbors=null;
            try{
                neighbors = cell.getNeighbors();
            }
            catch(Exception ex){
                @SuppressWarnings("unused")
                String message = ex.getMessage();
            }
            
            if(neighbors==null){
                
            }
            Assert.assertNotNull(neighbors);

            int numNeighboors = cell.getNeighbors().size();
            System.out.println(String.format(
                    "\t\tFinding neighbors [%d,%d] Num neighbors: %d", 0, row,
                    numNeighboors));
        }
        // east edge
        for (int row = 0; row < size; row++) {
            ICell cell = board.getCellAt(new Point(row, size - 1));
            Assert.assertNotNull(cell);
            List<ICell> neighbors = cell.getNeighbors();
            Assert.assertNotNull(neighbors);

            int numNeighboors = cell.getNeighbors().size();
            System.out.println(String.format(
                    "\t\tFinding neighbors [%d,%d] Num neighbors: %d", 0, row,
                    numNeighboors));
        }

        // center cells
        for (int row = 1; row < size - 1; row++) {
            for (int col = 1; col < size - 1; col++) {
                ICell cell = board.getCellAt(new Point(row, size - 1));
                Assert.assertNotNull(cell);
                List<ICell> neighbors = cell.getNeighbors();
                Assert.assertNotNull(neighbors);

                int numNeighboors = cell.getNeighbors().size();
                System.out.println(String.format(
                        "\t\tFinding neighbors [%d,%d] Num neighbors: %d", 0,
                        row, numNeighboors));
            }
        }

        System.out.println("Done executing TestNeighboors");
    }

    @Test
    public void TestNeighboorContents() throws IOException, BoardDataException,
            BoardSizeException {
        String fileText = ReadFile(filename5x5);
        int size = 5;
        // now we need to create the board
        IBoard board = new Board(fileText);

        Assert.assertNotNull(board);
        Assert.assertEquals(5, board.getBoardSize());

        // check the contents of the board
        int currentIndex = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char fromFile = fileText.charAt(currentIndex);
                IPoint loc = new Point(row, col);
                Assert.assertEquals(fromFile, board.getValueAt(loc));
                currentIndex++;
                ICell cell = board.getCellAt(loc);
                Assert.assertNotNull(cell);
                Assert.assertEquals(loc, cell.getLocation());
                Assert.assertEquals(fromFile, cell.getValue());
            }
        }

        // see how many neighboors the edges have
        System.out.println(String.format("\tTesting north edeg", null));

        List<ICell> nei00 = board.getCellAt(new Point(0, 0)).getNeighbors();
        Assert.assertNotNull(nei00);
        Assert.assertEquals(3, nei00.size());

        Map<IPoint, String> neighboorMap = BuildNeighboorMapFor();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                IPoint point = new Point(row, col);
                List<ICell> neighboors = board.getCellAt(point).getNeighbors();
                Assert.assertNotNull(nei00);

                if ( neighboorMap.containsKey(point) ) {
                    String expectedString = neighboorMap.get(point);
                    System.out.printf(
                            "Checking [%d,%d] Expected neighboors %s\n.", point
                                    .getRow(), point.getCol(), expectedString);
                    for (int i = 0; i < neighboors.size(); i++) {
                        ICell cell = neighboors.get(i);
                        String value = "" + cell.getValue();
                        value = value.toUpperCase();
                        System.out.printf("\tLooking for [%s]\n", value);
                        Assert.assertTrue(expectedString.toUpperCase()
                                .contains(value));
                    }
                }
            }
        }
        // //North row
        // for(int col = 0;col<size;col++){
        // ICell cell = board.getCellAt(new Point(0,col));
        // Assert.assertNotNull(cell);
        // List<ICell>neighbors = cell.getNeighbors();
        // Assert.assertNotNull(neighbors);
        //            
        // int numNeighboors = cell.getNeighbors().size();
        //            
        // System.out.println(String.format("\t\tFinding neighbors [%d,%d] Num
        // neighbors: %d", 0,col,numNeighboors));
        // if(col==0||col==size-1){
        // Assert.assertEquals(2, numNeighboors);
        // }
        // else{
        // Assert.assertEquals(3, numNeighboors);
        // }
        // }
    }

    private Map<IPoint, String> BuildNeighboorMapFor() {
        Map<IPoint, String> neighboorMap = new HashMap<IPoint, String>();
        neighboorMap.put(new Point(0, 0), "BFG");
        neighboorMap.put(new Point(0, 1), "ACGFH");
        neighboorMap.put(new Point(0, 2), "BHDGI");
        neighboorMap.put(new Point(0, 3), "CEIHJ");
        neighboorMap.put(new Point(0, 4), "DJI");

        neighboorMap.put(new Point(1, 0), "AKGBL");
        neighboorMap.put(new Point(1, 1), "BFHLAKCM");
        neighboorMap.put(new Point(1, 2), "CGIMBLDN");
        neighboorMap.put(new Point(1, 3), "DHJNCMEO");
        neighboorMap.put(new Point(1, 4), "EIODN");

        neighboorMap.put(new Point(2, 0), "FLPGQ");
        neighboorMap.put(new Point(2, 1), "GKMQFPHR");
        neighboorMap.put(new Point(2, 2), "HLNRGQIS");
        neighboorMap.put(new Point(2, 3), "IMOSHRJT");
        neighboorMap.put(new Point(2, 4), "JNTIS");

        neighboorMap.put(new Point(3, 0), "KQULV");
        neighboorMap.put(new Point(3, 1), "LPRVKUMW");
        neighboorMap.put(new Point(3, 2), "MQSWLVNX");
        neighboorMap.put(new Point(3, 3), "NRTXMWOY");
        neighboorMap.put(new Point(3, 4), "OSYNX");

        neighboorMap.put(new Point(4, 0), "PVQ");
        neighboorMap.put(new Point(4, 1), "UQWPR");
        neighboorMap.put(new Point(4, 2), "VRXQS");
        neighboorMap.put(new Point(4, 3), "SWYRT");
        neighboorMap.put(new Point(4, 4), "TXS");

        return neighboorMap;
    }

    // private String ReadFile(String filename) throws IOException{
    // if(filename==null){
    // throw new IllegalArgumentException("filename was null");
    // }
    //        
    // File file = new File(filename);
    // if(!file.exists()){
    // throw new FileNotFoundException(String.format("File not found: [%s]",
    // file.getAbsolutePath()));
    // }
    //        
    // BufferedReader bufferedReader = null;
    // FileReader fileReader = null;
    // System.out.printf("Reading word file %s\n", file.getAbsolutePath());
    //
    // int currentCount = 0;
    // String currentLine = null;
    // Date startTime = new Date();
    // StringBuffer sb = new StringBuffer();
    // try {
    // fileReader = new FileReader(file);
    // bufferedReader = new BufferedReader(fileReader);
    //
    //            
    // while ((currentLine = bufferedReader.readLine()) != null) {
    // sb.append(currentLine.trim());
    // }
    // }
    // catch(Exception e){
    // String message = String.format("Unable to read the file. Message:\n%s",
    // e.getMessage());
    // System.out.println(message);
    // }
    // finally {
    // if ( fileReader != null )
    // fileReader.close();
    // if ( bufferedReader != null )
    // bufferedReader.close();
    // }
    //        
    // return sb.toString();
    // }
}
