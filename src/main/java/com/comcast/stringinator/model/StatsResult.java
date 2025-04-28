package com.comcast.stringinator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class StatsResult {
   // private final Map<String, Integer> inputs;
    private int totalStrings;
    private String mostPopular;
    private String longestInputReceived;


}
