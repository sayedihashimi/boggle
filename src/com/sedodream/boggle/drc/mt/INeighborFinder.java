package com.sedodream.boggle.drc.mt;

import java.rmi.server.UID;
import java.util.List;

interface INeighborFinder {
	List<ICell> getUnvisitedNeighbors(int threadId, int x, int y);
	List<ICell> getUnvisitedNeighbors(long threadId, int x, int y);
	List<ICell> getUnvisitedNeighbors(UID threadId, int x, int y);
}
