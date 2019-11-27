package oopgroop.projects.boggIe.api;

import java.rmi.server.ExportException;
import java.util.HashMap;

public class LetterScores {
    private static HashMap<String, Integer> letterScores = new HashMap<>();

    static {
        letterScores.put("a", 2);
        letterScores.put("b", 5);
        letterScores.put("c", 1);
        letterScores.put("d", 4);
        letterScores.put("e", 1);
        letterScores.put("f", 2);
        letterScores.put("g", 6);
        letterScores.put("h", 4);
        letterScores.put("i", 2);
        letterScores.put("j", 7);
        letterScores.put("k", 5);
        letterScores.put("l", 4);
        letterScores.put("m", 3);
        letterScores.put("n", 2);
        letterScores.put("o", 2);
        letterScores.put("p", 4);
        letterScores.put("q", 4);
        letterScores.put("r", 2);
        letterScores.put("s", 2);
        letterScores.put("t", 5);
        letterScores.put("u", 4);
        letterScores.put("v", 5);
        letterScores.put("w", 6);
        letterScores.put("x", 9);
        letterScores.put("y", 8);
        letterScores.put("z", 10);
    }

    public static int GetScoreForLetter(String letter) {
        int letterScore = letterScores.getOrDefault(letter, -1);

        if (letterScore == -1) {
            throw new RuntimeException("Letter: " + letter + " does not exist");
        }

        return letterScore;
    }
}
