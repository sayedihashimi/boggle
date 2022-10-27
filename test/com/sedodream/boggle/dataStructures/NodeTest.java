/**
 * 
 */
package com.sedodream.boggle.dataStructures;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class NodeTest {
    //Fields
    private static int NumberElements = 1000000;
    
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }
    
    
	@Test
    public void NodeCreation()
    {
	    ITreeNode<Byte> node = new TreeNode<Byte>();
        Assert.assertNotNull(node);
        
        node.setValue(new Byte((byte)3));
        
        Assert.assertEquals(new Byte((byte)3),node.getValue());
    }
    @Test
    public void List(){
        ITreeNode<Byte> node = new TreeNode<Byte>();
        Assert.assertNotNull(node);
        node.setValue(new Byte(new Byte("1")));
        Assert.assertEquals(new Byte(new Byte("1")),node.getValue());
        
        Assert.assertEquals(0, node.getNextLetterNodes().size());
        
        ITreeNode<Byte>nextNode = new TreeNode<Byte>();
        nextNode.setValue(new Byte("2"));
        node.getNextLetterNodes().add(nextNode);
        
        Assert.assertEquals(1, node.getNextLetterNodes().size());
        Assert.assertEquals(nextNode, node.getNextLetterNodes().get(0));
        Assert.assertEquals(new Byte("2"), node.getNextLetterNodes().get(0).getValue());
        
        
        nextNode = new TreeNode<Byte>();
        nextNode.setValue(new Byte("3"));
        node.getNextLetterNodes().add(nextNode);
        
        Assert.assertEquals(2, node.getNextLetterNodes().size());
        Assert.assertEquals(nextNode, node.getNextLetterNodes().get(1));
        Assert.assertEquals(new Byte("3"), node.getNextLetterNodes().get(1).getValue());
    }
    @Test
    public void LoopTest(){
        
        ITreeNode<Byte> node = new TreeNode<Byte>();
        Assert.assertNotNull(node);
        
        int currentLength = 0;
        
        for(int i = 0;i<100;i++){
            ITreeNode<Byte>nextNode = new TreeNode<Byte>();
            nextNode.setValue(new Byte(""+i));
            node.getNextLetterNodes().add(nextNode);
            currentLength++;
            Assert.assertEquals(currentLength, node.getNextLetterNodes().size());
            Assert.assertEquals(nextNode, node.getNextLetterNodes().get(currentLength-1));
        }
        
        node.setValue(new Byte(new Byte("1")));
        Assert.assertEquals(new Byte(new Byte("1")),node.getValue());
    }
    @Test
    public void CreateLongList(){
        Date startTime = new Date();
        System.out.println("CreateLongList Started");
        
        List<Byte> byteList = new ArrayList<Byte>();
        
        for(int i =0;i<NumberElements;i++){
            byteList.add(new Byte(Integer.toString(i%Byte.MAX_VALUE)));
        }
        
        for(int i=0;i<NumberElements;i++){
            @SuppressWarnings("unused")
            byte byteVal = byteList.get(i).byteValue();
        }
        
        for(int i =0;i<NumberElements;i++){
            byteList.add(new Byte(Integer.toString((i+1)%Byte.MAX_VALUE)));
        }
        Date endTime = new Date();
        long milliSpent = endTime.getTime()-startTime.getTime();
        
        System.out.printf("Num elements %d\n\tMilliseconds spent: %s\n",NumberElements,milliSpent);
    }
    @Test
    public void CreateLongArray(){
        Date startTime = new Date();
        System.out.println("\nCreateLongArray Started");
        
        byte[]byteArray = new byte[NumberElements];

        for(int i =0;i<NumberElements;i++){
            byteArray[i]=new Byte(Integer.toString(i%Byte.MAX_VALUE));
        }
        
        for(int i=0;i<NumberElements;i++){
            @SuppressWarnings("unused")
            byte byteVal = byteArray[i];
        }
        
        for(int i =0;i<NumberElements;i++){
            byteArray[i]=new Byte(Integer.toString((i+1)%Byte.MAX_VALUE));
        }
        
        Date endTime = new Date();
        long milliSpent = endTime.getTime()-startTime.getTime();
        
        System.out.printf("Num elements %d\n\tMilliseconds spent: %s\n",NumberElements,milliSpent);
    }
}
