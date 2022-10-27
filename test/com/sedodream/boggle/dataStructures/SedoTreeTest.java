/**
 * 
 */
package com.sedodream.boggle.dataStructures;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class SedoTreeTest {

//    /**
//     * @throws java.lang.Exception
//     */
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//    }
//
//    /**
//     * @throws java.lang.Exception
//     */
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//    }
//
//    /**
//     * @throws java.lang.Exception
//     */
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    /**
//     * @throws java.lang.Exception
//     */
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void TestOneLevel() throws Exception{
        ITree<Integer> tree = new Tree<Integer>();
        
        Assert.assertNotNull(tree);
        Assert.assertNotNull(tree.getRootNode());
        
        int numToCreate = 100;
        for(int i = 0;i<numToCreate;i++){
            List<Integer>listOfOne = new ArrayList<Integer>();
            listOfOne.add(i);
            tree.insertAfter(listOfOne);
            Assert.assertEquals(i+1, tree.getRootNode().getNextLetterNodes().size());    
        }
        
        //now try to insert duplicates make sure it doesnt change
        for(int i = 0;i<numToCreate;i++){
            List<Integer>listOfOne = new ArrayList<Integer>();
            listOfOne.add(i);
            tree.insertAfter(listOfOne);
            Assert.assertEquals(numToCreate, tree.getRootNode().getNextLetterNodes().size());    
        }
    }
    
    @Test
    public void TestTwoLevel() throws Exception{
        ITree<Integer> tree = new Tree<Integer>();
        
        Assert.assertNotNull(tree);
        Assert.assertNotNull(tree.getRootNode());
        
        List<Integer> nodePath = new ArrayList<Integer>();
        nodePath.add(1);
        
        tree.insertAfter(nodePath);
        ITreeNode<Integer> foundNode = tree.find(nodePath);
        Assert.assertNotNull(foundNode);
        Assert.assertEquals(1, foundNode.getValue());

        nodePath.add(2);
        tree.insertAfter(nodePath);
        foundNode = tree.find(nodePath);
        Assert.assertNotNull(foundNode);
        Assert.assertEquals(2, foundNode.getValue());
    }
    
    
    
    
    
}
