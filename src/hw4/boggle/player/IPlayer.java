package hw4.boggle.player;

import java.util.List;
import java.util.concurrent.Callable;

public interface IPlayer extends Runnable {

	void setResults(List<String> results);
	List<String> getResults();
	
}
