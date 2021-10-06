package com.cm6121.countWord.app;

import com.cm6121.countWord.app.utilityFile.ApplicationFunctions;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main (String[] args) throws IOException {
        boolean userContinue = true;
        while(userContinue){
            var applicationFunc = new ApplicationFunctions();
            applicationFunc.writeAllFiles();
            Scanner userInputs = new Scanner(System.in);
            System.out.println("\nThe counting words application \n");
            System.out.println("(Enter number of option) \n1) Do you want to display the names and the number of documents? \n2) Do you want to display the number of occurrences of the words for each document? \n3) Do you want to enter a word, and display the number of occurrences of it in each document? \n4) Do you want to display the number of occurrences for each word in the whole corpus? \n5) Exit");
            String user = userInputs.nextLine();
            switch(user){
                case "1":
                    applicationFunc.printFileInformation();
                    break;
                case "2":
                    applicationFunc.printFileWordCounts();
                    break;
                case "3":
                    applicationFunc.searchForWord();
                    break;
                case "4":
                    applicationFunc.printCorpusInformation();
                    break;
                case "5":
                    userContinue = false;
                    break;
            }

        }

    }
}


