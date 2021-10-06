package com.cm6121.countWord.app.dataProcessing;

import java.util.*;
import java.util.stream.Collectors;

public class DocumentWordCounter implements Counter {

    private Map<String, Integer> sortedMap;
    private String documentTitle;
    private String fileTitle;

    @Override
    public String createWordCount(String[] fileContent){
        var fileWordCount = new HashMap<String, Integer>();
        var removeChar = new RemoveCharacters();
        fileTitle = fileContent[0];
        String file = fileContent[1];
        documentTitle = "\n" + fileTitle;

        String[] words = removeChar.removeUnwantedChars(file);

        // Create count of words in array and add to hashmap
        for(String word: words){
            fileWordCount.merge(word, 1, Integer::sum);
        }

        // Sort hashmap values by count of appearances, in ascending order
        // ADAPTED FROM : https://stackabuse.com/how-to-sort-a-hashmap-by-value-in-java/
        // DATE ACCESSED : 17/05/2021
        Map<String, Integer> sortedMap = fileWordCount.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));


        setSortedCount(sortedMap);
        return fileTitle;

    }

    @Override
    public void setSortedCount(Map<String, Integer> map) {
        sortedMap = map;
    }

    @Override
    public void printWordCount() {
        System.out.println(documentTitle);
        sortedMap.entrySet().forEach(System.out::println);
    }

    @Override
    public Map<String, Integer> getSortedCount() {
        return sortedMap;
    }
}
