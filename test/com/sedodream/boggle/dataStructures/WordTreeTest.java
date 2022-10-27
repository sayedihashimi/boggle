package com.sedodream.boggle.dataStructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordTreeTest {
    
    @Test
    public void TestInert() throws Exception{
        IWordTree tree = new WordTree();
        
        Assert.assertNotNull(tree);
        tree.insert("a");
        
        Assert.assertTrue(tree.contains("a"));
        
        tree.insert("bc");
        Assert.assertTrue(tree.contains("bc"));
        
        Assert.assertFalse(tree.contains("not"));
        
        
        
    }
}
