package com.sedodream.boggle.drc.mt;

import java.util.List;

public interface IPlayer extends Runnable{

	void setResults(List<String> results);
	List<String> getResults();
}
