package com.cm6121.countWord.app.dataProcessing;

import org.apache.commons.lang3.ArrayUtils;

public class RemoveCharacters {

    public String[] removeUnwantedChars(String data){
        // Remove special characters and split into array of words
        String cleanContent =  (data.replaceAll("[^\\w]", " ")).toLowerCase();
        String[] words = cleanContent.split("\\s+");

        // Remove single character words
        for(String word : words){
            if(word.length() == 1){
                words = ArrayUtils.removeElement(words, word);
            }
        }

        return words;
    }
}
