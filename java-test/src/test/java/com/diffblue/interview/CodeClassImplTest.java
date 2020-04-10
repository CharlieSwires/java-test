package com.diffblue.interview;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CodeClassImplTest {

    private CodeClassImpl cci = null;
    
    @Before
    public void setUp() throws IOException {
        cci = new CodeClassImpl("C:\\Users\\charl\\Downloads\\java-test\\java-test\\src\\main\\java\\com\\diffblue\\interview\\ClassToTest.java");
    }
    
    @Test
    public void test1() {
        List<CodeMethod> methods = cci.findMethods();
        Assert.assertEquals(methods.toString(), "[CodeMethod [methodName=publicFizzBuzz, returnParameter=String, parameters=[Integer i], start=4, end=14], CodeMethod [methodName=protectedFizzBuzz, returnParameter=String, parameters=[Integer i], start=16, end=26]]");
    }
    @Test
    public void test2() {
        boolean result = cci.getFunctionSignature(0);
        Assert.assertEquals(result, false);
    }
    @Test
    public void test3() {
        boolean result = cci.getFunctionSignature(4);
        Assert.assertEquals(result, true);
    }
    @Test
    public void test4() {
        boolean result = cci.getFunctionSignature(16);
        Assert.assertEquals(result, true);
    }
    @Test
    public void test5() {
        boolean result = cci.getFunctionSignature(28);
        Assert.assertEquals(result, true);
    }
    
    @Test
    public void test6() {
        List<String>[] result = cci.getTokens(4);
        Assert.assertEquals(result[CodeClassImpl.PRE_OPEN_BRACKET].toString(), "[public, String, publicFizzBuzz]");
        Assert.assertEquals(result[CodeClassImpl.BETWEEN_BRACKETS].toString(), "[Integer i]");
        Assert.assertEquals(result[CodeClassImpl.POST_BRACKET].toString(), "[]");
        Assert.assertEquals(result[CodeClassImpl.METHOD_RANGE].toString(), "[4, 14]");
     }
}
