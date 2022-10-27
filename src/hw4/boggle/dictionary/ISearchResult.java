package hw4.boggle.dictionary;

public interface ISearchResult {
	SearchResult CONTAINS_FALSE__HASCHILDREN_FALSE = new SearchResult( false, false);

	SearchResult CONTAINS_FALSE__HASCHILDREN_TRUE = new SearchResult( false, true);

	SearchResult CONTAINS_TRUE__HASCHILDREN_FALSE = new SearchResult( true, false);

	SearchResult CONTAINS_TRUE__HASCHILDREN_TRUE = new SearchResult( true, true);
	
	boolean getContains();
	boolean getHasChildren();
}
