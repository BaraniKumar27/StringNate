package com.comcast.stringinator.service;

import java.util.*;
import java.util.stream.Collectors;

import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringinatorServiceImpl implements StringinatorService {
    private static final Logger logger = LoggerFactory.getLogger(StringinatorServiceImpl.class);

    private Map<String, Integer> seenStrings = new HashMap<>();
    private String mostPopular = "";
    private String longestInput = "";

    @Override
    public StringinatorResult stringinate(StringinatorInput input) {
        logger.info("Processing stringinate for input: {}", input.getInput());

        seenStrings.compute(input.getInput(), (k, v) -> (v == null) ? Integer.valueOf(1) : v + 1);

        String text = input.getInput().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        Set<Character> foundVowels = new HashSet<>();

        if (seenStrings.get(input.getInput()) > seenStrings.getOrDefault(mostPopular, 0)) {
            logger.info("Updated most popular string: {}", mostPopular);
            mostPopular = input.getInput();
        }

        if (input.getInput().length() > longestInput.length()) {
            logger.info("Updated longest input string: {}", longestInput);
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
        logger.info("Most frequent character: {} with count: {}", mostFrequentChar, maxCount);
        return new StringinatorResult(input.getInput(), input.getInput().length(), mostFrequentChar, maxCount);
    }

    @Override
    public StatsResult stats() {
        logger.info("Calculating stats for all seen strings");
        Set<String> anagrams = findAnagrams(seenStrings.keySet());
        logger.info("Found anagrams: {}", anagrams);
        return new StatsResult(seenStrings.size(),mostPopular, longestInput,anagrams);
    }

    private Set<String> findAnagrams(Set<String> strings) {
        Map<String, List<String>> anagramGroups = strings.stream()
                .collect(Collectors.groupingBy(s -> {
                    char[] chars = s.toCharArray();
                    Arrays.sort(chars); // Sort characters of the string
                    return new String(chars);
                }));

        return anagramGroups.values().stream()
                .filter(list -> list.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

}
