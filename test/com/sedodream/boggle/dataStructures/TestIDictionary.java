package com.sedodream.boggle.dataStructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sedodream.boggle.BoggleMode;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;
import com.sedodream.boggle.drc.IDictionary;

public class TestIDictionary {
	private static final String DICTIONARY = "./files/word.list";

	@Test
	/**
	 * this is the offending word
	 */
	public void testQUI() throws Exception {
		String OFFENDER = "qui";
		IDictionary dictionary = BuildDictionaryFromFile(DICTIONARY);
		boolean dicResult = dictionary.findWord(OFFENDER).getContains();
		System.out.println("'"+OFFENDER+"'"+" in dictionary? "+dicResult);
		
		List<String> wordList = readWordsFromFile(DICTIONARY);
		boolean listResult = wordList.contains(OFFENDER);
		System.out.println("'"+OFFENDER+"'"+ " in wordlist? " + listResult);
		
		Assert.assertEquals(dicResult, listResult);
	}
	
	@Test
	/**
	 * make sure all the words in the wordList are findable in the dictionary
	 * 
	 */
	public void testIDictionary() throws Exception {
        System.out.println("Starting test case testIDictionary");
        
		List<String> wordList = readWordsFromFile(DICTIONARY);
		IDictionary dictionary = BuildDictionaryFromFile(DICTIONARY);

        
        //TODO: REMOVE
		for(String word: wordList) {
            if(word.startsWith("q")){
                
                @SuppressWarnings("unused")
                int debug = 3;
            }
			boolean found = dictionary.findWord(word.toUpperCase()).getContains();
			Assert.assertEquals(word+" in wordList.txt but not in dictionary",true,found);
		}
        if(wordList.size()!=dictionary.size()){
            System.out.printf("\tWord count mismatch. Word list: %d Dictionary: %d\n",
                    wordList.size(),dictionary.size());
        }
        Assert.assertEquals(wordList.size(), dictionary.size());
	}
	private static List<String> readWordsFromFile(String dictionaryFileName)
			throws Exception {
        
        Date startTime = new Date();
		List<String> words = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(
				dictionaryFileName));
		String s = null;
        int currentCount=0;
		while ((s = reader.readLine()) != null){
			words.add(s);
            currentCount++;
        }
		reader.close();
        
        Date endTime = new Date();
        long milliSpent = endTime.getTime() - startTime.getTime();
        System.out
                .printf(
                        "\tFinished reading wordlist\n\tNum words %d\n\tTime to create wordlist: %s\n",
                        currentCount, milliSpent);

        
		return words;
	}
	
    
    
    
	protected IDictionary BuildDictionaryFromFile(String filename)
			throws Exception {

		System.out.format("Starting to read the word list from file:%s\n",
				filename);

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		System.out.printf("Reading word file %s\n", filename);

		WordTreeCharacter wordList = new WordTreeCharacter();

		int currentCount = 0;
		String currentLine = null;
		Date startTime = new Date();
		try {
			File wordFile = new File(filename);

			if (!wordFile.exists()) {
				String message = String.format("Cannot file file at [%s]",
						wordFile.getAbsoluteFile());
				throw new Exception(message);
			}

			// GenericTreeTest.strings = new ArrayList<String>();

			fileReader = new FileReader(wordFile);
			bufferedReader = new BufferedReader(fileReader);

			while ((currentLine = bufferedReader.readLine()) != null) {
				// TODO: Not sure if we should be upper casing here
				wordList.insert(currentLine.toUpperCase());
				// GenericTreeTest.strings.add(currentLine);
				// if ( currentLine.length() > maxLength )
				// maxLength = currentLine.length();
				currentCount++;
			}
			// d.close();
		} finally {
			if (fileReader != null)
				fileReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}
		Date endTime = new Date();
		long milliSpent = endTime.getTime() - startTime.getTime();
		System.out
				.printf(
						"\tFinished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",
						currentCount, milliSpent);

		return wordList;
	}

}
