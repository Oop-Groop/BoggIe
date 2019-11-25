package oopgroop.projects.boggIe.api;

import java.rmi.server.ExportException;
import java.util.HashMap;

public class LetterScores {
    private HashMap<String, Integer> letterScores = new HashMap<>();

    public LetterScores() {
        this.letterScores.put("a", 2);
        this.letterScores.put("b", 5);
        this.letterScores.put("c", 1);
        this.letterScores.put("d", 4);
        this.letterScores.put("e", 1);
        this.letterScores.put("f", 2);
        this.letterScores.put("g", 6);
        this.letterScores.put("h", 4);
        this.letterScores.put("i", 2);
        this.letterScores.put("j", 7);
        this.letterScores.put("k", 5);
        this.letterScores.put("l", 4);
        this.letterScores.put("m", 3);
        this.letterScores.put("n", 2);
        this.letterScores.put("o", 2);
        this.letterScores.put("p", 4);
        this.letterScores.put("qu", 8);
        this.letterScores.put("r", 2);
        this.letterScores.put("s", 2);
        this.letterScores.put("t", 5);
        this.letterScores.put("u", 4);
        this.letterScores.put("v", 5);
        this.letterScores.put("w", 6);
        this.letterScores.put("x", 9);
        this.letterScores.put("y", 8);
        this.letterScores.put("z", 10);
    }

    public int GetScoreForLetter(String letter) {
        int letterScore = this.letterScores.getOrDefault(letter, -1);

        if (letterScore == -1) {
            throw new RuntimeException("Letter: " + letter + " does not exist");
        }

        return letterScore;
    }
}
