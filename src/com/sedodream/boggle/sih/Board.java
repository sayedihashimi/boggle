/**
 * 
 */
package com.sedodream.boggle.sih;

import java.io.IOException;
import java.io.StringReader;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.sih.exceptions.BoardDataException;
import com.sedodream.boggle.sih.exceptions.BoardSizeException;
import com.sedodream.boggle.sih.exceptions.InvalidBoardRequestException;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class Board implements IBoard {
    private static final String CONSTANT = "Unable to determine board size. Length: %s"; //$NON-NLS-1$
    private char[][] boardData;
    private ICell[][] cells;
    private int      boardSize;

    /**
     * Initalize the board from a string that contains all the characters in
     * sequence.
     * 
     * @throws BoardDataException
     * @throws BoardSizeException
     * @throws IOException 
     */
    public Board(String boardData) throws BoardDataException,
            BoardSizeException, IOException {
        if ( BoggleMode.DEBUG_MODE ) {
            if ( boardData == null || boardData.trim().length() <= 0 ) {
                throw new BoardDataException("Board data was empty");
            }
        }

        boardData=boardData.toUpperCase().replace("QU", "~");
        
        int length = boardData.length();
        int size = (int) Math.sqrt(length);

        if ( BoggleMode.DEBUG_MODE ) {
            if ( size * size != length ) {
                String message = String.format(
                        CONSTANT, length);
                throw new BoardSizeException(message);
            }
        }

        this.boardSize = size;
        this.boardData = new char[boardSize][boardSize];
        this.cells=new ICell[boardSize][boardSize];
        
        StringReader reader = null;
        try {
            reader= new StringReader(boardData);
            // new StringReader(boardData);
            for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
                for (int colIndex = 0; colIndex < boardSize; colIndex++) {
                    char value = (char) reader.read();
                    this.boardData[rowIndex][colIndex]=value;
                    IPoint location = new Point(rowIndex,colIndex);
                    ICell cell = new Cell(this,location);
                    this.cells[rowIndex][colIndex]=cell;
                }
            }
        }
        catch(Exception e){
            String message = String.format("Unable to read create board file. Message:\n%s", e.getMessage());
            System.out.println(message);
        }
        finally {
            if(reader != null)
                reader.close();
        }
    }
    public int getBoardSize() {
        return boardSize;
    }
    public char getValueAt(IPoint location) {
        if(BoggleMode.DEBUG_MODE){
            int row = location.getRow();
            int col = location.getCol();
            if(row < 0 || col <0 ){
                throw new InvalidBoardRequestException(String.format("Board size: %s. Request out of bounds [%d,%d]",this.getBoardSize(),
                        row,col));
            }
            if(row>getBoardSize()-1||col>getBoardSize()-1){
                throw new InvalidBoardRequestException(String.format("Board size: %s. Request out of bounds [%d,%d]",this.getBoardSize(),
                        row,col));
            }
        }
        
        return this.boardData[location.getRow()][location.getCol()];
    }
    public ICell getCellAt(IPoint location) {
        if(BoggleMode.DEBUG_MODE){
            int row = location.getRow();
            int col = location.getCol();
            if(row < 0 || col <0 ){
                throw new InvalidBoardRequestException(String.format("Board size: %s. Request out of bounds [%d,%d]",this.getBoardSize(),
                        row,col));
            }
            if(row>getBoardSize()-1||col>getBoardSize()-1){
                throw new InvalidBoardRequestException(String.format("Board size: %s. Request out of bounds [%d,%d]",this.getBoardSize(),
                        row,col));
            }
        }
        return this.cells[location.getRow()][location.getCol()];
    }
}
