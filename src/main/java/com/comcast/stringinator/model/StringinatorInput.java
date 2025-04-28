package com.comcast.stringinator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringinatorInput {
    private String input;

    public StringinatorInput(String input) {
        this.input = input;
    }


}
