/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.list.NodeCachingLinkedList;
import org.apache.commons.collections.list.SynchronizedList;
import org.apache.commons.collections.list.TreeList;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class CommonsSynchronizedList extends SynchronizedList implements ISynchronizedList {

    public CommonsSynchronizedList(){
        super(new ArrayList());
    }
    
    /**
     * @param list
     */
    protected CommonsSynchronizedList(List list) {
        super(list);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 2174224493772941648L;

}
