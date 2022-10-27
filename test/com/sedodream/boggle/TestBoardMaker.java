/**
 * 
 */
package com.sedodream.boggle;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.sedodream.boggle.sih.TestBase;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestBoardMaker extends TestBase {
    @Test
    public void Create25x25() throws FileNotFoundException{
        int size = 25;
        BoggleBoardMaker maker=BoggleBoardMaker.makeRandomBoard(size, diceFile);
        
        String fileToSave = ".\\Files\\file.generated.";
        String actualFileName = String.format("%s%sx%s.txt", fileToSave,size,size);
        
        File theFile = new File(actualFileName);
        if(theFile.exists()){
            System.out.printf("Deleting existig file at %s", theFile.getAbsolutePath());
            theFile.delete();
        }
        Assert.assertFalse(theFile.exists());
        
        maker.save(fileToSave);
        
        Assert.assertTrue(theFile.exists());
    }
    @Test
    public void Create100x100() throws FileNotFoundException{
        int size = 100;
        BoggleBoardMaker maker=BoggleBoardMaker.makeRandomBoard(size, diceFile);
        
        String fileToSave = ".\\Files\\file.generated.";
        String actualFileName = String.format("%s%sx%s.txt", fileToSave,size,size);
        
        File theFile = new File(actualFileName);
        if(theFile.exists()){
            System.out.printf("Deleting existig file at %s", theFile.getAbsolutePath());
            theFile.delete();
        }
        System.out.printf("Filename: %s", theFile.getAbsolutePath());
        Assert.assertFalse(theFile.exists());
        
        maker.save(fileToSave);
        
        Assert.assertTrue(theFile.exists());
    }
    @Test
    @Ignore("Takes too long")
    public void Create1000x1000() throws FileNotFoundException{
        int size = 1000;
        BoggleMode.DEBUG_MODE=false;
        BoggleBoardMaker maker=BoggleBoardMaker.makeRandomBoard(size, diceFile);
        
        String fileToSave = ".\\Files\\file.generated.";
        String actualFileName = String.format("%s%sx%s.txt", fileToSave,size,size);
        
        File theFile = new File(actualFileName);
        if(theFile.exists()){
            System.out.printf("Deleting existig file at %s", theFile.getAbsolutePath());
            theFile.delete();
        }
        System.out.printf("Filename: %s", theFile.getAbsolutePath());
        Assert.assertFalse(theFile.exists());
        
        maker.save(fileToSave);
        
        Assert.assertTrue(theFile.exists());
    }
}
