package com.diffblue.interview;

public class CodeLineImpl implements CodeLine {

    private String line;
    private int number;
    
    @Override
    public int getLineNumber() {
        // TODO Auto-generated method stub
        return getNumber();
    }

    @Override
    public String getContents() {
        // TODO Auto-generated method stub
        return getLine();
    }

    public CodeLineImpl(String line, int number) {
        super();
        this.line = line;
        this.number = number;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((line == null) ? 0 : line.hashCode());
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CodeLineImpl other = (CodeLineImpl) obj;
        if (line == null) {
            if (other.line != null)
                return false;
        } else if (!line.equals(other.line))
            return false;
        if (number != other.number)
            return false;
        return true;
    }

}
