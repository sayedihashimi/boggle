/**
 * 
 */
package com.sedodream.boggle.sih.reader;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.Test;

import com.sedodream.boggle.dataStructures.noGeneric.SearchResult;
import com.sedodream.boggle.dataStructures.noGeneric.TreeCharacter;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.DictionaryReader;
import com.sedodream.boggle.sih.TestBase;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class TestDictionaryReader extends TestBase {

    @Test
    public void testReadBufferedStrFile() throws Exception {
        String methodName = getCurrentMethodName();
        System.out.printf("Starting method %s\n", methodName);

        DictionaryReader reader = new DictionaryReader();
        long start = System.nanoTime();

        reader.readBufferedCharFile(wordListComplete);

        long end = System.nanoTime();

        long nano = end - start;
        long milli = (nano) / 1000000;// endTime.getTime() -
                                        // startTime.getTime();

        System.out.printf("\tDone. nano = %d\tmilli = %d\n", nano, milli);
        System.out.printf("Finished method %s\n\n", methodName);
    }

    @Test
    public void testReadBufferedCharFile() throws Exception {
        String methodName = getCurrentMethodName();
        System.out.printf("Starting method %s\n", methodName);

        DictionaryReader reader = new DictionaryReader();

        long start = System.nanoTime();

        reader.readBufferedCharFile(wordListComplete);

        long end = System.nanoTime();

        long nano = end - start;
        long milli = (nano) / 1000000;// endTime.getTime() -
                                        // startTime.getTime();

        System.out.printf("\tDone. nano = %d\tmilli = %d\n", nano, milli);
        System.out.printf("Finished method %s\n\n", methodName);
    }

    @Test
    public void testReadPruningFromFileOriginal() throws Exception {
        String methodName = getCurrentMethodName();
        System.out.printf("Starting method %s\n", methodName);

        DictionaryReader reader = new DictionaryReader();
        long start = System.nanoTime();

        reader.readPruningFromFileOriginal(wordListComplete);

        long end = System.nanoTime();

        long nano = end - start;
        long milli = (nano) / 1000000;// endTime.getTime() -
                                        // startTime.getTime();

        System.out.printf("\tDone. nano = %d\tmilli = %d\n", nano, milli);
        System.out.printf("Finished method %s\n\n", methodName);
    }

    @Test
    public void testReadPruningFromFileChar() throws Exception {
        String methodName = getCurrentMethodName();
        System.out.printf("Starting method %s\n", methodName);

        DictionaryReader reader = new DictionaryReader();
        long start = System.nanoTime();

        reader.readPruningFromFileChar(wordListComplete);

        long end = System.nanoTime();

        long nano = end - start;
        long milli = (nano) / 1000000;// endTime.getTime() -
                                        // startTime.getTime();

        System.out.printf("\tDone. nano = %d\tmilli = %d\n", nano, milli);
        System.out.printf("Finished method %s\n\n", methodName);
    }
    @Test
    public void verifyReadPruningFromFileChar() throws Exception{
        String methodName = getCurrentMethodName();
        System.out.printf("Starting method %s\n", methodName);
        
        DictionaryReader reader = new DictionaryReader();
        TreeCharacter tree = reader.readPruningFromFileChar(wordListComplete);
        
        String filename = wordListComplete;
        
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        System.out.printf("\tReading word file %s\n", filename);

        int currentCount = 0;
        String currentLine = null;
        try {
            File wordFile = new File(filename);

            if ( !wordFile.exists() ) {
                String message = String.format("Cannot file file at [%s]",
                        wordFile.getAbsoluteFile());
                throw new Exception(message);
            }
            
            fileReader = new FileReader(wordFile);
            bufferedReader = new BufferedReader(fileReader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                //resultList.add(currentLine);
                SearchResult result = tree.findWord(currentLine.toCharArray());
                if(!result.getContains()){
                    System.out.printf("\tWord [%s] not found in tree!\n", currentLine);
                }
                Assert.assertEquals(true, result.getContains());
                currentCount++;
            }
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }

        System.out.printf("\tVerified [%d] number words in tree\n", currentCount);
        System.out.printf("Finished method %s\n\n", methodName);
    }
    public String getCurrentMethodName() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos);
        (new Throwable()).printStackTrace(pw);
        pw.flush();
        String stackTrace = baos.toString();
        pw.close();

        StringTokenizer tok = new StringTokenizer(stackTrace, "\n");
        String l = tok.nextToken(); // 'java.lang.Throwable'
        l = tok.nextToken(); // 'at ...getCurrentMethodName'
        l = tok.nextToken(); // 'at ...<caller to getCurrentRoutine>'
        // Parse line 3
        tok = new StringTokenizer(l.trim(), " <(");
        String t = tok.nextToken(); // 'at'
        t = tok.nextToken(); // '...<caller to getCurrentRoutine>'
        return "[" + t + "] ";
    }

    protected List<String> ReadFileIntoList(String filename) throws Exception {
        System.out.format("Starting to read the word list from file:%s\n",
                filename);
        
        List<String>resultList = new ArrayList<String>();
        
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        System.out.printf("Reading word file %s\n", filename);

        WordTreeCharacter wordList = new WordTreeCharacter();

        int currentCount = 0;
        String currentLine = null;
        Date startTime = new Date();
        try {
            File wordFile = new File(filename);

            if ( !wordFile.exists() ) {
                String message = String.format("Cannot file file at [%s]",
                        wordFile.getAbsoluteFile());
                throw new Exception(message);
            }
            
            fileReader = new FileReader(wordFile);
            bufferedReader = new BufferedReader(fileReader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                resultList.add(currentLine);
                currentCount++;
            }
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out
                .printf(
                        "Finished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",
                        currentCount, milliSpent);

        return resultList;
    }

    public static void main(String[] args) throws Exception {
        int numIterations = 10;
        System.out.printf("Doing test for %d iterations", numIterations);

        String wordListComplete = "./files/word.list";

        // TestDictionaryReader tester = new TestDictionaryReader();
        DictionaryReader reader = new DictionaryReader();

        System.out.printf("\tStart test for String based reader\n");
        long start = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            reader.readPruningFromFileOriginal(wordListComplete);
        }

        long nano = System.nanoTime() - start;
        long milli = (nano) / 1000000;
        double averageMilli = (double) milli / (double) numIterations;
        double averageNano = (double) nano / (double) numIterations;

        System.out.printf("\tAverage time : %s\tOverall time %s [ms]\n",
                averageMilli, milli);

        System.out.printf("\n\tStart test for char based reader\n");
        start = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            reader.readPruningFromFileChar(wordListComplete);
        }
        nano = System.nanoTime() - start;
        averageNano = (double) nano / (double) numIterations;
        milli = (nano) / 1000000;
        averageMilli = (double) milli / (double) numIterations;
        System.out.printf("\tAverage time : %s\tOverall time %s [ms]\n",
                averageMilli, milli);
    }
}
