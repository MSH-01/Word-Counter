package com.cm6121.countWord.app.dataProcessing;

import java.util.*;
import java.util.stream.Collectors;

public class CorpusWordCounter implements Counter {

    private Map<String, Integer> sortedMap;

    @Override
    public String createWordCount(String[] corpusData){
        var fileWordCount = new HashMap<String, Integer>();
        var corpusContent = corpusData[2];
        var removeChar = new RemoveCharacters();

        String[] words = removeChar.removeUnwantedChars(corpusContent);

        // Create count of words in array and add to hashmap
        for(String word: words){
            fileWordCount.merge(word, 1, Integer::sum);
        }

        // Sort hashmap values by count of appearances, in descending order
        // ADAPTED FROM : https://stackabuse.com/how-to-sort-a-hashmap-by-value-in-java/
        // DATE ACCESSED : 17/05/2021
        Map<String, Integer> sortedMap = fileWordCount.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        setSortedCount(sortedMap);
        return corpusContent;
    }

    @Override
    public void setSortedCount(Map<String, Integer> map) {
        sortedMap = map;
    }

    @Override
    public void printWordCount() {
        System.out.println("\nCorpus Top 20 Words");
        int count = 0;
        for (String name : sortedMap.keySet()) {
            String value = sortedMap.get(name).toString();
            System.out.println("Word = " + name + " : Count = " + value);
            count +=1;
            if(count == 20){
                break;
            }
        }
    }

    @Override
    public Map<String, Integer> getSortedCount() {
        return sortedMap;
    }
}
