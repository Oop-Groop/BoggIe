package oopgroop.projects.boggIe.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFileProcessor {
	public static String[] ConvertFileToWordList(final File wordFile) throws FileNotFoundException {
		final Scanner sc = new Scanner(wordFile);
		final ArrayList<String> words = new ArrayList<>();

		while (sc.hasNextLine()) {
			final String[] wordsOnLine = sc.nextLine().split(" ");
			for (final String currentWord : wordsOnLine)
				words.add(currentWord);
		}

		return words.toArray(new String[words.size()]);
	}
}
