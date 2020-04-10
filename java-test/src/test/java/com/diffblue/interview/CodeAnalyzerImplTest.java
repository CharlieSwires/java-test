package com.diffblue.interview;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CodeAnalyzerImplTest {
    private CodeAnalyzerImpl cai = null;
    
    @Before
    public void setUp() throws IOException {
        cai = new CodeAnalyzerImpl();
    }
    
    @Test
    public void test1() {
        int i = 0;
        CodeLineImpl cli = new CodeLineImpl("       public static Fred testMethod(Integer i, String hello) throws Exception {", 0);
        i = cai.skipBeginning(cli, i);
        List<String> results = cai.getCommand(cli, i);
        Assert.assertEquals(results.toString(), "[public, Integer i, String hello, {]");
    }
    @Test
    public void test2() {
        int i = 0;
        CodeLineImpl cli = new CodeLineImpl("       if (i % 15 == 0) {", 0);
        i = cai.skipBeginning(cli, i);
        List<String> results = cai.getCommand(cli, i);
        Assert.assertEquals(results.toString(), "[if, i % 15 == 0, {]");
    }    
    @Test
    public void test3() {
        int i = 0;
        CodeLineImpl cli = new CodeLineImpl("       else if (i % 15 == 0) {", 0);
        i = cai.skipBeginning(cli, i);
        List<String> results = cai.getCommand(cli, i);
        Assert.assertEquals(results.toString(), "[else if, i % 15 == 0, {]");
    }
    @Test
    public void test4() {
        int i = 0;
        CodeLineImpl cli = new CodeLineImpl("       return \"fred\";", 0);
        i = cai.skipBeginning(cli, i);
        List<String> results = cai.getCommand(cli, i);
        Assert.assertEquals(results.toString(), "[return, , ]");
    }
    @Test
    public void test5() {
        int i = 0;
        CodeLineImpl cli = new CodeLineImpl("       return fred;", 0);
        i = cai.skipBeginning(cli, i);
        List<String> results = cai.getCommand(cli, i);
        Assert.assertEquals(results.toString(), "[return, , ]");
    }    
    @Test
    public void test6() {
        int i = 0;
        CodeLineImpl cli = new CodeLineImpl("       return 1;", 0);
        i = cai.skipBeginning(cli, i);
        List<String> results = cai.getCommand(cli, i);
        Assert.assertEquals(results.toString(), "[return, , ]");
    }    

}
