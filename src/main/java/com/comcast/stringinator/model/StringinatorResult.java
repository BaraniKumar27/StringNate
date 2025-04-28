package com.comcast.stringinator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StringinatorResult {
    private final String input;
    private final Integer length;
    private char mostFrequentChar;
    private int mostFrequentCharCount;


}
