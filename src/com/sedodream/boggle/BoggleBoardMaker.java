package com.sedodream.boggle;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class BoggleBoardMaker {
	 
	private int N; // number of rows and cols
	private char[] letters; // letters in the board
	
	
	
	public BoggleBoardMaker(char[][] board, int N) {
		this.N = N;
		this.letters = new char[N*N];
		int size = N*N;
		for(int i = 0; i < size; i++){
			letters[i] = board[i/N][i%N];
		}
	}
		
	public BoggleBoardMaker(String[] board, int N) {
		this.N = N;
		this.letters = new char[N*N];
		int size = N*N;
		for(int i = 0; i < size; i++){
			letters[i] = board[i].charAt(0);
		}		
	}
	
	public BoggleBoardMaker(char[] board, int N){
		this.N = N;
		this.letters = new char[N*N];
		int size = N*N;
		for(int i = 0; i < size; i++){
			letters[i] = board[i];
		}		
	}
	
	public BoggleBoardMaker(String board, String regex, int N){
		this.N = N;
		this.letters = new char[N*N];
		Scanner scanner = new Scanner(regex);
		int size = N*N;
		for(int i = 0; i < size; i++){
			letters[i*N+i%N] = scanner.next().charAt(0);
		}
	}
	
	/**
	 * Initializes the BoggleBoard with a file containing exactly
	 * N*N lines.
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public BoggleBoardMaker(String filename, int N) throws FileNotFoundException {
		this.N = N;
		// allocate buffer for the letters		
		this.letters = new char[N*N];
		
		// open file
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));
		
			int i;
			for (i = 0, letters[i] = scanner.nextLine().charAt(0); scanner.hasNextLine(); letters[++i] = scanner.nextLine().charAt(0));
		} finally {
			try {
			scanner.close();
			} catch(Exception e){}
			
		}
	}
	
	private String letterToString(char c) {
		switch(c){
		case 'q':
			return "qu";
		case 'Q':
			return "QU";
		default:
			return ""+c;
		}
	}
	
	public static BoggleBoardMaker makeRandomBoard(int N, String dicefile) throws FileNotFoundException {
		// create the random number generator
		Random r = new Random();
		// open the dice file
		Scanner scanner = new Scanner(new File(dicefile));
		
		// create the list of dice equal to the size of the board
		String s = null;
		List<String> dice = new LinkedList<String>();
		for(s = scanner.nextLine(); scanner.hasNextLine(); s = scanner.nextLine()){
			dice.add(s);
		}
		int size = N*N;
		int addMoreDice = size - dice.size();
		System.out.println("adding "+addMoreDice+" more dice...");
		
		if(addMoreDice > 0){ // add more to equal size of board
			for(int i = 0; i < addMoreDice; i++ ){
                if(BoggleMode.DEBUG_MODE){
                    System.out.println("adding "+(addMoreDice-i)+" more dice...");
                }
				int j = r.nextInt(dice.size());
				dice.add(new String(dice.get(j)));
			}
		}
		else if (addMoreDice < 0){ // remove to equal size of board
			for(int i = 0; i > addMoreDice; i--){
				dice.remove(r.nextInt(dice.size()));
			}
		}
		
		// create list of points 
		List<Point> pts = new LinkedList<Point>();
		for(int i = 0; i < size; i++){
			pts.add(new Point(i/N,i%N));
		}
		
		// create the backing for the board object
		char[] board = new char[N*N];
		
		// create random board
		for(int i = 0; i < size; i++) {
			// select a random dice 
			String die = dice.remove(r.nextInt(dice.size()));
			
			// select random point
			Point pt = pts.remove(r.nextInt(pts.size()));
			
			
			// create sides array
			String[] sides = die.split(" ");
			
			// add a letter to the board
			board[pt.x*N+pt.y] = sides[r.nextInt(sides.length)].charAt(0);
		}
		
		return new BoggleBoardMaker(board, N);
		
	}
	
	public void save(String filename) throws FileNotFoundException {
		filename += ""+N+"x"+N+".txt";
		PrintWriter p = new PrintWriter(new FileOutputStream(new File(filename)));
		for(int i = 0; i < letters.length; i++) {
			p.println(letters[i]== 'Q' || letters[i]=='q'?"QU":""+letters[i]);
		}
		p.close();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String sep = "\t";
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++) {
				sb.append(letterToString(letters[i*N + j]));
				if(j<N-1) {
					sb.append(sep);
				}
			}
			if(i<N-1)
				sb.append("\n");
		}
		return sb.toString();	
	}
}