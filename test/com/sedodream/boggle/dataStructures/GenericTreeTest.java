/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.io.*;
import java.util.*;

import org.apache.commons.collections.FastTreeMap;
import org.junit.*;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacterFast;
//import com.sedodream.boggle.dataStructures.sahni.BinarySearchTree;
import com.sedodream.swtTest.SwtDesignTest;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class GenericTreeTest {
    private static int          NumElements = 300000;
    private static List<String> strings;
    private static List<String> otherStrings;
    private static int          maxLength   = -1;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        String wordFilePath = ".\\Files\\word.list";
        String missFilePath = ".\\Files\\miss.300000.list";

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        System.out.printf("Reading word file %s\n", wordFilePath);

        int currentCount = 0;
        String currentLine = null;
        Date startTime = new Date();
        try {
            File wordFile = new File(wordFilePath);

            if ( !wordFile.exists() ) {
                String message = String.format("Cannot file file at [%s]",
                        wordFile.getAbsoluteFile());
                throw new Exception(message);
            }

            GenericTreeTest.strings = new ArrayList<String>();

            fileReader = new FileReader(wordFile);
            bufferedReader = new BufferedReader(fileReader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                GenericTreeTest.strings.add(currentLine);
                if ( currentLine.length() > maxLength )
                    maxLength = currentLine.length();
                currentCount++;
            }
            // d.close();
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("Num words %d\n\tTime to create word list: %s\n",
                currentCount, milliSpent);

        System.out.printf("Reading miss file %s\n", missFilePath);
        startTime = new Date();
        try {
            GenericTreeTest.otherStrings = new ArrayList<String>();

            File otherFile = new File(missFilePath);
            if ( !otherFile.exists() ) {
                String message = String.format("Cannot file file at [%s]",
                        otherFile.getAbsoluteFile());
                System.out.println(message);
                System.err.println(message);
                throw new Exception(message);
            }
            bufferedReader = new BufferedReader(new FileReader(otherFile));

            currentCount = 0;
            currentLine = null;
            while ((currentLine = bufferedReader.readLine()) != null) {
                GenericTreeTest.otherStrings.add(currentLine);
                currentCount++;
            }
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }
        System.out.printf("Num words %d\n\tTime to create word list: %s\n",
                currentCount, milliSpent);

        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
    }

    @Test
    public void TestCreateJavaTree() {
        Date startTime = new Date();
        System.out.println("\nCreate JavaTree Started");

        int i = 0;
        int numStrings = strings.size();

        TreeMap map = new TreeMap();
        for (i = 0; i < strings.size(); i++) {
            map.put(strings.get(i), strings.get(i));
        }
        Assert.assertEquals(numStrings, map.size());
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();

        System.out.printf("\tTime to create %d\n", milliSpent);

        System.out.println("\tStarting search");
        int numFound = 0;
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(map.containsKey(strToFind));
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %d\n", milliSpent);

        // ------ Start miss test -------------------------------
        System.out.println("\tStarting miss search");
        int numMissStrings = otherStrings.size();
        startTime = new Date();
        numFound = 0;
        int numMissed = 0;
        for (i = 0; i < numMissStrings; i++) {
            // look to find the element
            String strToFind = otherStrings.get(i);
            if ( map.containsKey(strToFind) )
                numFound++;
            else
                numMissed++;
        }
        Assert.assertEquals(numFound + numMissed, numMissStrings);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTime to search %s num missed %d num found %d\n",
                milliSpent, numMissed, numFound);
    }

    @Test
    public void TestCreateFastTreeFast() {
        Date startTime = new Date();
        System.out.println("\nCreate CreateFastTreeFast Started");

        int i = 0;
        int numStrings = strings.size();
        FastTreeMap map = new FastTreeMap();
        map.setFast(false);
        for (i = 0; i < numStrings; i++) {
            map.put(strings.get(i), strings.get(i));
        }
        Assert.assertEquals(numStrings, map.size());
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        map.setFast(true);
        int numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(map.containsKey(strToFind));
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out
                .printf("\t\tTotal time to search [Fast ON] %s\n", milliSpent);

        map.setFast(false);
        numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(map.containsKey(strToFind));
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search [Fast OFF] %s\n",
                milliSpent);

        // ------ Start miss test -------------------------------
        System.out.println("\tStarting miss search");
        int numMissStrings = otherStrings.size();
        startTime = new Date();
        numFound = 0;
        int numMissed = 0;
        for (i = 0; i < numMissStrings; i++) {
            // look to find the element
            String strToFind = otherStrings.get(i);
            if ( map.containsKey(strToFind) )
                numFound++;
            else
                numMissed++;
        }
        Assert.assertEquals(numFound + numMissed, numMissStrings);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTime to search %s num missed %d num found %d",
                milliSpent, numMissed, numFound);
    }

