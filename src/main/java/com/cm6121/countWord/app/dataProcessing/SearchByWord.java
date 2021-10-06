package com.cm6121.countWord.app.dataProcessing;

import java.util.*;

public class SearchByWord {

    public void search(List<Map<String, Integer>> listOfMaps, Map<String, Integer> corpusMap, ArrayList<String> documentTitles){
        Scanner userInputs = new Scanner(System.in);

        System.out.println("What word do you want to search for?");
        String userWord = userInputs.nextLine().toLowerCase();

        if(corpusMap.containsKey(userWord)){
            System.out.println("The word '" + userWord + "' appears in the whole corpus " + corpusMap.get(userWord) + " times");
        } else{
            System.out.println("The word '" + userWord + "' appears in the whole corpus 0 times");
        }

        int count = 0;
        for(Map<String, Integer> map: listOfMaps){
            if(map.containsKey(userWord)){
                System.out.println("The word '" + userWord + "' appears in text "+ documentTitles.get(count) + " " + map.get(userWord) + " times");
            } else{
                System.out.println("The word '" + userWord + "' appears in text " + documentTitles.get(count) + " 0 times");
            }
            count +=1;
        }
    }

}
