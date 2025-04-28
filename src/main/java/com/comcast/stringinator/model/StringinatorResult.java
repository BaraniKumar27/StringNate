package com.comcast.stringinator.model;

public class StringinatorResult {
    private final String input;
    private final Integer length;
    private char mostFrequentChar;
    private int mostFrequentCharCount;

    public StringinatorResult(String input, Integer length, char mostFrequentChar, int mostFrequentCharCount) {
        this.input = input;
        this.length = length;
        this.mostFrequentChar = mostFrequentChar;
        this.mostFrequentCharCount = mostFrequentCharCount;
    }

    public Integer getLength() {
        return length;
    }

    public String getInput() {
        return this.input;
    }

    public char getMostFrequentChar() {
        return mostFrequentChar;
    }

    public void setMostFrequentChar(char mostFrequentChar) {
        this.mostFrequentChar = mostFrequentChar;
    }

    public int getMostFrequentCharCount() {
        return mostFrequentCharCount;
    }

    public void setMostFrequentCharCount(int mostFrequentCharCount) {
        this.mostFrequentCharCount = mostFrequentCharCount;
    }
}
