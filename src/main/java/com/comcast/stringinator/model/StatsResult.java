package com.comcast.stringinator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class StatsResult {
    private final Map<String, Integer> inputs;

    private String mostPopular;
    private String longestInputReceived;


    public StatsResult(Map<String, Integer> inputs, String mostPopular, String longestInputReceived) {
        this.inputs = inputs;
        this.mostPopular = mostPopular;
        this.longestInputReceived = longestInputReceived;
    }

}
