package com.cm6121.countWord.app.utilityFile;

import com.cm6121.countWord.app.dataProcessing.FileInformation;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class StatisticWriter {

    public void writeNewFile(Map<String, Integer> wordStatistics, String[] content) throws IOException {
        WriterCSV directory = new WriterCSV();
        String path = directory.createDirectory();
        String fileTitle = FileInformation.repairFileNames(content[0]);
        String fileDate = content[2];

        String csv = path+fileTitle+"_allWords.csv";
        writeToCSV(wordStatistics, content, fileDate, csv);
    }

    public void writeCorpusFile(Map<String, Integer> wordStatistics, String[] content) throws IOException {
        WriterCSV directory = new WriterCSV();
        String path = directory.createDirectory();
        String fileDate = content[1];

        String csv = path+"CSVAllDocuments_allWords.csv";
        writeToCSV(wordStatistics, content, fileDate, csv);
    }

    private void writeToCSV(Map<String, Integer> wordStatistics, String[] content, String fileDate, String csv) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        ArrayList<String> listOfKeys = new ArrayList<>(wordStatistics.keySet());

        ArrayList<Integer> listOfValues = new ArrayList<>(wordStatistics.values());

        String[] firstLine = new String[]{content[0], fileDate};

        writer.writeNext(firstLine);

        for(int i = 0; i < listOfKeys.size(); i++){
            String[] newLine = new String[]{listOfKeys.get(i), String.valueOf(listOfValues.get(i))};
            writer.writeNext(newLine);
        }
        writer.close();
    }
}
