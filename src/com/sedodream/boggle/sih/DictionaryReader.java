/**
 * 
 */
package com.sedodream.boggle.sih;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.sedodream.boggle.dataStructures.noGeneric.PruningTreeCharacter;
import com.sedodream.boggle.dataStructures.noGeneric.TreeCharacter;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class DictionaryReader {
    public void readBufferedCharFile(String filename) throws Exception {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        int currentCount = 0;

        try {
            File wordFile = new File(filename);

            if ( !wordFile.exists() ) {
                String message = String.format("Cannot file file at [%s]",
                        wordFile.getAbsoluteFile());
                throw new Exception(message);
            }

            fileReader = new FileReader(wordFile);
            bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.read() > 0) {
                currentCount++;
            }
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }
    }

    public void readBufferedStrFile(String filename) throws Exception {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

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
                // wordList.insert(currentLine.toCharArray());
                currentCount++;
            }
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }
    }

    public TreeCharacter readPruningFromFileChar(String filename)
            throws Exception {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        PruningTreeCharacter wordList = new PruningTreeCharacter();
        wordList.setDoPruning(false);
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
            // bufferedReader.read
            char[] currentWord = new char[50];
            boolean done = false;
            boolean stillReadingLine = true;
            // read one word at at time
            int currentChar;

            int endOfLineChar = (int) '\n';
            int currentPosition = 0;
            
//            int maxNumReads = 270000*45;
//            int currentReadNum = 0;
            do {
//                if(currentReadNum>maxNumReads){
//                    System.out.printf("\tToo many reads performed stopping!\n");
//                    break;
//                }
                do {
//                    if(currentReadNum++>maxNumReads){
//                        System.out.printf("\tToo many reads performed stopping!\n");
//                        break;
//                    }
                    currentChar = bufferedReader.read();
                    if ( currentChar < 0 ) {
                        done = true;
                        stillReadingLine = false;
                        //System.out.printf("Were done!\n");
                        // todo: see if there is still a word in the buffer
                    }
                    else if ( currentChar == endOfLineChar ) {
                        stillReadingLine = false;

                        char[] theWord = new char[currentPosition]; //make sure to not include the newline in the word
                        System.arraycopy(currentWord, 0, theWord, 0,
                                currentPosition);
                        String word = new String(theWord);
//                        System.out.printf("\tread word: %s\n",word);
                        wordList.insert(theWord);

                        currentPosition = 0;
                    }
                    else {
                        currentWord[currentPosition] = (char) currentChar;
                        currentPosition++;
                    }
                } while (stillReadingLine);
            } while (!done);

//            while ((currentLine = bufferedReader.readLine()) != null) {
//                wordList.insert(currentLine.toCharArray());
//                currentCount++;
//            }
        }
        finally {
            if ( fileReader != null )
                fileReader.close();
            if ( bufferedReader != null )
                bufferedReader.close();
        }
        wordList.setDoPruning(true);

        return wordList;
    }

//    public TreeCharacter readPruningFromFileString(String filename)
//            throws Exception {
//        Date startTime = new Date();
//
//        BufferedReader bufferedReader = null;
//        FileReader fileReader = null;
//
//        PruningTreeCharacter wordList = new PruningTreeCharacter();
//        wordList.setDoPruning(false);
//        int currentCount = 0;
//        String currentLine = null;
//
//        try {
//            File wordFile = new File(filename);
//
//            if ( !wordFile.exists() ) {
//                String message = String.format("Cannot file file at [%s]",
//                        wordFile.getAbsoluteFile());
//                throw new Exception(message);
//            }
//
//            fileReader = new FileReader(wordFile);
//            bufferedReader = new BufferedReader(fileReader);
//            // bufferedReader.read
//            while ((currentLine = bufferedReader.readLine()) != null) {
//                wordList.insert(currentLine.toCharArray());
//                currentCount++;
//            }
//        }
//        finally {
//            if ( fileReader != null )
//                fileReader.close();
//            if ( bufferedReader != null )
//                bufferedReader.close();
//        }
//        wordList.setDoPruning(true);
//
//        return wordList;
//    }

    public TreeCharacter readPruningFromFileOriginal(String filename)
            throws Exception {
        // System.out.format("Starting to read the word list from file:%s\n",
        // filename);
        Date startTime = new Date();

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        // System.out.printf("Reading word file %s\n", filename);

        PruningTreeCharacter wordList = new PruningTreeCharacter();
        wordList.setDoPruning(false);
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
                wordList.insert(currentLine.toCharArray());
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
        wordList.setDoPruning(true);

        // Date endTime = new Date();
        // long milliSpent = endTime.getTime() - startTime.getTime();
        // System.out
        // .printf(
        // "Finished reading word file\n\tNum words %d\n\tTime to create word
        // list: %s\n",
        // currentCount, milliSpent);

        return wordList;
    }
}
