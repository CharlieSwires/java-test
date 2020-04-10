package com.diffblue.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CodeClassImpl implements CodeClass{

    private List<CodeLine> lines = null;
    private File file = null;

    @Override
    public List<CodeLine> getLinesOfCode() {
        // TODO Auto-generated method stub
        return getLines();
    }

    @Override
    public File getFile() {
        // TODO Auto-generated method stub
        return getFile();
    }


    public List<CodeLine> getLines() {
        return this.lines;
    }

    public void setLines(List<CodeLine> lines) {
        this.lines = lines;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public CodeClassImpl(String fnam) throws IOException {
        this.file = new File(fnam);
        this.lines = new ArrayList<CodeLine>();
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        String line;
        int lineNo = 0;
        while ((line = br.readLine())!=null) {
            this.lines.add((CodeLine)new CodeLineImpl(line,lineNo++));
        }

    }

    @Override
    public String toString() {
        String result = "";
        for(CodeLine cl : getLines()) {
            result += "" + cl.getLineNumber() + " -- " + cl.getContents() + "\n";
        }
        return result;

    }
    

    public static final int PRE_OPEN_BRACKET = 0;
    public static final int BETWEEN_BRACKETS = 1;
    public static final int POST_BRACKET = 2;
    public static final int METHOD_RANGE = 3;
    public static final int START = 0;
    public static final int END = 1;

    public List<CodeMethod> findMethods() {
        List<CodeMethod> results = new ArrayList<CodeMethod>();;
        for(CodeLine cl : getLines()) {
            List<String>[] tokenList = getTokens(cl.getLineNumber());
            Set<String> set = new HashSet<String>();
            set.addAll(tokenList[PRE_OPEN_BRACKET]);

            if ((set.contains("void") || set.contains("public") || set.contains("protected") || set.contains("static")) 
                    && getFunctionSignature(cl.getLineNumber())) {
                String methodName = tokenList[PRE_OPEN_BRACKET].get(tokenList[PRE_OPEN_BRACKET].size()-1);
                String returnParameter = tokenList[PRE_OPEN_BRACKET].get(tokenList[PRE_OPEN_BRACKET].size()-2);
                String parameters = tokenList[BETWEEN_BRACKETS].toString();
                int start = Integer.parseInt(tokenList[METHOD_RANGE].get(START));
                int end = Integer.parseInt(tokenList[METHOD_RANGE].get(END));
                CodeMethod method = new CodeMethod(methodName, returnParameter, parameters, start,
                        end);
                results.add(method);

            }
        }
        return results;

    }

    public List<String>[] getTokens(int lineNo) {
        String preOpenBracket = "";
        String betweenBrackets = "";
        String postBrackets = "";
        @SuppressWarnings("unchecked")
        List<String>[] listArray = new List[4];
        listArray[PRE_OPEN_BRACKET] = new ArrayList<String>();
        listArray[BETWEEN_BRACKETS] = new ArrayList<String>();
        listArray[POST_BRACKET] = new ArrayList<String>();
        listArray[METHOD_RANGE] = new ArrayList<String>();

        String line = this.getLinesOfCode().get(lineNo).getContents();
        //line start
        int i = 0;
        for (; i < line.length(); i++) {
            if ((line.charAt(i) >= 'a' && line.charAt(i) <= 'z') ||
                    (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z'))
                break;
        }
        //start tokens
        for (; i < line.length(); i++) {
            if ((line.charAt(i) == '(')) {
                i++;
                break;
            }
            Character c = line.charAt(i);
            if (c == '\t' || c == ' ')
                c=',';
            preOpenBracket += c;
        }
        listArray[PRE_OPEN_BRACKET] = Arrays.asList(preOpenBracket.split(","));
        //between brackets tokens
        for (; i < line.length(); i++) {
            if ((line.charAt(i) == ')')) {
                i++;
                break;
            }
            Character c = line.charAt(i);
            betweenBrackets += c;
        }
        listArray[BETWEEN_BRACKETS] = Arrays.asList(betweenBrackets.split(","));
        //post tokens
        for (; i < line.length(); i++) {
            if ((line.charAt(i) == '{'))
                break;
            Character c = line.charAt(i);
            if (c == '\t' || c == ' ')
                c=',';
            postBrackets += c;
        }
        listArray[POST_BRACKET] = Arrays.asList(postBrackets.split(","));
        int bracketCount = 0;
        for (; lineNo < this.getLines().size(); lineNo++) {
            line = this.getLinesOfCode().get(lineNo).getContents();
            for (i = 0; i < line.length(); i++) {
                if ((line.charAt(i) == '{'))
                    bracketCount++;
                if ((line.charAt(i) == '}'))
                    bracketCount--;
            }
            
            if (bracketCount == 1 && listArray[METHOD_RANGE].size() == 0)
                listArray[METHOD_RANGE].add(""+this.getLines().get(lineNo).getLineNumber());
            if (bracketCount == 0 && listArray[METHOD_RANGE].size() == 1) {
                listArray[METHOD_RANGE].add(""+this.getLines().get(lineNo).getLineNumber());
                break;
            }
        }

        return listArray;
    }

    public boolean getFunctionSignature(int lineNo) {
        boolean openBracket = false;
        boolean closedBracket = false;
        boolean openCurly = false;
        String line = this.getLinesOfCode().get(lineNo).getContents();
        //line start
        int i = 0;
        for (; i < line.length(); i++) {
            if ((line.charAt(i) >= 'a' && line.charAt(i) <= 'z') ||
                    (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z'))
                break;
        }
        //start tokens
        for (; i < line.length(); i++) {
            if ((line.charAt(i) == '(')) {
                openBracket = true;
                i++;
                break;
            }
        }
        //between brackets tokens
        for (; i < line.length(); i++) {
            if ((line.charAt(i) == ')')) {
                closedBracket = true;
                i++;
                break;
            }
        }
        //post tokens
        for (; i < line.length(); i++) {
            if ((line.charAt(i) == '{')) {
                openCurly = true;
                break;
            }
        }
        return openBracket && closedBracket && openCurly;
    }


}
