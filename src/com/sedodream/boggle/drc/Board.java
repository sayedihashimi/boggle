package com.sedodream.boggle.drc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board implements IBoard {

	private ICell[][] cells;
	private int N;
	private List<ICell>[] letterList = new ArrayList[26];
	private static final char A = 'a';
	
	public Board(int theN, String[] theBoard) {
		this.N = theN;
		cells = new Cell[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++){
				char c = Character.toLowerCase(theBoard[i*N+j].charAt(0));
				cells[i][j] = new Cell(i,j, c);
				if(letterList[c-A] == null) {
					letterList[c-A]= new ArrayList<ICell>();
				}
				letterList[c-A].add(cells[i][j]);
			}
		}
	}

	public Map<String, ICell> cloneMap(Map<String, ICell> map) {
		// TODO Auto-generated method stub
		return (Map<String, ICell>) ((HashMap<String,ICell>)map).clone();
	}

	public Map<String, ICell> createMap() {
		// TODO Auto-generated method stub
		return new HashMap<String,ICell>();
	}

	public List<ICell> getCells(char letter) {
		List<ICell> list = letterList[Character.toLowerCase(letter)-A];
		if(list == null) {
			list = new LinkedList<ICell>();
		}
		return list;
	}

	public List<ICell> getCells(int tlX, int tlY, int brX, int brY) {
		ArrayList<ICell> list = new ArrayList<ICell>();
		for(int i = tlX; i <= brX; i++ ) {
			for(int j = tlY; j <= brY; j++) {
				list.add(cells[i][j]);
			}
		}
		return list;
	}

	public Map<String, ICell> getUnvisitedNeighbors(ICell theCell, Map<String, ICell> visitedCells) {
		HashMap<String,ICell> map = new HashMap<String,ICell>();
		final int x = theCell.getX(),y = theCell.getY();
		int nX,nY;
		// leftside
		nX = x - 1;
		nY = y - 1;
		// top left
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		//left
		nY = y;
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		// bottom left
		nY = y+ 1;
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		
		// center 
		nX = x;
		// bottomCenter 
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		nY = y-1;
		// top center
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		
		nX = x + 1;
		// right
		nY = y - 1;
		// top right
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		nY = y;
		// right
		checkAndAddCell(x,y,nX,nY,visitedCells, map);
		nY = y + 1;
		// bottom right
		checkAndAddCell(x,y,nX,nY,visitedCells, map);

		return map;
	}
	
	private void checkAndAddCell(int x,int y, int nX, int nY, Map<String,ICell> visitedCells, Map<String,ICell> map){
		if(nX >= 0 && nX < N && nY >=0 && nY < N ){
			if(visitedCells.get(cells[nX][nY]) == null){
				map.put(cells[nX][nY].toString(), cells[nX][nY]);
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String sep = "\t";
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++) {
				sb.append(letterToString(cells[i][j].getLetter()));
				if(j<N-1) {
					sb.append(sep);
				}
			}
			if(i<N-1)
				sb.append("\n");
		}
		return sb.toString();	
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
}
