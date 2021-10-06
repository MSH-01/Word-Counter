package com.cm6121.countWord.app.dataProcessing;

import java.util.Map;

public interface Counter {

    String createWordCount(String[] fileContent);

    void setSortedCount(Map<String,Integer> map);

    void printWordCount();

    Map<String, Integer> getSortedCount();
}
