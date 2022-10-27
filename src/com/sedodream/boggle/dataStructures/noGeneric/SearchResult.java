/**
 * 
 */
package com.sedodream.boggle.dataStructures.noGeneric;

/**
 * Simple class that contains the result of the search.
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class SearchResult {
    /**
     * Fields
     */
    private boolean contains;
    private boolean hasChildren;
    
    /**
     * @param contains
     */
//    public SearchResult(boolean contains) {
//        super();
//        this.contains = contains;
//    }
    /**
     * @param contains
     * @param hasChildren
     */
    public SearchResult(boolean contains, boolean hasChildren) {
        this.contains = contains;
        this.hasChildren = hasChildren;
    }
    /**
     * @return the contains
     */
    public boolean getContains() {
        return contains;
    }
    /**
     * @param contains the contains to set
     */
    public void setContains(boolean contains) {
        this.contains = contains;
    }
    /**
     * @return the hasChildren
     */
    public boolean getHasChildren() {
        return hasChildren;
    }
    /**
     * @param hasChildren the hasChildren to set
     */
    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public String toString() {
        String str = String.format("[contains=%s,hasChildren=%s]", contains,hasChildren);
        return str;
    }
}
