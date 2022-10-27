/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import com.sedodream.boggle.BoggleMode;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class PerfTest {
    private static final String CONSTANT = "char"; //$NON-NLS-1$

    enum TestCase {
        WordTreeCharacters, WordTreeBytes, JavaTree, FastTree
    }

    private TestCase            caseToTest;
    private static List<String> strings;
    private static int          NUM_ITERATIONS = 10;

    public PerfTest() throws Exception {
        caseToTest = TestCase.JavaTree;
        this.setUpBeforeClass();
    }

    public PerfTest(TestCase caseToTest) throws Exception {
        this();
        this.caseToTest = caseToTest;
    }

    public void StartTest() throws Exception {
        System.out.printf("Starting test case %s\n", this.caseToTest);

        if ( caseToTest.equals(TestCase.WordTreeCharacters) ) {
            StartWordTreeCharacters();
        }
        else if ( caseToTest.equals(TestCase.WordTreeBytes) ) {
            StartWordTreeBytes();
        }
        else if ( caseToTest.equals(TestCase.FastTree) ) {
            StartFastTree();
        }
        else if ( caseToTest.equals(TestCase.JavaTree) ) {
            StartJavaTree();
        }
    }

    protected static void setUpBeforeClass() throws Exception {

        System.out.println("Reading word file");
        Date startTime = new Date();
        File wordFile = new File(".\\Files\\word.list");

        if ( !wordFile.exists() ) {
            String message = String.format("Cannot file file at [%s]", wordFile
                    .getAbsoluteFile());
            throw new Exception(message);
        }

        strings = new ArrayList<String>();
        int currentCount = 0;
        BufferedReader d = new BufferedReader(new FileReader(wordFile));
        String currentLine = null;
        while ((currentLine = d.readLine()) != null) {
            strings.add(currentLine);
            currentCount++;
        }

        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();

        System.out.printf("Num words %d\n\tTime to create word list: %s\n",
                currentCount, milliSpent);
    }

    protected void StartWordTreeCharacters() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        Date startTime = new Date();
        System.out.println("\nCreate WordTreeCharacters Started");

        int j = 0;
        int numStrings = strings.size();
        IWordTree tree = new WordTree();
        for (j = 0; j < numStrings; j++) {
            tree.insert(strings.get(j));
        }
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        System.out.printf("\tStarting search, num iterations %d\n",
                NUM_ITERATIONS);
        long totalSearchTime = 0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            // --------------------- SEARCH ----------------------------------
            int numFound = 0;
            // startTime = new Date();
            for (int k = 0; k < numStrings; k++) {
                // look to find the element
                String strToFind = strings.get(k);
                Assert.assertTrue(tree.contains(strToFind));
                numFound++;
            }
            Assert.assertEquals(numStrings, numFound);
            // endTime = new Date();
            // milliSpent = endTime.getTime() - startTime.getTime();
            // System.out.printf("\t\tTotal time to search %s\n", milliSpent);
        }
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);
    }

    protected void StartWordTreeBytes() throws Exception {
        BoggleMode.DEBUG_MODE = false;
        Date startTime = new Date();
        System.out.println("\nCreate WordTreeCharacters Started");

        int j = 0;
        int numStrings = strings.size();
        IWordTree tree = new WordTreeBytes();
        for (j = 0; j < numStrings; j++) {
            tree.insert(strings.get(j));
        }
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\tTime to create %s\n", milliSpent);

        System.out.printf("\tStarting search, num iterations %d\n",
                NUM_ITERATIONS);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            // --------------------- SEARCH ----------------------------------
            int numFound = 0;
            // startTime = new Date();
            for (i = 0; i < numStrings; i++) {
                // look to find the element
                String strToFind = strings.get(i);
                Assert.assertTrue(tree.contains(strToFind));
                numFound++;
            }
            Assert.assertEquals(numStrings, numFound);
            // endTime = new Date();
            // milliSpent = endTime.getTime() - startTime.getTime();
            // System.out.printf("\t\tTotal time to search %s\n", milliSpent);
        }
        endTime = new Date();
        milliSpent = endTime.getTime() - startTime.getTime();
        System.out.printf("\t\tTotal time to search %s\n", milliSpent);
    }

    protected void StartFastTree() {

    }

    protected void StartJavaTree() {

    }

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        TestCase caseToTest = TestCase.WordTreeBytes;
        if(args!=null && args.length>=1 && args[0]!=null){
            if(CONSTANT.equals( args[0] )) {
                caseToTest=TestCase.WordTreeCharacters;
            }
            else if("byte".equals(args[0])) {
                caseToTest=TestCase.WordTreeBytes;
            }
        }
        
        PerfTest tester = new PerfTest(caseToTest);
        tester.StartTest();
    }

}
