package com.diffblue.interview;

public class ClassToTest {
 
    public String publicFizzBuzz(Integer i) {
        if (i % 15 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return ""+i;
        }
    }
    
    protected String protectedFizzBuzz(Integer i) {
        if (i % 15 == 0) 
            return "FizzBuzz";
        else if (i % 3 == 0) 
            return "Fizz";
        else if (i % 5 == 0) 
            return "Buzz";
        else 
            return ""+i;
        
    }
    
    private String privateFizzBuzz(Integer i) {
        if (i % 15 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return ""+i;
        }
    }
}
