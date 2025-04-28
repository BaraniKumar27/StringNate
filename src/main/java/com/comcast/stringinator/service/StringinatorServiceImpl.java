package com.comcast.stringinator.service;

import java.util.HashMap;
import java.util.Map;

import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;

import org.springframework.stereotype.Service;

@Service
public class StringinatorServiceImpl implements StringinatorService {

    private Map<String, Integer> seenStrings = new HashMap<>();
    private String mostPopular = "";
    private String longestInput = "";

    @Override
    public StringinatorResult stringinate(StringinatorInput input) {
        seenStrings.compute(input.getInput(), (k, v) -> (v == null) ? Integer.valueOf(1) : v + 1);

        String text = input.getInput().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (seenStrings.get(input.getInput()) > seenStrings.getOrDefault(mostPopular, 0)) {
            mostPopular = input.getInput();
        }

        if (input.getInput().length() > longestInput.length()) {
            longestInput = input.getInput();
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        char mostFrequentChar = 0;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentChar = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return new StringinatorResult(input.getInput(), input.getInput().length(), mostFrequentChar, maxCount);
    }

    @Override
    public StatsResult stats() {
        return new StatsResult(seenStrings.size(),mostPopular, longestInput);
    }
    

}
