package com.diffblue.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

public class CodeAnalyzerImpl implements CodeAnalyzer{

    @Override
    public Set<CodeLine> runTest(CodeTest test) {
        CodeTestImpl testTemp = (CodeTestImpl)test;

        return simpleRunner(testTemp);
    }

    @Override
    public Set<CodeLine> runTestSuite(Set<CodeTest> tests) {
        Set<CodeLine> covered = new HashSet<CodeLine>();
        for (CodeTest test : tests) {
            CodeTestImpl testTemp = (CodeTestImpl)test;

            covered.addAll(simpleRunner(testTemp));
        }
        return covered;
    }

    private static final int COMMAND = 0;
    private static final int EXPRESSION = 1;
    private static final int FOLLOWING_BRACKET = 2;

    protected Set<CodeLine> simpleRunner(CodeTestImpl test){
        List<CodeLine> lines = test.getCode();
        int index = test.getMethodUnderTest().getStart();
        Set<CodeLine> covered = new HashSet<CodeLine>();
        covered.add(lines.get(index));
        index++;
        boolean done = false;
        while(!done && index <= test.getMethodUnderTest().getEnd()) {
            int i = 0;
            Operator eq = new Operator("==", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

                @Override
                public double apply(double[] values) {
                    if (values[0] == values[1]) {
                        return 1d;
                    } else {
                        return 0d;
                    }
                }
            };
            Expression e = null;
            double result = 0d;
            String variables = null;
            i = skipBeginning(lines.get(index),i);
            List<String> command = getCommand(lines.get(index),i);
            String word = command.get(COMMAND);
            String expression = command.get(EXPRESSION);
            switch (word) {
            case "if":
                variables = test.getMethodUnderTest().getParameters()
                .replace("[","").replace("]","").split(",")[0].split(" ")[1];

                e = new ExpressionBuilder(expression).operator(eq)
                        .variables(variables)
                        .build()
                        .setVariable(variables, 
                                Double.parseDouble((""+(test.getInputs().get(0))).replace("[","").replace("]","")));
                result = e.evaluate();
                covered.add(lines.get(index));
                if (result == 1d) {
                    index++;
                    i = 0;
                    covered.add(lines.get(index));
                } else if ("{".equals(command.get(FOLLOWING_BRACKET))) {
                    do {
                        index++;
                        i=0;
                        i = skipBeginning(lines.get(index),i);
                    } while (index <= test.getMethodUnderTest().getEnd() && lines.get(index).getContents().charAt(i) != '}');
                    i++;
                } else {
                  index += 2;  
                }
                break;
            case "else":
                covered.add(lines.get(index));
                index++;
                i=0;
                covered.add(lines.get(index));
                index++;
                break;
            case "else if":
                variables = test.getMethodUnderTest().getParameters()
                .replace("[","").replace("]","").split(",")[0].split(" ")[1];

                e = new ExpressionBuilder(expression).operator(eq)
                        .variables(variables)
                        .build()
                        .setVariable(variables, 
                                Double.parseDouble((""+(test.getInputs().get(0))).replace("[","").replace("]","")));
                result = e.evaluate();
                covered.add(lines.get(index));
                if (result == 1d) {
                    index++;
                    i = 0;
                    covered.add(lines.get(index));
                } else if ("{".equals(command.get(FOLLOWING_BRACKET))) {
                    do {
                        index++;
                        i=0;
                        i = skipBeginning(lines.get(index),i);
                    } while (index <= test.getMethodUnderTest().getEnd() && lines.get(index).getContents().charAt(i) != '}');
                    i++;
                } else {
                    index += 2;  
                }
                break;
            case "for":
                break;
            case "while":
                break;
            case "break":
                break;
            case "do":
                break;
            case "return":
                covered.add(lines.get(index));
                done = true;
                break;
            default:
                covered.add(lines.get(index));
                index++;
                break;
            }
        }
        return covered;

    }

    protected List<String> getCommand(CodeLine codeLine, int i) {
        List<String> listArray = new ArrayList<String>();
        String line = codeLine.getContents();
        String preOpenBracket = "";
        String betweenBrackets = "";
        String postBrackets = "";
        for (; i < line.length(); i++) {
            Character c = line.charAt(i);
            if (c >= 'a' && c <= 'z') {
                break;
            }
        }        //start tokens
        for (; i < line.length(); i++) {
            Character c = line.charAt(i);
            if (!line.substring(i).startsWith("else if")) {
                if (!(c >= 'a' && c <= 'z')) {
                    int temp = line.indexOf('(');
                    if (temp != -1) i = temp;
                    i++;
                    break;
                }
                preOpenBracket += c;
            } else {
                i = line.indexOf('(');
                i++;
                preOpenBracket = "else if";
                break;
            }
        }
        listArray.add(preOpenBracket);
        //between brackets tokens
        if (line.charAt(i-1) == '(') {
            for (; i < line.length(); i++) {
                if ((line.charAt(i) == ')')) {
                    i++;
                    break;
                }
                Character c = line.charAt(i);
                betweenBrackets += c;
            }
        }
        listArray.add(betweenBrackets);
        //post tokens
        for (; i < line.length(); i++) {
            Character c = line.charAt(i);
            if (c == '{') {
                postBrackets += c;
                break;
            }
        }
        listArray.add(postBrackets);

        return listArray;
    }

    protected int skipBeginning(CodeLine codeLine, int i) {
        while(i < codeLine.getContents().length() && (codeLine.getContents().charAt(i) == '\t' || codeLine.getContents().charAt(i) == ' '))i++;
        return i;
    }

    @Override
    public Set<CodeTest> uniqueTests(Set<CodeTest> tests) {
        Set<CodeLine> covered = new HashSet<CodeLine>();
        Set<CodeTest> unique = new HashSet<CodeTest>();
        int runningSize = 0;
        for (CodeTest test : tests) {
            CodeTestImpl testTemp = (CodeTestImpl)test;

            covered.addAll(simpleRunner(testTemp));
            if (covered.size() > runningSize) {
                unique.add(test);
            }
            runningSize = covered.size();
        }
        return unique;    }
}
