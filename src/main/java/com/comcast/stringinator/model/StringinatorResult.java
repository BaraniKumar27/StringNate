package com.comcast.stringinator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
