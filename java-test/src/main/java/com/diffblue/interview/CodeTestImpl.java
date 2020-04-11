package com.diffblue.interview;

import java.util.List;
import java.util.Set;

public class CodeTestImpl implements CodeTest{

    private String name;
    private Set<CodeLine> coveredLines;
    private List<Class> inputTypes;
    private List<Object> inputs;
    private Class outputType;
    private Object output;
    private CodeMethod methodUnderTest;
    private List<CodeLine> code;
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<CodeLine> getCoveredLines() {
        return this.coveredLines;
    }

    public List<Class> getInputTypes() {
        return inputTypes;
    }

    public void setInputTypes(List<Class> inputTypes) {
        this.inputTypes = inputTypes;
    }

    public List<Object> getInputs() {
        return inputs;
    }

    public void setInputs(List<Object> inputs) {
        this.inputs = inputs;
    }

    public Class getOutputType() {
        return outputType;
    }

    public void setOutputType(Class outputType) {
        this.outputType = outputType;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoveredLines(Set<CodeLine> coveredLines) {
        this.coveredLines = coveredLines;
    }

    public CodeMethod getMethodUnderTest() {
        return methodUnderTest;
    }

    public void setMethodUnderTest(CodeMethod methodUnderTest) {
        this.methodUnderTest = methodUnderTest;
    }

    public List<CodeLine> getCode() {
        return code;
    }

    public void setCode(List<CodeLine> code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeTestImpl [name=" + name + ", coveredLines=" + coveredLines + ", inputTypes="
                + inputTypes + ", inputs=" + inputs + ", outputType=" + outputType + ", output="
                + output + ", methodUnderTest=" + methodUnderTest + ", code=" + code + "]";
    }

}
