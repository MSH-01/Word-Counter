package com.cm6121.countWord.app.dataProcessing;

public class FileInformation {

    public void printFileInformation(String[] currentFile, String fileName){
        String fileTitle = currentFile[0];
        String fileDate = currentFile[2];
        System.out.println("The file name is " + fileName + ", the title is " + fileTitle + ", the creation date is " + fileDate);

    }

    public static String repairFileNames(String title){

        return (title.replaceAll("\\s", "_"));
    }
}
