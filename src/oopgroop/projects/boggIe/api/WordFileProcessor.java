package oopgroop.projects.boggIe.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFileProcessor {
    public static String[] ConvertFileToWordList(File wordFile) throws FileNotFoundException {
        Scanner sc = new Scanner(wordFile);
        ArrayList<String> words = new ArrayList<>();

        while (sc.hasNextLine()) {
            String[] wordsOnLine = sc.nextLine().split(" ");
            for (String currentWord : wordsOnLine) {
                words.add(currentWord);
            }
        }

        return words.toArray(new String[words.size()]);
    }
}
