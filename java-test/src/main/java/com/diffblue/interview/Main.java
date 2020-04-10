package com.diffblue.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    
    public static void main(String[] args) throws IOException {
        CodeClassImpl cci =  new CodeClassImpl(args[0]);
        Set<CodeTest> tests = new HashSet<CodeTest>();
 
        CodeTestImpl cti = new CodeTestImpl();
        cti.setName("test1");
        cti.setCode(cci.getLinesOfCode());
        List<Class> type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        List<Object> value = new ArrayList<Object>();
        value.add(15);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(0));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("FizzBuzz");
        cti.setOutput(value.get(0));
        CodeAnalyzerImpl cai = new CodeAnalyzerImpl();
        tests.add(cti);             
        
        cti = new CodeTestImpl();
        cti.setName("test2");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(3);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(0));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("Fizz");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);
        
        cti = new CodeTestImpl();
        cti.setName("test3");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(5);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(0));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("Buzz");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);
        
        cti = new CodeTestImpl();
        cti.setName("test4");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(1);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(0));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("1");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);
        
        cti = new CodeTestImpl();
        cti.setName("test5");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(15);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(1));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("FizzBuzz");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);             
        
        cti = new CodeTestImpl();
        cti.setName("test6");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(3);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(1));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("Fizz");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);
        
        cti = new CodeTestImpl();
        cti.setName("test7");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(5);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(1));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("Buzz");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);
        
        cti = new CodeTestImpl();
        cti.setName("test8");
        cti.setCode(cci.getLinesOfCode());
        type = new ArrayList<Class>();
        type.add(Integer.class);
        cti.setInputTypes(type);
        value = new ArrayList<Object>();
        value.add(1);
        cti.setInputs(value);
        cti.setMethodUnderTest(cci.findMethods().get(1));
        type = new ArrayList<Class>();
        type.add(String.class);
        cti.setOutputType(type.get(0));
        value = new ArrayList<Object>();
        value.add("1");
        cti.setOutput(value.get(0));
        cai = new CodeAnalyzerImpl();
        tests.add(cti);
        
        Set<CodeLine> coverage = cai.runTestSuite(tests);
        
        //System.out.println(cci.toString());
        //System.out.println(cci.findMethods());
        for(CodeLine cl : cci.getLinesOfCode()) {
            
            System.out.println(""+(coverage.contains(cl)? "**":"--")+cl.getLineNumber()+" - "+cl.getContents());
        }
   }

}
