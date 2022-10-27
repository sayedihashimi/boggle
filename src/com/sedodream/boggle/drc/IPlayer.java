package com.sedodream.boggle.drc;

import java.util.List;

public interface IPlayer extends Runnable {

	List<String> getResults();
	
	void setResults(List<String> resultList);
	
}
