package com.cm6121.countWord.app.utilityFile;

import com.cm6121.countWord.app.dataProcessing.CorpusWordCounter;
import com.cm6121.countWord.app.dataProcessing.DocumentWordCounter;
import com.cm6121.countWord.app.dataProcessing.FileInformation;
import com.cm6121.countWord.app.dataProcessing.SearchByWord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationFunctions {

    private final List<Map<String, Integer>> listOfMaps = new ArrayList<>();
    private Map<String, Integer> corpusMap;
    private final ArrayList<String> documentTitles = new ArrayList<>();

    private final SearchByWord wordSearch = new SearchByWord();
    private final String documentToRead = ClassLoader.getSystemClassLoader().getResource("FolderDocumentsToRead").getPath();
    private final File[] filePaths = FileReader.getResourceFolderFiles(documentToRead);
    private final FileReader myReader = new FileReader();
    private final DocumentWordCounter wordCount = new DocumentWordCounter();
    private final StatisticWriter myWriter = new StatisticWriter();
    private final CorpusWordCounter corpusCount = new CorpusWordCounter();

    public void printCorpusInformation(){
        corpusCount.printWordCount();
    }

    public void printFileInformation(){
        var fileInfo = new FileInformation();

        int documentCount = 0;
        for (File ignored : filePaths) {
            documentCount += 1;
        }
        System.out.println("The number of documents in the folder is " + documentCount + "\n");

        for (File file : filePaths) {
            List<String[]> fileLines = myReader.readCSVMethod1(file);
            String fileName = file.getName();
            for (String[] fileLine : fileLines) {
                fileInfo.printFileInformation(fileLine, fileName);
            }
        }

    }

    public void printFileWordCounts() throws IOException {
        for (File file : filePaths) {
            List<String[]> fileLines = myReader.readCSVMethod1(file);
            for (String[] content : fileLines) {
                wordCount.createWordCount(content);
                Map<String, Integer> wordStatistics = wordCount.getSortedCount();
                myWriter.writeNewFile(wordStatistics, content);
                wordCount.printWordCount();
            }
        }
    }

    public void writeAllFiles() throws IOException{
        for (File file : filePaths) {
            List<String[]> fileLines = myReader.readCSVMethod1(file);
            for (String[] content : fileLines) {
                documentTitles.add(wordCount.createWordCount(content));
                Map<String, Integer> wordStatistics = wordCount.getSortedCount();
                myWriter.writeNewFile(wordStatistics, content);
                listOfMaps.add(wordStatistics);
            }
        }

        // Find corpus values
        String corpusContent = "";
        for (File file : filePaths) {
            List<String[]> fileLines = myReader.readCSVMethod1(file);
            for (String[] content : fileLines) {
                corpusContent = corpusContent.concat(content[1]);
            }
        }
        String[] corpusData = new String[]{"Word", "Number of occurrences", corpusContent};
        corpusCount.createWordCount(corpusData);
        myWriter.writeCorpusFile(corpusCount.getSortedCount(), corpusData);
        corpusMap = corpusCount.getSortedCount();
    }

    public void searchForWord(){
        wordSearch.search(listOfMaps, corpusMap,documentTitles);
    }
}
