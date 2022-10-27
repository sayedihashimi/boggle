package hw4.boggle.board;

import java.util.LinkedList;
import java.util.List;

public class BoardUtil {
	public static void checkAndAddNeighbor(List<ICell> list, ICell theCell,int threadId) {
		if(!theCell.isVisited(threadId)){
			list.add(theCell);
		}
	}
	public static List<ICell> makeICellNeighborList(){
		return new LinkedList<ICell>();
	}
	public static List<ICell>[] makeICellListArray(int size) {
		return new LinkedList[size];
	}
}
