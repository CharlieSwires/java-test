package com.diffblue.interview;

public class CodeMethod {


    private String methodName = null;
    private String returnParameter = null;
    private String parameters = null;
    private int start = 0;
    private int end = 0;
    
    public CodeMethod(String methodName, String returnParameter, String parameters, int start,
            int end) {
        super();
        this.methodName = methodName;
        this.returnParameter = returnParameter;
        this.parameters = parameters;
        this.start = start;
        this.end = end;
    }
    
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getReturnParameter() {
        return returnParameter;
    }
    public void setReturnParameter(String returnParameter) {
        this.returnParameter = returnParameter;
    }
    public String getParameters() {
        return parameters;
    }
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CodeMethod [methodName=" + methodName + ", returnParameter=" + returnParameter
                + ", parameters=" + parameters + ", start=" + start + ", end=" + end + "]";
    }

}
