package com.sedodream.boggle.sih;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.sedodream.boggle.dataStructures.noGeneric.PruningTreeCharacter;
//import com.sedodream.boggle.dataStructures.noGeneric.PruningWordTreeCharacter;
import com.sedodream.boggle.dataStructures.noGeneric.TreeCharacter;
import com.sedodream.boggle.dataStructures.noGeneric.WordTreeCharacter;
import com.sedodream.boggle.drc.IDictionary;
import com.sedodream.boggle.sih.exceptions.BoardDataException;
import com.sedodream.boggle.sih.exceptions.BoardSizeException;

public abstract class TestBase {
	protected String filename4x4 = "./files/board4x4.txt";

	protected String filename5x5 = "./files/board5x5.txt";

	protected String filename8x8 = "./files/board8x8.txt";

	protected String filename25x25 = "./files/board25x25.txt";

	protected String filename32x32 = "./files/board32x32.txt";

	protected String filename100x100 = "./files/board100x100.txt";

	protected String words5x5 = "./files/word5x5.txt";

	protected String wordsExpected4x4 = "./files/wordsExpected4x4.list";

	protected String filename16x16 = "./files/board16x16.txt";

	protected String wordListComplete = "./files/word.list";

	protected String wordy2kList = "./files/wordy2k.list";

	protected String diceFile = "./files/dice.txt";

	public String knownBoard4x4 = "O,A,A,N,E,T,R,I,I,H,K,R,I,F,L,V";

	protected IBoard BuildBoardFromFile(String filename) throws IOException,
			BoardDataException, BoardSizeException {
		String fileContents = ReadFile(filename);
		return new Board(fileContents);
	}

	protected String ReadFile(String filename) throws IOException {
		if (filename == null) {
			throw new IllegalArgumentException("filename was null");
		}
		System.out.println(String.format("Reading board from file: %s",
				filename));
		File file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException(String.format(
					"File not found: [%s]", file.getAbsolutePath()));
		}

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		System.out.printf("Reading word file %s\n", file.getAbsolutePath());

		int currentCount = 0;
		String currentLine = null;
		Date startTime = new Date();
		StringBuffer sb = new StringBuffer();
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			while ((currentLine = bufferedReader.readLine()) != null) {
				sb.append(currentLine.trim());
			}
		} catch (Exception e) {
			String message = String.format(
					"Unable to read the file. Message:\n%s", e.getMessage());
			System.out.println(message);
		} finally {
			if (fileReader != null)
				fileReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}

		return sb.toString();
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
						"Finished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",
						currentCount, milliSpent);

		return wordList;
	}

	protected TreeCharacter BuildCharDictionaryFromFile(String filename)
			throws Exception {

		System.out.format("Starting to read the word list from file:%s\n",
				filename);

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		System.out.printf("Reading word file %s\n", filename);

		TreeCharacter wordList = new TreeCharacter();

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
				wordList.insert(currentLine.toUpperCase().replace("QU", "~")
						.toCharArray());
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
						"Finished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",
						currentCount, milliSpent);

		return wordList;
	}

	protected PruningTreeCharacter BuildPruningDictionaryFromFile(
			String filename) throws Exception {

		System.out.format("Starting to read the word list from file:%s\n",
				filename);

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		System.out.printf("Reading word file %s\n", filename);

		PruningTreeCharacter wordList = new PruningTreeCharacter();
		wordList.setDoPruning(false);
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
				wordList.insert(currentLine.toUpperCase().replace("QU", "~")
						.toCharArray());
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
		wordList.setDoPruning(true);
		//        wordList.startPruning();

		Date endTime = new Date();
		long milliSpent = endTime.getTime() - startTime.getTime();
		System.out
				.printf(
						"Finished reading word file\n\tNum words %d\n\tTime to create word list: %s\n",
						currentCount, milliSpent);

		return wordList;
	}
}