//    @Test
//    @Ignore("Dont even try this!")
//    public void TestSahniBinaryTree() {
//        Date startTime = new Date();
//        System.out.println("\n\nCreate Sahni BST Started");
//
//        BinarySearchTree bst = new BinarySearchTree();
//        int i = 0;
//        int numStrings = strings.size();
//        for (i = 0; i < numStrings; i++) {
//            bst.put(strings.get(i), strings.get(i));
//        }
//        Assert.assertEquals(numStrings, bst.size());
//        Date endTime = new Date();
//        long milliSpent = endTime.getTime() - startTime.getTime();
//        System.out.printf("\tTime to create %s\n", milliSpent);
//
//        int numFound = 0;
//        System.out.println("\tStarting search");
//        startTime = new Date();
//        for (i = 0; i < numStrings; i++) {
//            // look to find the element
//            String strToFind = strings.get(i);
//            Assert.assertTrue(bst.get(strToFind) != null);
//            numFound++;
//        }
//        Assert.assertEquals(numStrings, numFound);
//        endTime = new Date();
//        milliSpent = endTime.getTime() - startTime.getTime();
//        System.out.printf("\t\tTotal time to search %s\n", milliSpent);
//    }

    @Test
    @Ignore("Dont even try this it takes soo long!")
    public void TestArrayList() {
        Date startTime = new Date();
        System.out.println("\nCreate CreateArrayList Started");

        int i = 0;
        int numStrings = strings.size();
        ArrayList<String> strList = new ArrayList<String>();
        for (i = 0; i < numStrings; i++) {
            strList.add(strings.get(i));
        }

        Assert.assertEquals(numStrings, strList.size());
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        int numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(strList.contains(strToFind));
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);

    }

    @Test
    public void TestCreateWordTreeCharacters() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        if ( strings.size() > 500000 || otherStrings.size() > 20000 ) {
            System.out
                    .println("\n\nSkipping WordTreeCharacters beacuse its too slow");
            return;
        }

        Date startTime = new Date();
        System.out.println("\n\nCreate WordTreeCharacters Started");

        int i = 0;
        int numStrings = strings.size();
        IWordTree tree = new WordTree();
        for (i = 0; i < numStrings; i++) {
            tree.insert(strings.get(i));
        }

        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        // --------------------- SEARCH ----------------------------------
        int numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(tree.contains(strToFind));
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);

        // ------ Start miss test -------------------------------
        System.out.println("\tStarting miss search");
        int numMissStrings = otherStrings.size();
        startTime = new Date();
        numFound = 0;
        int numMissed = 0;
        for (i = 0; i < numMissStrings; i++) {
            // look to find the element
            String strToFind = otherStrings.get(i);
            if ( tree.contains(strToFind) )
                numFound++;
            else
                numMissed++;
        }
        Assert.assertEquals(numFound + numMissed, numMissStrings);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTime to search %s num missed %d num found %d",
                milliSpent, numMissed, numFound);
    }

    @Test
    public void TestCreateNoGenWordTreeCharacters() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        Date startTime = new Date();
        System.out.println("\n\nCreate NoGenWordTreeCharacters Started");

        int i = 0;
        int numStrings = strings.size();
        WordTreeCharacter tree = new WordTreeCharacter();
        for (i = 0; i < numStrings; i++) {
            tree.insert(strings.get(i));
        }

        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        // --------------------- SEARCH ----------------------------------
        int numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(tree.contains(strToFind).getContains());
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);

        // ------ Start miss test -------------------------------
        System.out.println("\tStarting miss search");
        int numMissStrings = otherStrings.size();
        startTime = new Date();
        numFound = 0;
        int numMissed = 0;
        for (i = 0; i < numMissStrings; i++) {
            // look to find the element
            String strToFind = otherStrings.get(i);
            if ( tree.contains(strToFind).getContains() )
                numFound++;
            else
                numMissed++;
        }
        Assert.assertEquals(numFound + numMissed, numMissStrings);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTime to search %s num missed %d num found %d",
                milliSpent, numMissed, numFound);

    }

    @Test
    public void TestCreateNoGenWordTreeCharactersFast() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        Date startTime = new Date();
        System.out.println("\n\nCreate NoGenWordTreeCharactersFast Started");

        int i = 0;
        int numStrings = strings.size();
        WordTreeCharacterFast tree = new WordTreeCharacterFast();
        for (i = 0; i < numStrings; i++) {
            tree.insert(strings.get(i));
        }

        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        // --------------------- SEARCH ----------------------------------
        int numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(tree.contains(strToFind).getContains());
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);

        // ------ Start miss test -------------------------------
        System.out.println("\tStarting miss search");
        int numMissStrings = otherStrings.size();
        startTime = new Date();
        numFound = 0;
        int numMissed = 0;
        for (i = 0; i < numMissStrings; i++) {
            // look to find the element
            String strToFind = otherStrings.get(i);
            if ( tree.contains(strToFind).getContains() )
                numFound++;
            else
                numMissed++;
        }
        Assert.assertEquals(numFound + numMissed, numMissStrings);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTime to search %s num missed %d num found %d",
                milliSpent, numMissed, numFound);
    }

    @Test
    public void TestCreateWordTreeBytes() throws Exception {
        BoggleMode.DEBUG_MODE = false;

        if ( strings.size() > 500000 || otherStrings.size() > 20000 ) {
            System.out
                    .println("\n\nSkipping WordTreeBytes beacuse its too slow");
            return;
        }

        Date startTime = new Date();
        System.out.println("\n\nCreate WordTreeBytes Started");

        int i = 0;
        int numStrings = strings.size();
        IWordTree tree = new WordTreeBytes();
        for (i = 0; i < numStrings; i++) {
            tree.insert(strings.get(i));
        }

        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        // --------------------- SEARCH ----------------------------------
        int numFound = 0;
        System.out.println("\tStarting search");
        startTime = new Date();
        for (i = 0; i < numStrings; i++) {
            // look to find the element
            String strToFind = strings.get(i);
            Assert.assertTrue(tree.contains(strToFind));
            numFound++;
        }
        Assert.assertEquals(numStrings, numFound);
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);
    }

    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            GenericTreeTest gtt = new GenericTreeTest();
            GenericTreeTest.setUpBeforeClass();
            gtt.TestCreateNoGenWordTreeCharactersFast();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}