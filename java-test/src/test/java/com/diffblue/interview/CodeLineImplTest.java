package com.diffblue.interview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CodeLineImplTest {

    private CodeLineImpl cli = null;
    
    @Before
    public void setUp() {
        cli = new CodeLineImpl("hello", 23);
    }
    
    @Test
    public void test1() {
        CodeLineImpl testValue = new CodeLineImpl("hello", 23);
        Assert.assertEquals(testValue.equals(cli),true);
    }
    @Test
    public void test2() {
        CodeLineImpl testValue = new CodeLineImpl("hello", 24);
        Assert.assertEquals(!testValue.equals(cli),true);
    }
    @Test
    public void test3() {
        CodeLineImpl testValue = new CodeLineImpl("hello!", 23);
        Assert.assertEquals(!testValue.equals(cli),true);
    }
}
