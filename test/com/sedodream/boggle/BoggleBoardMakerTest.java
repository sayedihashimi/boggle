package com.sedodream.boggle;

import org.junit.Ignore;
import org.junit.Test;

public class BoggleBoardMakerTest {
	private String diceFilename = "./files/dice.txt";
	private String boardFilenameLocation ="./files/";
	private String boardFilenamePrefix = "board";
	
	@Test
	public void makeBoard() throws Exception {
//		BoggleBoardMaker.makeRandomBoard(32, diceFilename).save(boardFilenameLocation+boardFilenamePrefix);
		BoggleBoardMaker.makeRandomBoard(64, diceFilename).save(boardFilenameLocation+boardFilenamePrefix);
	}
//	@Test
	public void displayDisplayBoard() throws Exception {
		BoggleBoardMaker b = new BoggleBoardMaker("./files/board8x8.txt",8);
		System.out.println(b);
	}
}
